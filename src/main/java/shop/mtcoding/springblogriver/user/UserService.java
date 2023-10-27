package shop.mtcoding.springblogriver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver._core.error.exception.Exception401;
import shop.mtcoding.springblogriver._core.error.exception.Exception404;
import shop.mtcoding.springblogriver._core.auth.PasswordUtil;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse.DTO 회원가입(UserRequest.JoinDTO requestDTO) {
        String encPassword = PasswordUtil.encode(requestDTO.password());
        User userPS = userRepository.save(requestDTO.toEntity(encPassword));
        return new UserResponse.DTO(userPS);
    }

    public UserResponse.DTO 로그인(UserRequest.LoginDTO requestDTO) {
        User userPS = userRepository.findByUsername(requestDTO.username()).orElseThrow(
                ()-> new Exception401("아이디를 찾을 수 없습니다")
        );

        if(!PasswordUtil.verify(requestDTO.password(), userPS.getPassword())) throw new Exception401("패스워드가 일치하지 않습니다");
        return new UserResponse.DTO(userPS);
    }

    public List<UserResponse.DTO> 회원목록보기() {
        List<User> usersPS = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return usersPS.stream().map(UserResponse.DTO::new).toList(); // Java16
    }

    public UserResponse.DTO 회원정보보기(int id) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("id가 존재하지 않습니다 : "+id)
        );
        return new UserResponse.DTO(userPS);
    }

    @Transactional
    public UserResponse.DTO 패스워드수정(int id, UserRequest.PasswordUpdateDTO requestDTO) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("id가 존재하지 않습니다 : "+id)
        );
        userPS.updatePassword(requestDTO.password());
        return new UserResponse.DTO(userPS);
    }

    @Transactional
    public UserResponse.DTO 프로필사진수정(int id, UserRequest.ImgBase64UpdateDTO requestDTO) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("id가 존재하지 않습니다 : "+id)
        );
        userPS.updatePassword(requestDTO.imgBase64());
        return new UserResponse.DTO(userPS);
    }
}
