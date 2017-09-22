package com.revisamigrieta.backend;

import com.revisamigrieta.backend.models.GrietaModel;
import com.revisamigrieta.backend.models.RevisionModel;
import com.revisamigrieta.backend.models.dao.RevisionDao;
import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;

import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;
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
						jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com")
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
	                          @Named("comentariosAdicionales") String comentariosAdicionales,
	                          @Named("diagonalEnLozaDeEsquinaACentro") boolean diagonalEnLozaDeEsquinaACentro,
	                          @Named("grietasFormanDiagonalDelPiso") boolean grietasFormanDiagonalDelPiso,
	                          @Named("grietasParalelasAPiso") boolean grietasParalelasAPiso,
	                          @Named("peligroInminente") boolean peligroInminente,
	                          @Named("loza") boolean loza,
	                          @Named("piso") boolean piso,
	                          @Named("id") String id) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Invalid credentials");
		}
		RevisionModel revisionModel = new RevisionModel();

		revisionModel.setGrietaModelRef(id);
		revisionModel.setComentariosAdicionales(comentariosAdicionales);
		revisionModel.setDiagonalEnLozaDeEsquinaACentro(diagonalEnLozaDeEsquinaACentro);
		revisionModel.setGrietasFormanDiagonalDelPiso(grietasFormanDiagonalDelPiso);
		revisionModel.setGrietasParalelasAPiso(grietasParalelasAPiso);
		revisionModel.setLoza(loza);
		revisionModel.setPeligroIniminente(peligroInminente);
		revisionModel.setPiso(piso);
		revisionModel.setRevisadaPor(user.getId());

		if(!revisionModel.getGrietaModelRef().isRevisada()){
			revisionModel.getGrietaModelRef().setRevisada(true);
		}

		ofy().save().entity(revisionModel).now();
	}
	// [END publish_method]

	// [START retrieveAllGrietas_method]
	@ApiMethod(name = "retrieveAllGrietasReview", path = API_VERSION + "/grietas/{grietaId}/revisiones", httpMethod = ApiMethod.HttpMethod.GET)
	public List<RevisionModel> retrieveAllGrietasReview(@Named("grietaId") String grietaId) {

		Key grietaModelKey = Key.create(GrietaModel.class, grietaId);

		List<RevisionModel> revisionModels = ofy().load().type(RevisionModel.class).filter("grietaModelRef", grietaModelKey).list();
		logger.info(revisionModels.toString());
		return revisionModels;
	}
	// [END retrieveAllGrietas_method]

	// [START retrieveGrieta_method]
	@ApiMethod(name = "retrieveGrietasReview", path = API_VERSION + "/grietas/{grietaId}/revisiones/{revisionId}", httpMethod = ApiMethod.HttpMethod.GET)
	public RevisionModel retrieveGrietasReview(@Named("grietaId") String grietaId, @Named("revisionId") String id) {
		Long revisionId = Long.parseLong(id);
		RevisionDao revisionDao = new RevisionDao();
		RevisionModel revisionModel = revisionDao.get(revisionId);
		logger.info(revisionModel.toString());
		return revisionModel;
	}
	// [END retrieveGrieta_method]

}
