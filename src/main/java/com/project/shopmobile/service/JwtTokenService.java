package com.project.shopmobile.service;

import com.nimbusds.jose.JOSEException;
import com.project.shopmobile.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

public interface JwtTokenService {
    String generateOauth2AccessToken(User user);

    boolean verifyToken(String token) throws ParseException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException;
}
