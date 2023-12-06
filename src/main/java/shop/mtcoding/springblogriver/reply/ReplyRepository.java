package shop.mtcoding.springblogriver.reply;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query("select r from Reply r join fetch r.user u where r.post.id = :postId order by r.id desc")
    List<Reply> mFindAllByPostId(@Param("postId") int postId);

    @Query("select r from Reply r join fetch r.user u where r.post.id = :postId order by r.id desc")
    Page<Reply> mFindAllByPostId(@Param("postId") int postId, Pageable pageable);
}
