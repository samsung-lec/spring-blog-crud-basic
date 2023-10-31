package shop.mtcoding.springblogriver.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.springblogriver._core.auth.SessionUser;
import shop.mtcoding.springblogriver._core.util.ApiUtil;
import shop.mtcoding.springblogriver.user.User;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO requestDTO, @SessionUser User sessionUser){
        ReplyResponse.DTO responseDTO = replyService.댓글쓰기(requestDTO, sessionUser);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }
}
