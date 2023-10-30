package shop.mtcoding.springblogriver.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import shop.mtcoding.springblogriver.user.User;
import shop.mtcoding.springblogriver.user.UserRepository;


@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test(){
        // given
        User user = User.builder().id(3).username("love").password("1234").email("love@nate.com").build();
        userRepository.save(user);
        em.clear();

        // when
        User sessionUser = User.builder().id(3).build();
        PostRequest.SaveDTO requestDTO = new PostRequest.SaveDTO("제목3", "내용3");
        Post postPS = postRepository.save(requestDTO.toEntity(sessionUser)); // 비영속 User 조회 후 insert

        PostResponse.SaveDTO responseDTO = new PostResponse.SaveDTO(postPS);
        System.out.println(responseDTO);
    }

    @Test
    public void findAll_test() throws JsonProcessingException {
        User user = User.builder().id(3).username("love").password("1234").email("love@nate.com").build();
        userRepository.save(user);
        em.clear();

        // when
        PostRequest.SaveDTO requestDTO1 = new PostRequest.SaveDTO("제목3", "내용3");
        PostRequest.SaveDTO requestDTO2 = new PostRequest.SaveDTO("제목3", "내용3");
        PostRequest.SaveDTO requestDTO3 = new PostRequest.SaveDTO("제목3", "내용3");
        PostRequest.SaveDTO requestDTO4 = new PostRequest.SaveDTO("제목3", "내용3");
        postRepository.save(requestDTO1.toEntity(user)); // 비영속 User 조회 후 insert
        postRepository.save(requestDTO2.toEntity(user)); // 비영속 User 조회 후 insert
        postRepository.save(requestDTO3.toEntity(user)); // 비영속 User 조회 후 insert
        postRepository.save(requestDTO4.toEntity(user)); // 비영속 User 조회 후 insert
        em.clear();

        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postPG = postRepository.findAll(pageable);

    }
}
