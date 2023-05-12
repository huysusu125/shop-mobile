package com.project.shopmobile.service.impl;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.project.shopmobile.entity.User;
import com.project.shopmobile.service.JwtTokenService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.tokenExpiration:7200}")
    private Long tokenExpiration;
    private static final String BEARER_PREFIX = "Bearer ";
    private KeyPair keyPair;

    @Value("${jwt.public-key}")
    private String publicKeyString;

    @Value("${jwt.private-key}")
    private String privateKeyString;

    @PostConstruct
    public void init() throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", ""));
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s", ""));
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        keyPair = new KeyPair(publicKey, privateKey);
    }

    private String generateAccessToken(User user) throws JOSEException {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + tokenExpiration * 1000);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .expirationTime(expirationDate)
                .build();

        return signTokenRS256(claimsSet);
    }

    @SneakyThrows
    @Override
    public String generateOauth2AccessToken(User user) {
        return this.generateAccessToken(user);
    }

    @Override
    public boolean verifyToken(String token) throws ParseException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        if (token.trim().isEmpty() || !token.startsWith(BEARER_PREFIX)) {
            return false;
        }
        token = token.replace(BEARER_PREFIX, "");
        JWSObject jwsObject = JWSObject.parse(token);
        byte[] encoded = org.apache.commons.codec.binary.Base64.decodeBase64(publicKeyString.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", ""));
        KeyFactory kf = KeyFactory.getInstance("RSA");

        RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
        RSASSAVerifier verifier = new RSASSAVerifier(rsaPublicKey);
        return jwsObject.verify(verifier);
    }

    private String signTokenRS256(JWTClaimsSet claimsSet) throws JOSEException {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        RSASSASigner signer = new RSASSASigner(keyPair.getPrivate());
        jwsObject.sign(signer);
        return jwsObject.serialize();
    }
}
