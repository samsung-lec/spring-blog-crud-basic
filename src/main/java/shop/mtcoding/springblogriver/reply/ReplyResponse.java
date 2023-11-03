package shop.mtcoding.springblogriver.reply;

import org.springframework.data.domain.Page;
import shop.mtcoding.springblogriver.post.Post;
import shop.mtcoding.springblogriver.post.PostResponse;
import shop.mtcoding.springblogriver.user.User;
import shop.mtcoding.springblogriver.user.UserResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReplyResponse {

    public record DTO(Integer id, String comment, String createdAt, UserResponse.DTO replyUser) {
        public DTO(Reply reply) {
            this(reply.getId(), reply.getComment(), reply.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), new UserResponse.DTO(reply.getUser()));
        }
    }

    public record PageDTO(boolean isFirst, boolean isLast, int pageNumber, int size, int totalPage, List<DTO> replies) {
        public PageDTO(Page<Reply> replyPG){
            this(replyPG.isFirst(), replyPG.isLast(), replyPG.getNumber(), replyPG.getSize(), replyPG.getTotalPages(),
                    replyPG.getContent().stream().map(DTO::new).toList());
        }
    }
}
