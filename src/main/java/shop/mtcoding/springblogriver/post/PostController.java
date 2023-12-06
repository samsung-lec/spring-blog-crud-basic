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

    @GetMapping("/init/post")
    public ResponseEntity<?> initPost(@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글목록보기(page)));
    }

    @PostMapping("/api/post")
    public ResponseEntity<?> save(@RequestBody PostRequest.SaveDTO requestDTO, @SessionUser User sessionUser) {

        return ResponseEntity.ok(ApiUtil.success(postService.게시글쓰기(requestDTO, sessionUser)));
    }

    @GetMapping("/api/post")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") Integer page) {
        System.out.println("페이징 요청옴 : "+page);
        return ResponseEntity.ok(ApiUtil.success(postService.게시글목록보기(page)));
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id, @SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글상세보기(id, sessionUser.getId())));
    }

    @PutMapping("/api/post/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PostRequest.UpdateDTO requestDTO, @SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글수정하기(id, requestDTO, sessionUser.getId())));
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @SessionUser User sessionUser) {
        postService.게시글삭제하기(id, sessionUser.getId());
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
