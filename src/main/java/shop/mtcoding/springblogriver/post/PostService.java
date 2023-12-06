package shop.mtcoding.springblogriver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver._core.error.exception.Exception403;
import shop.mtcoding.springblogriver._core.error.exception.Exception404;
import shop.mtcoding.springblogriver.reply.Reply;
import shop.mtcoding.springblogriver.reply.ReplyRepository;
import shop.mtcoding.springblogriver.user.User;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public PostResponse.DTO 게시글쓰기(PostRequest.SaveDTO requestDTO, User sessionUser){
        Post postPS = postRepository.save(requestDTO.toEntity(sessionUser));
        return new PostResponse.DTO(postPS);
    }

    public PostResponse.PageDTO 게시글목록보기(int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> postPG = postRepository.mFindAll(pageable);
        return new PostResponse.PageDTO(postPG);
    }

    public PostResponse.DetailDTO 게시글상세보기(int id, int sessionUserId){
        // Post+User 조회
        Post postPS = postRepository.mFindById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));

        // Reply List 조회 (Paging 때문에 추가 조회)
        List<Reply> repliesPS = replyRepository.mFindAllByPostId(id);

        return new PostResponse.DetailDTO(postPS, repliesPS, sessionUserId);
    }

    @Transactional
    public PostResponse.DTO 게시글수정하기(int id, PostRequest.UpdateDTO requestDTO, int sessionUserId){
        Post postPS = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));

        if(postPS.getUser().getId() != sessionUserId) throw new Exception403("게시글을 수정할 권한이 없습니다");

        postPS.update(requestDTO.getTitle(), requestDTO.getContent());

        return new PostResponse.DTO(postPS);
    }

    @Transactional
    public void 게시글삭제하기(int id, int sessionUserId){
        Post postPS = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));
        if(postPS.getUser().getId() != sessionUserId) throw new Exception403("게시글을 삭제할 권한이 없습니다");
        postRepository.deleteById(id);
    }
}
