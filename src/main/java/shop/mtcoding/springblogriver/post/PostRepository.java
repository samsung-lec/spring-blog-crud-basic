package shop.mtcoding.springblogriver.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // 애플리케이션에서 개수 만들기
    @Query("select p from Post p order by p.id desc")
    List<Post> mFindAll();

    @Query("select p from Post p where p.id = :id")
    Optional<Post> mFindById(@Param("id") int id);

}
