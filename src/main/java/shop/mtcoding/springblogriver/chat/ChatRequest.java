package shop.mtcoding.springblogriver.chat;

import lombok.Data;
import shop.mtcoding.springblogriver.user.User;

public class ChatRequest {

    @Data
    public static class SaveDTO {
        private String message;

        Chat toEntity(User sessionUser){
            return Chat.builder()
                    .message(message)
                    .sender(sessionUser)
                    .build();
        }
    }

}
