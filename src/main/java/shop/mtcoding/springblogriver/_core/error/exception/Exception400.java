package shop.mtcoding.springblogriver._core.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.springblogriver._core.util.ApiUtil;


// 유효성 검사 실패, 잘못된 파라메터 요청
@Getter
public class Exception400 extends RuntimeException {

    public Exception400(String message) {
        super(message);
    }

    public ApiUtil<?> body(){
        return new ApiUtil<>(HttpStatus.BAD_REQUEST.value(), getMessage());
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}