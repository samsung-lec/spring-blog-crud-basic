package shop.mtcoding.springblogriver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.springblogriver._core.util.ApiUtil;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<?> save(@RequestBody PostRequest.SaveDTO requestDTO) {
        return ResponseEntity.ok(new ApiUtil<>(postService.게시글쓰기(requestDTO)));
    }

    @GetMapping("/api/post")
    public ResponseEntity<?> findAll() {
        System.out.println("findAll 실행됨");
        return ResponseEntity.ok(new ApiUtil<>(postService.게시글목록보기()));
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(postService.게시글상세보기(id)));
    }

    @PutMapping("/api/post/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PostRequest.UpdateDTO requestDTO) {
        return ResponseEntity.ok(new ApiUtil<>(postService.게시글수정하기(id, requestDTO)));
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        postService.게시글삭제하기(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
