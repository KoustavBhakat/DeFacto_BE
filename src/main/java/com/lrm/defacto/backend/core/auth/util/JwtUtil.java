package com.lrm.defacto.backend.core.auth.util;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lrm.defacto.backend.core.auth.dto.JWTRequestBody;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value(("S{jwtExpirationMs}"))
    private String jwtExpirationMs;

    public String generateJWTToken(JWTRequestBody jwtRequestBody){
        //check what are principals and roles and implement them
        return Jwts.builder()
//        		.setClaims(jwtRequestBody)
                .setSubject(jwtRequestBody.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (8 * 60 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("forceLogoutTime",jwtRequestBody.getCurrentDate())
                .compact();
    }
    
    public String generateJWTTokenForAskForHelp(HashMap<String, Object> jwtRequestBody){
        //check what are principals and roles and implement them
        return Jwts.builder()
        		.setClaims(jwtRequestBody)
                //.setSubject(jwtRequestBody.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (24 * 60 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //retrieve username from jwt token
    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getSubjectFromToken(token);
        System.err.println(username + "        "+ userDetails == null);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
