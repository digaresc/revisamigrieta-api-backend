package com.revisamigrieta.backend;

import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.UnauthorizedException;
import com.revisamigrieta.backend.models.GrietaModel;
import com.revisamigrieta.backend.models.RevisionModel;
import com.revisamigrieta.backend.models.dao.GrietaDao;

import java.util.logging.Logger;

import static com.revisamigrieta.backend.helpers.Constants.API_VERSION;

/**
 * The RevisionEndpoint API which Endpoints will be exposing.
 */
// [START grieta_api_annotation]
@Api(
		name = "grieta",
		version = "v1",
		namespace =
		@ApiNamespace(
				ownerDomain = "backend.revisamigrieta.backend",
				ownerName = "backend.revisamigrieta.backend",
				packagePath = ""
		),
		// [START_EXCLUDE]
		issuers = {
				@ApiIssuer(
						name = "firebase",
						issuer = "https://securetoken.google.com/revisamigrieta",
						jwksUri = "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system.gserviceaccount.com")
		},
		issuerAudiences = {
				@ApiIssuerAudience(name = "firebase", audiences = "revisamigrieta")
		}
		// [END_EXCLUDE]
)
// [END grieta_api_annotation]
public class RevisionEndpoint {
	private static final Logger logger = Logger.getLogger(RevisionEndpoint.class.getName());
	// [START publish_method]
	@ApiMethod(name = "publishReview", path = API_VERSION + "/grietas/{id}/revisiones",
			httpMethod = ApiMethod.HttpMethod.POST,
			authenticators = {EspAuthenticator.class},
			issuerAudiences = {@ApiIssuerAudience(name = "firebase",
					audiences = {"revisamigrieta"})})
	public void publishReview(User user,
	                          @Named("comentarios") String comentarios,
	                          @Named("diagonalesLosa") boolean diagonalesLosa,
	                          @Named("diagonalesPiso") boolean diagonalesPiso,
	                          @Named("paralelasPiso") boolean paralelasPiso,
	                          @Named("peligroInminente") boolean peligroInminente,
	                          @Named("id") String id) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Invalid credentials");
		}

		GrietaDao grietaDao = new GrietaDao();




		GrietaModel grietaModel = grietaDao.get(Long.parseLong(id));

		RevisionModel revisionModel = new RevisionModel();
		revisionModel.setRevisadaPor(user.getId());

		revisionModel.setComentarios(comentarios);
		revisionModel.setDiagonalesLosa(diagonalesLosa);
		revisionModel.setDiagonalesPiso(diagonalesPiso);
		revisionModel.setParalelasPiso(paralelasPiso);
		revisionModel.setPeligroInminente(peligroInminente);



		grietaModel.addRevision(revisionModel);


		logger.info(grietaModel.toString());

		if(!grietaModel.isRevisada()){
			grietaModel.setRevisada(true);
		}

		grietaDao.put(grietaModel);



	}





}
