package shop.mtcoding.springblogriver.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.springblogriver._core.auth.JwtUtil;
import shop.mtcoding.springblogriver._core.auth.SessionUser;
import shop.mtcoding.springblogriver._core.error.exception.Exception400;
import shop.mtcoding.springblogriver._core.error.exception.Exception401;
import shop.mtcoding.springblogriver._core.error.exception.Exception403;
import shop.mtcoding.springblogriver._core.util.ApiUtil;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/init/user")
    public ResponseEntity<?> initUser() {
        return ResponseEntity.ok(ApiUtil.success(userService.회원목록보기()));
    }

    @GetMapping("/jwt/verify")
    public ResponseEntity<?> jwtVerify(HttpServletRequest request) {
        String jwt = request.getHeader("authorization");
        Optional.ofNullable(jwt).orElseThrow(() -> new Exception400("jwt를 전달해주세요"));
        try {
            int userId = JwtUtil.verify(jwt);
            return ResponseEntity.ok(ApiUtil.success(userService.회원정보보기(userId)));
        }catch (Exception e){
            throw new Exception401("jwt가 유효하지 않습니다 : "+e.getMessage());
        }
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO requestDTO) {
        return ResponseEntity.ok(ApiUtil.success(userService.회원가입(requestDTO)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        UserResponse.LoginDTO responseDTO = userService.로그인(requestDTO);
        return ResponseEntity.ok().header("Authorization", responseDTO.jwt()).body(responseDTO);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> userinfo(@PathVariable Integer id, @SessionUser User sessionUser) {
        System.out.println("sessionUser : id : "+sessionUser.getId());
        if (sessionUser.getId() != id) {
            throw new Exception403("해당 정보에 접근할 권한이 없습니다 : "+id);
        }
        return ResponseEntity.ok(ApiUtil.success(userService.회원정보보기(id)));
    }


    @PutMapping("/user/{id}/password")
    public ResponseEntity<?> userPasswordUpdate(@PathVariable Integer id, @RequestBody UserRequest.PasswordUpdateDTO requestDTO, @SessionUser User sessionUser) {
        if (sessionUser.getId() != id) {
            throw new Exception403("해당 정보를 수정할 권한이 없습니다 : "+id);
        }
        return ResponseEntity.ok(ApiUtil.success(userService.패스워드수정(id, requestDTO)));
    }

    @PutMapping("/user/{id}/imgBase64")
    public ResponseEntity<?> userPasswordUpdate(@PathVariable Integer id, @RequestBody UserRequest.ImgBase64UpdateDTO requestDTO, @SessionUser User sessionUser) {
        if (sessionUser.getId() != id) {
            throw new Exception403("해당 정보를 수정할 권한이 없습니다 : "+id);
        }
        return ResponseEntity.ok(ApiUtil.success(userService.프로필사진수정(id, requestDTO)));
    }
}
