package shop.mtcoding.springblogriver.bookmark;

import shop.mtcoding.springblogriver.post.Post;
import shop.mtcoding.springblogriver.user.User;

public class BookmarkRequest {
    public record SaveDTO(int postId){
        public Bookmark toEntity(User sessionUser, Post post){
            return Bookmark.builder()
                    .user(sessionUser)
                    .post(post)
                    .build();
        }
    }
}
