package shop.mtcoding.springblogriver._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import shop.mtcoding.springblogriver._core.auth.JwtUtil;
import shop.mtcoding.springblogriver._core.error.exception.Exception401;
import shop.mtcoding.springblogriver.user.User;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/blog-websocket")
                .setAllowedOrigins("*");
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new StompHandler());
//
//    }

    // 최초 연결시에만 인증하기가 필요없음. Jwt인가 필터 잘 작동함 (플러터에서는 - 테스트 필요)
//    public class StompHandler implements ChannelInterceptor {
//        @Override
//        public Message<?> preSend(Message<?> message, MessageChannel channel) {
//            StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//            if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//                String jwt = accessor.getFirstNativeHeader("Authorization").substring(7);
//                System.out.println("jwt : "+jwt);
//                JwtUtil.verify(jwt);
//            }
//
//            return message;
//        }
//    }


}



