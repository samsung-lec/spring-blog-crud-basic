package shop.mtcoding.springblogriver._core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ApiUtil {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, 200, null);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, status.value(), message);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiResult<T> {
        private boolean success;
        private T response;
        private int status;
        private String errorMessage;
    }
}