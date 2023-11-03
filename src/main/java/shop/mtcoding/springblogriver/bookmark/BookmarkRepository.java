package shop.mtcoding.springblogriver.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    @Query("select count(b) from Bookmark b where b.post.id = :postId")
    int mCount(@Param("postId") int postId);
}
