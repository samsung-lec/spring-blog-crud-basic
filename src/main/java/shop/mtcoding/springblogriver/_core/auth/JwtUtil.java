package shop.mtcoding.springblogriver._core.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.mtcoding.springblogriver.user.User;

import java.time.Instant;

public class JwtUtil {

    public static String create(User user) {
        String jwt = JWT.create()
                .withSubject("metacoding")
                .withClaim("id", user.getId())
                .withExpiresAt(Instant.now().plusMillis(1000*60*60*24*7L))
                .sign(Algorithm.HMAC512("metacoding"));
        return jwt;
    }

    public static int verify(String jwt)
            throws SignatureVerificationException, TokenExpiredException {
        jwt = jwt.replace("Bearer ", "");

        // JWT를 검증한 후, 검증이 완료되면, header, payload를 base64로 복호화함.
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("metacoding"))
                .build().verify(jwt);
        return decodedJWT.getClaim("id").asInt();
    }
}
