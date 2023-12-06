package sel.prac.springboot.BlogApp.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sel.prac.springboot.BlogApp.Payload.PostDTO;
import sel.prac.springboot.BlogApp.Entity.Post;
import sel.prac.springboot.BlogApp.Exception.ResourceNotFoundException;
import sel.prac.springboot.BlogApp.Payload.PostResponse;
import sel.prac.springboot.BlogApp.Repository.PostRepository;
import sel.prac.springboot.BlogApp.Service.PostServiceInterface;

import java.util.Date;
import java.util.List;
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
        Post post=mapToEntity(postDTO);
        post.setCreatedDate(new Date());
        post.setUpdateDate(new Date());
        Post postResponse=postRepository.save(post);
        //Converting postResponse to PostDTO
        PostDTO postDTOResponse=mapToDto(postResponse);

        return postDTOResponse;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();


        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> page = postRepository.findAll(pageable);

        List<Post> posts = page.getContent();
        List<PostDTO> postDTOList=posts.stream().map(post ->mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setContent(postDTOList);
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setLast(page.isLast());
        postResponse.setFirst(page.isFirst());

        return postResponse;

    }

    @Override
    public PostDTO getPost(long id) {

        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));
        return mapToDto(post);


    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {

        //get post by id
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));

        post.setTopic(postDTO.getTopic());
        post.setDescription(postDTO.getContent());
        post.setContent(postDTO.getDescription());
        post.setUpdateDate(new Date());

        Post updatedPost=postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));
        postRepository.delete(post);
    }

    private PostDTO mapToDto(Post post){
        return modelMapper.map(post,PostDTO.class);
    }
    private Post mapToEntity(PostDTO postDTO){
        return modelMapper.map(postDTO,Post.class);
    }
}
