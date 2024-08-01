package shop.mtcoding.springblogriver._core.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.springblogriver._core.util.ApiUtil;

// 서버 에러
@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public ApiUtil<?> body(){
        return new ApiUtil<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), getMessage());
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
