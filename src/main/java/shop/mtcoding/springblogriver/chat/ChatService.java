package shop.mtcoding.springblogriver.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver.user.User;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public Chat send(ChatRequest.SaveDTO requestDTO, User sessionUser){
        Chat chatPS = chatRepository.save(requestDTO.toEntity(sessionUser));
        return chatPS;
    }
}
