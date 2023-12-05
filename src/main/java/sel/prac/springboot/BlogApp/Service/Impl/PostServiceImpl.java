package sel.prac.springboot.BlogApp.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sel.prac.springboot.BlogApp.DTO.PostDTO;
import sel.prac.springboot.BlogApp.Entity.Post;
import sel.prac.springboot.BlogApp.Exception.ResourceNotFoundException;
import sel.prac.springboot.BlogApp.Repository.PostRepository;
import sel.prac.springboot.BlogApp.Service.PostServiceInterface;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostServiceInterface {

    @Autowired
    PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO) {


        //Converting DTO to Entity
        Post post=modelMapper.map(postDTO,Post.class);
        Post postResponse=postRepository.save(post);
        //Converting postResponse to PostDTO
        PostDTO postDTOResponse=modelMapper.map(postResponse,PostDTO.class);

        return postDTOResponse;
    }

    @Override
    public List<PostDTO> getAllPost() {

         List<Post> posts= postRepository.findAll();

         List<PostDTO> postDTOList=posts.stream().map(post ->
             modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

         return postDTOList;

    }

    @Override
    public PostDTO getPost(long id) {

        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));
        return modelMapper.map(post,PostDTO.class);


    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {

        //get post by id
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));

        post.setTopic(postDTO.getTopic());
        post.setDescription(postDTO.getContent());
        post.setContent(postDTO.getDescription());
        post.setUpdateDate(new Date());

        Post updatedPOst=postRepository.save(post);

        return modelMapper.map(updatedPOst,PostDTO.class);
    }

    @Override
    public void deletePost(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));
        postRepository.delete(post);
    }
}
