package sel.prac.springboot.BlogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sel.prac.springboot.BlogApp.Entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByCategoryId(Long categoryId);
}
