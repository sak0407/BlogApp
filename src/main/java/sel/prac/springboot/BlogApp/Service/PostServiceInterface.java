package sel.prac.springboot.BlogApp.Service;

import sel.prac.springboot.BlogApp.Payload.PostDTO;
import sel.prac.springboot.BlogApp.Payload.PostResponse;

import java.util.List;

public interface PostServiceInterface {

    public PostDTO createPost(PostDTO postDTO);
    public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);
    public PostDTO getPost(long id);
    public PostDTO updatePost(PostDTO postDTO,long id);
    public void deletePost(long id);

}
