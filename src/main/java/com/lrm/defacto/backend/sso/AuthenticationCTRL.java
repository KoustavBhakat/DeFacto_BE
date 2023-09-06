package com.lrm.defacto.backend.sso;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrm.defacto.backend.sso.statusmessages.ErrorMessages;
import com.lrm.defacto.backend.sso.statusmessages.ErrorStatuses;
import com.lrm.defacto.backend.sso.statusmessages.SuccessMessages;
import com.lrm.defacto.backend.sso.statusmessages.SuccessStatuses;
import com.lrm.defacto.backend.util.Response;

@RestController
@RequestMapping("/sign-in")
public class AuthenticationCTRL {

	@Autowired
	private AuthenticationSERV authServ;

	@PostMapping("/google")
	public ResponseEntity<?> googleSignIn(@RequestBody HashMap<String, Object> data) {

		try {
			return new ResponseEntity<>(new Response<>(SuccessStatuses.SIGNED_IN_SUCCESSFULLY,
					SuccessMessages.SIGNED_IN_SUCCESSFULLY, this.authServ.validateToken(data)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Response<>(ErrorStatuses.ERROR_OCCURRED_WHILE_SIGNING_IN,
					ErrorMessages.ERROR_OCCURRED_WHILE_SIGNING_IN, new HashMap<>()), HttpStatus.BAD_REQUEST);
		}
	}

}
