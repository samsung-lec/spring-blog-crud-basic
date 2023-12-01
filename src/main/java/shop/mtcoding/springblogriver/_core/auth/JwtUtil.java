package shop.mtcoding.springblogriver._core.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.mtcoding.springblogriver.user.User;

import java.time.Instant;

public class JwtUtil {

    public final static Long EXPIRATION_TIME = 1000*60*60*24*2L;
    public final static Long EXPIRATION_REFRESH_TIME = 1000*60*60*24*14L;

    private static String create(User user, Long expirationTime) {
        String jwt = JWT.create()
                .withSubject("metacoding")
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("imgUrl", user.getImgUrl())
                .withExpiresAt(Instant.now().plusMillis(expirationTime))
                .sign(Algorithm.HMAC512("metacoding"));
        return jwt;
    }

    public static String createdAccessToken(User user){
        return create(user, EXPIRATION_TIME);
    }

    public static String createdRefreshToken(User user){
        return create(user, EXPIRATION_REFRESH_TIME);
    }

    public static User verify(String jwt)
            throws SignatureVerificationException, TokenExpiredException {
        jwt = jwt.replace("Bearer ", "");

        // JWT를 검증한 후, 검증이 완료되면, header, payload를 base64로 복호화함.
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("metacoding"))
                .build().verify(jwt);

        int id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();
        String imgUrl = decodedJWT.getClaim("imgUrl").asString();

        return User.builder().id(id).username(username).imgUrl(imgUrl).build();
    }
}
