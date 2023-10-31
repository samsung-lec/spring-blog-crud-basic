package shop.mtcoding.springblogriver.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver._core.error.exception.Exception404;
import shop.mtcoding.springblogriver.post.Post;
import shop.mtcoding.springblogriver.post.PostRepository;
import shop.mtcoding.springblogriver.post.PostRequest;
import shop.mtcoding.springblogriver.user.User;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    // 응답시에 sessionUser 영속화 되서 나오는지 확인해보기
    @Transactional
    public ReplyResponse.DTO 댓글쓰기(ReplyRequest.SaveDTO requestDTO, User sessionUser) {
        Post postPS = postRepository.findById(requestDTO.postId())
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+requestDTO.postId()));
        Reply replyPS = replyRepository.save(requestDTO.toEntity(sessionUser, postPS));
        return new ReplyResponse.DTO(replyPS);
    }
}
