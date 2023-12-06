package shop.mtcoding.springblogriver.bookmark;

import lombok.Data;
import shop.mtcoding.springblogriver.post.Post;
import shop.mtcoding.springblogriver.user.User;

public class BookmarkRequest {

    @Data
    public static class SaveDTO {
        private int postId;

        public Bookmark toEntity(User sessionUser, Post post){
            return Bookmark.builder()
                    .user(sessionUser)
                    .post(post)
                    .build();
        }
    }
}
