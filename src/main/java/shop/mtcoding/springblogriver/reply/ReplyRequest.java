package shop.mtcoding.springblogriver.reply;

import shop.mtcoding.springblogriver.post.Post;
import shop.mtcoding.springblogriver.user.User;

public class ReplyRequest {

    record SaveDTO(String comment, int postId) {
        Reply toEntity(User sessionUser, Post post){
            return Reply.builder()
                    .comment(comment)
                    .user(sessionUser)
                    .post(post)
                    .build();
        }
    }
}
