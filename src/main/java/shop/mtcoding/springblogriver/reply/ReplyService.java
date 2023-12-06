package shop.mtcoding.springblogriver.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver._core.error.exception.Exception403;
import shop.mtcoding.springblogriver._core.error.exception.Exception404;
import shop.mtcoding.springblogriver._core.error.exception.Exception500;
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
        Post postPS = postRepository.findById(requestDTO.getPostId())
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+requestDTO.getPostId()));
        Reply replyPS = replyRepository.save(requestDTO.toEntity(sessionUser, postPS));
        return new ReplyResponse.DTO(replyPS);
    }

    public ReplyResponse.PageDTO 댓글목록(int postId, int page) {
        Pageable pageable = PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "id"));
        Page<Reply> replyPG = replyRepository.mFindAllByPostId(postId, pageable);
        return new ReplyResponse.PageDTO(replyPG);
    }

    @Transactional
    public void 댓글삭제(int id, int sessionUserId) {
        Reply replyPS = replyRepository.findById(id).orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));
        if(replyPS.getUser().getId() != sessionUserId) throw new Exception403("댓글을 삭제할 권한이 없습니다");
        replyRepository.deleteById(id);
    }
}
