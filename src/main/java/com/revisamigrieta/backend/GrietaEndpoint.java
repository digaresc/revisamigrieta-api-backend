package com.revisamigrieta.backend;

import com.google.api.server.spi.response.NotFoundException;
import com.revisamigrieta.backend.models.GrietaModel;
import com.revisamigrieta.backend.models.dao.GrietaDao;
import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.GeoPt;

import java.util.List;
import java.util.logging.Logger;

/**
 * The GrietaEndpoint API which Endpoints will be exposing.
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
public class GrietaEndpoint {
	private static final Logger logger = Logger.getLogger(GrietaEndpoint.class.getName());
	// [START publish_method]
	@ApiMethod(
			name = "publish",
			path = "/grietas",
			httpMethod = ApiMethod.HttpMethod.POST,
			authenticators = {EspAuthenticator.class},
			issuerAudiences = {@ApiIssuerAudience(name = "firebase",
					audiences = {"revisamigrieta"})}
	)
	public void publish(User user,
	                    @Named("critica") boolean critica,
	                    @Named("desplomes") boolean desplomes,
	                    @Named("desprendimiento") boolean desprendimiento,
	                    @Named("diagonalConHorizontalDePiso") boolean diagonalConHorizontalDePiso,
	                    @Named("diagonalEnLozaDeEsquinaACentro") boolean diagonalEnLozaDeEsquinaACentro,
	                    @Named("externa") boolean externa,
	                    @Named("golpeteo") boolean golpeteo,
	                    @Named("hundimiento") boolean hundimiento,
	                    @Named("interna") boolean interna,
	                    @Named("loza") boolean loza,
	                    @Named("message") String message,
	                    @Named("paralelaAPiso") boolean paralelaAPiso,
	                    @Named("pared") boolean pared,
	                    @Named("pisosHuecos") boolean pisosHuecos,
	                    @Named("vibraciones") boolean vibraciones,
	                    @Named("latitude") float latitude,
	                    @Named("longitude") float longitude,
	                    @Named("grietaId") Long grietaId
	) throws UnauthorizedException, NotFoundException {
		if (user == null) {
			throw new UnauthorizedException("Invalid credentials");
		}
		GrietaDao grietaDao = new GrietaDao();
		GrietaModel grietaModel = grietaDao.get(grietaId);

		if (grietaModel == null || grietaModel.getFiles().size() == 0 || grietaModel.getReportadaPor() != user.getId()) {
			throw new NotFoundException("Una grieta require al menos una imagen");
		}

		grietaModel.setCritica(critica);
		grietaModel.setDesplomes(desplomes);
		grietaModel.setDesprendimiento(desprendimiento);
		grietaModel.setDiagonalConHorizontalDePiso(diagonalConHorizontalDePiso);
		grietaModel.setDiagonalEnLozaDeEsquinaACentro(diagonalEnLozaDeEsquinaACentro);
		grietaModel.setExterna(externa);
		grietaModel.setGolpeteo(golpeteo);
		grietaModel.setHundimientos(hundimiento);
		grietaModel.setInterna(interna);
		grietaModel.setLoza(loza);
		grietaModel.setMessage(message);
		grietaModel.setParalelaAlPiso(paralelaAPiso);
		grietaModel.setPared(pared);
		grietaModel.setPisosHuecos(pisosHuecos);
		grietaModel.setReportadaPor(user.getId());
		grietaModel.setVibraciones(vibraciones);
		grietaModel.setGeoPt(new GeoPt(latitude, longitude));


		grietaDao.put(grietaModel);

	}
	// [END publish_method]

	// [START retrieveAllGrietas_method]
	@ApiMethod(name = "retrieveAllGrietas", path = "/grietas", httpMethod = ApiMethod.HttpMethod.GET)
	public List<GrietaModel> retrieveAllGrietas() {

		GrietaDao grietaDao = new GrietaDao();
		List<GrietaModel> grietaModelList = grietaDao.getAll();
		logger.info(grietaModelList.toString());
		return grietaModelList;

	}
	// [END retrieveAllGrietas_method]


	// [START retrieveGrieta_method]
	@ApiMethod(name = "retrieveGrietas", path = "/grietas/{id}", httpMethod = ApiMethod.HttpMethod.GET)
	public GrietaModel retrieveGrietas(@Named("id") String id) {
		Long grietaId = Long.parseLong(id);
		GrietaDao grietaDao = new GrietaDao();

		GrietaModel grietaModel = grietaDao.get(grietaId);

		logger.info(grietaModel.toString());
		return grietaModel;
	}
	// [END retrieveGrieta_method]


}
