package shop.mtcoding.springblogriver.reply;

import lombok.Data;
import shop.mtcoding.springblogriver.post.Post;
import shop.mtcoding.springblogriver.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO{
        private String comment;
        private int postId;

        Reply toEntity(User sessionUser, Post post){
            return Reply.builder()
                    .comment(comment)
                    .user(sessionUser)
                    .post(post)
                    .build();
        }
    }

}
