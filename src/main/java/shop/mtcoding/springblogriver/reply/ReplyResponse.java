package shop.mtcoding.springblogriver.reply;

import shop.mtcoding.springblogriver.post.PostResponse;
import shop.mtcoding.springblogriver.user.UserResponse;

import java.time.format.DateTimeFormatter;

public class ReplyResponse {
    public record DTO(Integer id, String comment, String createdAt, UserResponse.DTO replyUser, PostResponse.DTO post) {
        public DTO(Reply reply) {
            this(reply.getId(), reply.getComment(), reply.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), new UserResponse.DTO(reply.getUser()), new PostResponse.DTO(reply.getPost()));
        }
    }
}
