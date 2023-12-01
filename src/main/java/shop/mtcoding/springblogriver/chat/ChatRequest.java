package shop.mtcoding.springblogriver.chat;

import shop.mtcoding.springblogriver.user.User;

public class ChatRequest {

    record SaveDTO(String message) {
        Chat toEntity(User sessionUser){
            return Chat.builder()
                    .message(message)
                    .sender(sessionUser)
                    .build();
        }
    }
}
