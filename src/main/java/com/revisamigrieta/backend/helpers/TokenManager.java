package com.revisamigrieta.backend.helpers;

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.repackaged.com.google.gson.JsonElement;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;
import endpoints.repackaged.org.jose4j.jwt.JwtClaims;
import endpoints.repackaged.org.jose4j.jwt.MalformedClaimException;
import endpoints.repackaged.org.jose4j.jwt.consumer.InvalidJwtException;
import endpoints.repackaged.org.jose4j.jwt.consumer.JwtConsumer;
import endpoints.repackaged.org.jose4j.jwt.consumer.JwtConsumerBuilder;
import endpoints.repackaged.org.jose4j.jwt.consumer.JwtContext;
import endpoints.repackaged.org.jose4j.jwx.JsonWebStructure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

public class TokenManager {
	private final static String PROJECT_ID = "revisamigrieta";
	private final static String AUDIENCE = PROJECT_ID;
	private final static String ISSUER = "https://securetoken.google.com/" + PROJECT_ID;
	private final static String KEYS_URL = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com";

	/**
	 * Parses and verifies a FirebaseUser ID token (JWT) and returns the associated user's uid
	 *
	 * @param token the firebase user's token
	 * @return the firebase user UID
	 * @throws UnauthorizedException if the token is invalid.
	 */
	public static String verfiyToken(String token) throws UnauthorizedException {
		JwtConsumer firstPassJwtConsumer = new JwtConsumerBuilder()
				.setSkipAllValidators()
				.setDisableRequireSignature()
				.setSkipSignatureVerification()
				.build();

		//The first JwtConsumer is basically just used to parse the JWT into a JwtContext object.
		JwtContext jwtContext;
		try {
			jwtContext = firstPassJwtConsumer.process(token);
		} catch (InvalidJwtException e) {
			throw new UnauthorizedException(e.getMessage());
		}

		// get the key id from the header of the JWT
		List<JsonWebStructure> list = jwtContext.getJoseObjects();
		String kid = list.get(0).getKeyIdHeaderValue();
		String keyAsString;
		try {
			keyAsString = getPublicKey(kid);
		} catch (IOException e) {
			throw new UnauthorizedException(e.getMessage());
		}

		// decode the key into proper format
		InputStream certIs = new ByteArrayInputStream(Charset.forName("UTF-8").encode(keyAsString).array());

		CertificateFactory certificateFactory;
		try {
			certificateFactory = CertificateFactory.getInstance("X.509");
		} catch (CertificateException e) {
			throw new UnauthorizedException(e.getMessage());
		}

		X509Certificate cert;
		try {
			cert = (X509Certificate) certificateFactory.generateCertificate(certIs);
		} catch (CertificateException e) {
			throw new UnauthorizedException(e.getMessage());
		}
		PublicKey key = cert.getPublicKey();

		// now that we have the public key, we can verify the JWT
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime() // the JWT must have an expiration time
				.setMaxFutureValidityInMinutes(300) // but the  expiration time can't be too crazy
				.setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
				.setRequireSubject() // the JWT must have a subject claim
				.setExpectedIssuer(ISSUER) // whom the JWT needs to have been issued by
				.setExpectedAudience(AUDIENCE) // to whom the JWT is intended for
				.setVerificationKey(key) // verify the signature with the public key
				.build(); // create the JwtConsumer instance

		JwtClaims jwtClaims;
		try {
			//  Validate the JWT and process it to the Claims
			jwtClaims = jwtConsumer.processToClaims(token);
		} catch (InvalidJwtException e)  {
			throw new UnauthorizedException(e.getMessage());
		}

		String userUid;

		try {
			userUid = jwtClaims.getSubject();
		} catch(MalformedClaimException e) {
			throw new UnauthorizedException(e.getMessage());
		}
		return userUid;
	}


	/**
	 * Grab the certificate corresponding to the keyid specified in the JWT
	 *
	 * @param kid key id corresponding to one of the public keys listed at public keys listed at
	 *            https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com
	 * @return the certificate
	 * @throws IOException if the process fails
	 */
	private static String getPublicKey(String kid) throws IOException {
		URL url = new URL(KEYS_URL);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		JsonParser jp = new JsonParser(); //from gson
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
		JsonObject rootobj = root.getAsJsonObject();
		String publicKey = rootobj.get(kid).getAsString();

		return publicKey;
	}
}