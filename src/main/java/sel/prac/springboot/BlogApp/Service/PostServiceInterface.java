package sel.prac.springboot.BlogApp.Service;

import sel.prac.springboot.BlogApp.Payload.PostDTO;

import java.util.List;

public interface PostServiceInterface {

    public PostDTO createPost(PostDTO postDTO);
    public List<PostDTO> getAllPost();
    public PostDTO getPost(long id);
    public PostDTO updatePost(PostDTO postDTO,long id);
    public void deletePost(long id);

}
