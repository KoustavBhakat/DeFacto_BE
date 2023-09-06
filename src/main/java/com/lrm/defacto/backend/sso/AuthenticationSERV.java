package com.lrm.defacto.backend.sso;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.lrm.defacto.backend.core.auth.dto.JWTRequestBody;
import com.lrm.defacto.backend.core.auth.dto.JwtResponseBody;
import com.lrm.defacto.backend.core.auth.util.JwtUtil;
import com.lrm.defacto.backend.user.UserMODL;
import com.lrm.defacto.backend.user.UserREPO;

@Service
public class AuthenticationSERV {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserREPO userRepo;

	private JWTRequestBody jwtRequestBody;

	@Value("${google.client.id}")
	private String GOOGLE_CLIENT_ID;

	public Object validateToken(HashMap<String, Object> data) throws Exception {

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
				.setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build();
		String token = (String) data.get("token");
		System.out.println("verifyToken ===> " + token);
		GoogleIdToken idToken = verifier.verify(token);
		System.out.println("idToken ===>> " + idToken);
		Payload payload = null;
		UserMODL user = null;

		String jwtToken = "";
		if (idToken != null) {
			payload = idToken.getPayload();
			boolean value = (boolean) payload.get("email_verified");

			if (value) {

				if (this.jwtRequestBody == null) {
					jwtRequestBody = new JWTRequestBody((String) payload.get("name"), (String) payload.get("hd"));
				} else {
					jwtRequestBody.setUsername((String) payload.get("name"));
					jwtRequestBody.setEmailDomain((String) payload.get("hd"));
					;
				}
				jwtToken = this.jwtUtil.generateJWTToken(jwtRequestBody);

				String email = (String) payload.get("email");

				if (email != null) {
					user = this.userRepo.findByEmail(email);
				} else {
					throw new Exception("Invalid email");
				}
			}

		} else {
			System.out.println("Invalid ID token.");
			throw new Exception("Invalid ID token");
		}

		JwtResponseBody jwtResponse = new JwtResponseBody();
		jwtResponse.setUser_email((String) payload.get("email"));

		jwtResponse.setUser_id(user != null ? user.getId() : null);
		jwtResponse.setToken(jwtToken);
		return jwtResponse;
	}

}
