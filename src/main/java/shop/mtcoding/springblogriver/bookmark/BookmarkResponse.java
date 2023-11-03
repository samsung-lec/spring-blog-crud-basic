package shop.mtcoding.springblogriver.bookmark;

import java.time.format.DateTimeFormatter;

public class BookmarkResponse {
    public record DTO(Integer id, Integer postId, int userId, String createdAt) {
        public DTO(Bookmark bookmark) {
            this(
                    bookmark.getId(),
                    bookmark.getPost().getId(),
                    bookmark.getUser().getId(),
                    bookmark.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
            );
        }
    }
}
