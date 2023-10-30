package shop.mtcoding.springblogriver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver.user.User;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResponse.SaveDTO 게시글쓰기(PostRequest.SaveDTO requestDTO, User sessionUser){
        Post postPS = postRepository.save(requestDTO.toEntity(sessionUser));
        return new PostResponse.SaveDTO(postPS);
    }

    public PostResponse.PageDTO 게시글목록보기(int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Post> postPG = postRepository.findAll(pageable);
        return new PostResponse.PageDTO(postPG);
    }

    public void 게시글상세보기(int id){

    }


    @Transactional
    public void 게시글수정하기(int id){

    }

    @Transactional
    public void 게시글삭제하기(int id){

    }
}
