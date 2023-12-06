package sel.prac.springboot.BlogApp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sel.prac.springboot.BlogApp.Payload.CommentDTO;
import sel.prac.springboot.BlogApp.Service.Impl.CommentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentDTO> createComment(@PathVariable long postId,
                                                    @RequestBody CommentDTO comment){

        CommentDTO commentDTO=commentService.createComment(postId,comment);
        return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDTO> getAllComments(@PathVariable long postId){
        return commentService.getAllComments(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentsById(@PathVariable long postId,@PathVariable long commentId){

        CommentDTO commentDTO=commentService.getCommentsById(postId,commentId);

        return new ResponseEntity<>(commentDTO,HttpStatus.OK);
    }
    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComments(@PathVariable long postId,
                                     @PathVariable long commentId,
                                     @RequestBody CommentDTO comment){

        CommentDTO commentDTO=commentService.updateComment(postId,commentId,comment);
        return new ResponseEntity<>(commentDTO,HttpStatus.OK);

    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId,@PathVariable long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<String>("Comment deleted sussesfully",HttpStatus.OK);
    }
}
