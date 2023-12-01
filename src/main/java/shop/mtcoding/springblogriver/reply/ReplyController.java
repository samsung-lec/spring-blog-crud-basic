package shop.mtcoding.springblogriver.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.springblogriver._core.auth.SessionUser;
import shop.mtcoding.springblogriver._core.util.ApiUtil;
import shop.mtcoding.springblogriver.user.User;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO requestDTO, @SessionUser User sessionUser){
        ReplyResponse.DTO responseDTO = replyService.댓글쓰기(requestDTO, sessionUser);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }

    @GetMapping("/reply")
    public ResponseEntity<?> findAll(int postId, @RequestParam(defaultValue = "0") int page){
        ReplyResponse.PageDTO responseDTO = replyService.댓글목록(postId, page);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id, @SessionUser User sessionUser){
        replyService.댓글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
