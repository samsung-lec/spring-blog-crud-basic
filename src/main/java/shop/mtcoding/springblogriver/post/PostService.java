package shop.mtcoding.springblogriver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver._core.error.exception.Exception404;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResponse.DTO 게시글쓰기(PostRequest.SaveDTO requestDTO){
        Post postPS = postRepository.save(requestDTO.toEntity());
        return new PostResponse.DTO(postPS);
    }

    public List<PostResponse.DTO> 게시글목록보기(){
        List<Post> posts = postRepository.mFindAll();
        System.out.println(posts.size());
        return posts.stream().map(PostResponse.DTO::new).toList();
    }

    public PostResponse.DetailDTO 게시글상세보기(int id){
        // Post+User 조회
        Post post = postRepository.mFindById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));

        return new PostResponse.DetailDTO(post);
    }

    @Transactional
    public PostResponse.DTO 게시글수정하기(int id, PostRequest.UpdateDTO requestDTO){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));

        post.update(requestDTO.getTitle(), requestDTO.getContent());

        return new PostResponse.DTO(post);
    }

    @Transactional
    public void 게시글삭제하기(int id){
        postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));
        postRepository.deleteById(id);
    }
}
