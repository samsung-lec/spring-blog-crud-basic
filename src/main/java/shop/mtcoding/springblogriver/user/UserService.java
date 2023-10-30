package shop.mtcoding.springblogriver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver._core.auth.JwtUtil;
import shop.mtcoding.springblogriver._core.error.exception.Exception401;
import shop.mtcoding.springblogriver._core.error.exception.Exception404;
import shop.mtcoding.springblogriver._core.auth.PasswordUtil;
import shop.mtcoding.springblogriver._core.util.Base64Util;
import shop.mtcoding.springblogriver._core.util.MyFileUtil;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse.DTO 회원가입(UserRequest.JoinDTO requestDTO) {
        // 1. 비밀번호 암호화
        String encPassword = PasswordUtil.encode(requestDTO.password());

        // 2. base64 -> file 저장
        String imgUrl = MyFileUtil.write(requestDTO.imgBase64());

        // 3. file 경로 가져와서 유저정보 + 사진경로 DB 저장
        User userPS = userRepository.save(requestDTO.toEntity(encPassword, imgUrl));
        return new UserResponse.DTO(userPS);
    }

    public UserResponse.LoginDTO 로그인(UserRequest.LoginDTO requestDTO) {
        // 1. 유저 인증
        User userPS = userRepository.findByUsername(requestDTO.username()).orElseThrow(
                ()-> new Exception401("아이디를 찾을 수 없습니다")
        );

        if(!PasswordUtil.verify(requestDTO.password(), userPS.getPassword())) throw new Exception401("패스워드가 일치하지 않습니다");

        // 2. jwt 생성
        String jwt = JwtUtil.create(userPS);

        // 3. Bearer 추가
        jwt = "Bearer "+jwt;

        return new UserResponse.LoginDTO(jwt, userPS);
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

        String encPassword = PasswordUtil.encode(requestDTO.password());


        userPS.updatePassword(encPassword);
        return new UserResponse.DTO(userPS);
    }

    @Transactional
    public UserResponse.DTO 프로필사진수정(int id, UserRequest.ImgBase64UpdateDTO requestDTO) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("id가 존재하지 않습니다 : "+id)
        );
        String imgUrl = MyFileUtil.write(requestDTO.imgBase64());
        userPS.updateImgUrl(imgUrl);
        return new UserResponse.DTO(userPS);
    }
}
