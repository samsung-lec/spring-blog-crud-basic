package shop.mtcoding.springblogriver.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.springblogriver._core.auth.JwtUtil;
import shop.mtcoding.springblogriver._core.auth.SessionUser;
import shop.mtcoding.springblogriver.user.User;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessagingTemplate messageTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat") // (출판 -> 브로커 /pub/chat)
    @SendTo("/sub/chat") // (브로커 -> 구독자 /sub/chat)
    // 클라이언트는 /sub/chat을 구독하면 된다.
    public Chat sendMessage(@Payload ChatRequest.SaveDTO requestDTO/*, @SessionUser User user*/) {


        Chat chatPS = chatService.send(requestDTO, User.builder().id(1).username("ssar").build());


        // TIP : 특정 유저에게 메시지 보내는법 (특정 유저에 PK 넣으면 됨)
        // 이때는 구독할때 /1/queue/pub 이런식으로 구독해야 함.
        // messageTemplate.convertAndSendToUser("1", "/sub/chat", chatPS);
        // messageTemplate.convertAndSendToUser("2", "/sub/chat", chatPS);
        return chatPS;
    }
}
