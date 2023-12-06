package sel.prac.springboot.BlogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sel.prac.springboot.BlogApp.Entity.Comment;
import sel.prac.springboot.BlogApp.Payload.CommentDTO;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
}
