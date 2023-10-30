package shop.mtcoding.springblogriver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.springblogriver._core.auth.SessionUser;
import shop.mtcoding.springblogriver._core.util.ApiUtil;
import shop.mtcoding.springblogriver.user.User;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> join(@RequestBody PostRequest.SaveDTO requestDTO, @SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글쓰기(requestDTO,sessionUser)));
    }

    @GetMapping("/post")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글목록보기(page)));
    }
}
