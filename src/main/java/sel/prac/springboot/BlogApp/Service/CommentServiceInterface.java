package sel.prac.springboot.BlogApp.Service;

import sel.prac.springboot.BlogApp.Entity.Comment;
import sel.prac.springboot.BlogApp.Payload.CommentDTO;

import java.util.List;
import java.util.Set;

public interface CommentServiceInterface {

    public CommentDTO createComment(long postId, CommentDTO commentDTO);
    public List<CommentDTO> getAllComments(long postId);
    public CommentDTO getCommentsById(long postId,long commentId);
    public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDto);
    public void deleteComment(long postId,long commentId);

}
