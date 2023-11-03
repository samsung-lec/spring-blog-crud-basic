package shop.mtcoding.springblogriver.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p join fetch p.user u")
    Page<Post> mFindAll(Pageable pageable);

    @Query("select p from Post p join fetch p.user u where p.id = :id")
    Optional<Post> mFindById(@Param("id") int id);

}
