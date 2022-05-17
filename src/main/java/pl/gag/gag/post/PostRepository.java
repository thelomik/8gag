package pl.gag.gag.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "Select * from post INNER JOIN post_tags ON post.id = post_tags.post_id where  tags LIKE %?1%", nativeQuery = true)
    List<Post> search(String keyword);

//    title LIKE %?1% or description LIKE %?1% or

}
