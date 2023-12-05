package sel.prac.springboot.BlogApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sel.prac.springboot.BlogApp.Payload.PostDTO;
import sel.prac.springboot.BlogApp.Service.Impl.PostServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postService.getAllPost();
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){

        return new ResponseEntity<PostDTO>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long id){

        return ResponseEntity.ok(postService.getPost(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO,@PathVariable long id){

        PostDTO postDTOResponse=postService.updatePost(postDTO,id);
        return new ResponseEntity<>(postDTOResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<String>("Post Entry deleted successfully",HttpStatus.OK);
    }

}
