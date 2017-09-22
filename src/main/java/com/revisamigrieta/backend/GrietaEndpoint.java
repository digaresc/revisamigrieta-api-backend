/*
 * Copyright (c) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not  use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.revisamigrieta.backend;

import com.revisamigrieta.backend.models.GrietaModel;
import com.revisamigrieta.backend.models.dao.GrietaDao;
import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.GeoPt;

import java.util.List;

/**
 * The Grieta API which Endpoints will be exposing.
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
public class Grieta {

	// [START publish_method]
	@ApiMethod(
			name = "publish",
			path = "/grieta",
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
	                    @Named("longitude") float longitude
	                    ) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Invalid credentials");
		}
		GrietaModel grietaModel = new GrietaModel();

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

		GrietaDao grietaDao = new GrietaDao();
		grietaDao.put(grietaModel);

	}
	// [END publish_method]

	// [START retrieveAllGrietas_method]
	@ApiMethod(name = "retrieveAllGrietas", path = "/grieta",httpMethod = ApiMethod.HttpMethod.GET)
	public List<GrietaModel> retrieveAllGrietas() {

		GrietaDao grietaDao = new GrietaDao();
		List<GrietaModel> grietaModelList = grietaDao.getAll();
		System.out.println(grietaModelList.toString());
		return grietaModelList;

	}
	// [END retrieveAllGrietas_method]

	// [START retrieveGrieta_method]
	@ApiMethod(name = "retrieveGrietas", path = "/grieta/{id}",httpMethod = ApiMethod.HttpMethod.GET)
	public GrietaModel retrieveGrietas(@Named("id") String id) {
		Long grietaId = Long.parseLong(id);
		GrietaDao grietaDao = new GrietaDao();

		GrietaModel grietaModel = grietaDao.get(grietaId);

		System.out.println(grietaModel.toString());
		return grietaModel;
	}
	// [END retrieveGrieta_method]


}
