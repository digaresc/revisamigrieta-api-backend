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

;
import com.revisamigrieta.backend.models.GrietaModel;
import com.revisamigrieta.backend.models.RevisionModel;
import com.revisamigrieta.backend.models.dao.RevisionDao;
import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * The Revision API which Endpoints will be exposing.
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
public class Revision {

	// [START publish_method]
	@ApiMethod(name = "publishReview", path = "/grieta/{id}/revision",
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

		ofy().save().entity(revisionModel).now();
	}
	// [END publish_method]

	// [START retrieveAllGrietas_method]
	@ApiMethod(name = "retrieveAllGrietasReview", path = "/grieta/{grietaId}/revision", httpMethod = ApiMethod.HttpMethod.GET)
	public List<RevisionModel> retrieveAllGrietasReview(@Named("grietaId") String grietaId) {

		Key grietaModelKey = Key.create(GrietaModel.class, grietaId);

		List<RevisionModel> revisionModels = ofy().load().type(RevisionModel.class).filter("grietaModelRef",grietaModelKey).list();
		System.out.println(revisionModels.toString());
		return revisionModels;
	}
	// [END retrieveAllGrietas_method]

	// [START retrieveGrieta_method]
	@ApiMethod(name = "retrieveGrietasReview", path = "/grieta/{grietaId}/revision/{revisionId}", httpMethod = ApiMethod.HttpMethod.GET)
	public RevisionModel retrieveGrietasReview(@Named("grietaId") String grietaId, @Named("revisionId") String id) {
		Long revisionId = Long.parseLong(id);
		RevisionDao revisionDao = new RevisionDao();
		RevisionModel revisionModel = revisionDao.get(revisionId);
		System.out.println(revisionModel.toString());
		return revisionModel;
	}
	// [END retrieveGrieta_method]

}
