package shop.mtcoding.springblogriver.bookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.springblogriver._core.auth.SessionUser;
import shop.mtcoding.springblogriver._core.util.ApiUtil;
import shop.mtcoding.springblogriver.user.User;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity<?> bookmark(@RequestBody BookmarkRequest.SaveDTO requestDTO, @SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(bookmarkService.북마크(requestDTO, sessionUser)));
    }

    @DeleteMapping("/bookmark/{id}")
    public ResponseEntity<?> cancel(@PathVariable Integer id, @SessionUser User sessionUser) {
        bookmarkService.북마크취소(id, sessionUser.getId());
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
