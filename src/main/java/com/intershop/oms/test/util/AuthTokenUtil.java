package com.intershop.oms.test.util;

import com.intershop.oms.test.configuration.ConfigBuilder;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class AuthTokenUtil
{
    public static String getDefaultJWTForUser(String username)
    {
        String jwtSecret = ConfigBuilder.getDefault().jwtSecret()
                        .orElseThrow(() -> new RuntimeException("jwt-secret is not configured!"));
        Key key = new HmacKey(jwtSecret.getBytes(StandardCharsets.UTF_8));

        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.systemDefault());

        Date issueTime = Date.from(now.toInstant());
        Date expirationTime = Date.from(now.plusHours(1).toInstant());

        // create the claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Test");
        claims.setExpirationTime(NumericDate.fromMilliseconds(expirationTime.getTime()));
        claims.setIssuedAt(NumericDate.fromMilliseconds(issueTime.getTime()));
        claims.setSubject("Authentication");
        claims.setClaim("user", username);

        // a JWT is a JWS and/or a JWE with JSON claims as the payload.
        JsonWebSignature jws = new JsonWebSignature();

        // use JSON content of the JWT claims as payload
        jws.setPayload(claims.toJson());

        // sign JWT using the private key
        jws.setKey(key);

        // set the signature algorithm on the JWT/JWS that will integrity
        // protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);

        // https://tools.ietf.org/html/rfc7518#section-3.2 says that a key of
        // the same size as the hash output or larger must be used with the JWS
        // HMAC SHA-2 algorithms (i.e, 256 bits for "HS256", 384bits/"HS384", &
        // 512 bits/"HS512"). By default jose4j enforces the minimum key sizes
        // mandated by JWA/RFC 7518. To tell jose4j to relax the key length
        // requirement following property could be used.
        jws.setDoKeyValidation(false);

        // sign the JWS and produce the compact serialization or the complete
        // JWT/JWS representation, which is a string consisting of three dot
        // ('.') separated base64url-encoded parts in the form
        // Header.Payload.Signature
        try
        {
            return jws.getCompactSerialization();
        }
        catch(JoseException e)
        {
            throw new RuntimeException("error during creation of JWT", e);
        }
    }
}
