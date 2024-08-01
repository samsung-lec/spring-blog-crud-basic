package shop.mtcoding.springblogriver._core.error.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.springblogriver._core.util.ApiUtil;


// 인증 안됨
@Getter
public class Exception401 extends RuntimeException {
    public Exception401(String message) {
        super(message);
    }

    public ApiUtil<?> body(){
        return new ApiUtil<>(HttpStatus.UNAUTHORIZED.value(), getMessage());
    }

    public HttpStatus status(){
        return HttpStatus.UNAUTHORIZED;
    }
}