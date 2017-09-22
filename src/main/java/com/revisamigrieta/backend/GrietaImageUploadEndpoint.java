package com.revisamigrieta.backend;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.tools.cloudstorage.*;
import com.revisamigrieta.backend.models.GrietaModel;
import com.revisamigrieta.backend.models.dao.GrietaDao;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import static com.revisamigrieta.backend.helpers.Constants.BUCKET_NAME;

public class GrietaImageUploadEndpoint extends HttpServlet {
	private static final Logger log = Logger.getLogger(GrietaImageUploadEndpoint.class.getName());

	// [START gcs]
	private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
			.initialRetryDelayMillis(10)
			.retryMaxAttempts(10)
			.totalRetryPeriodMillis(15000)
			.build());
	// [END gcs]

	/**Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB */
	private static final int BUFFER_SIZE = 2 * 1024 * 1024;


	@SuppressWarnings("serial")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String sctype = null, sfieldname, sname = null;
		ServletFileUpload upload;
		FileItemIterator iterator;
		FileItemStream item;
		InputStream stream = null;

		ArrayList fileList = new ArrayList();
		try {
			upload = new ServletFileUpload();
			resp.setContentType("text/plain");

			iterator = upload.getItemIterator(req);
			while (iterator.hasNext()) {
				item = iterator.next();
				stream = item.openStream();

				if (item.isFormField()) {
					log.warning("Got a form field: " + item.getFieldName());
				} else {
					log.warning("Got an uploaded file: " + item.getFieldName() +
							", name = " + item.getName());

					//sfieldname = item.getFieldName();

					sname = item.getName();
					String extension = sname.substring(sname.lastIndexOf('.'),sname.length());

					sctype = item.getContentType();

					String filename;
					String dateString = String.valueOf(Calendar.getInstance().getTimeInMillis());
					filename = UUID.randomUUID().toString() +"-"+ dateString;


					byte[] imageBytes = IOUtils.toByteArray(stream);
					// Write the original image to Cloud Storage
					gcsService.createOrReplace(
							new GcsFilename(BUCKET_NAME, filename + extension),
							new GcsFileOptions.Builder().acl("public-read")
									.mimeType("image/jpeg")
									.addUserMetadata("date",dateString)
									.build(),
							ByteBuffer.wrap(imageBytes));
					//[END original_image]



					//[START resize]
					// Get an instance of the imagesService we can use to transform images.
					ImagesService imagesService = ImagesServiceFactory.getImagesService();

					// Make an image directly from a byte array, and transform it.
					Image image = ImagesServiceFactory.makeImage(imageBytes);
					Transform resize = ImagesServiceFactory.makeResize(300, 300,false);
					Image resizedImage = imagesService.applyTransform(resize, image);

					// Write the transformed image back to a Cloud Storage object.
					gcsService.createOrReplace(
							new GcsFilename(BUCKET_NAME, filename + "-thumb.jpeg"),
							new GcsFileOptions.Builder()
									.acl("public-read")
									.addUserMetadata("date",dateString)
									.mimeType("image/jpeg").build(),
							ByteBuffer.wrap(resizedImage.getImageData()));
					//[END resize]

					fileList.add(filename + ".jpeg");

					//res.sendRedirect("/");

				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		GrietaDao grietaDao = new GrietaDao();
		GrietaModel grietaModel = new GrietaModel();
		grietaModel.setFiles(fileList);
		grietaModel.setReportadaPor("Diego");

		GrietaModel grietaModelResponse = grietaDao.put(grietaModel);
		HashMap<String, Long> response = new HashMap<>();
		response.put("grietaId",grietaModel.id);

		String json = new Gson().toJson(response);

		resp.setContentType("application/json");
		resp.getWriter().print(json);

	}


	private void copy(InputStream input, OutputStream output) throws IOException {
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = input.read(buffer);
			while (bytesRead != -1) {
				output.write(buffer, 0, bytesRead);
				bytesRead = input.read(buffer);
			}
		} finally {
			input.close();
			output.close();
		}
	}



}
