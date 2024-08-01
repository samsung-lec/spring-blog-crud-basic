package shop.mtcoding.springblogriver._core.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.springblogriver._core.util.ApiUtil;


// 권한 없음
@Getter
public class Exception404 extends RuntimeException {
    public Exception404(String message) {
        super(message);
    }

    public ApiUtil<?> body(){
        return new ApiUtil<>(HttpStatus.NOT_FOUND.value(), getMessage());
    }

    public HttpStatus status(){
        return HttpStatus.NOT_FOUND;
    }
}