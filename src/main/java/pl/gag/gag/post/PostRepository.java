package pl.gag.gag.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT s FROM Post s JOIN s.tags t WHERE t = LOWER(:tag)")
    List<Post> retrieveByTag(@Param("tag") String tag);


    @Query(value = "SELECT * FROM posts WHERE MATCH(title,description) "
            + "AGAINST (?1)", nativeQuery = true)
    public Page<Post> search(String keyword, Pageable pageable);

}
