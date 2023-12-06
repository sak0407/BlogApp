package sel.prac.springboot.BlogApp.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sel.prac.springboot.BlogApp.Entity.Comment;
import sel.prac.springboot.BlogApp.Entity.Post;
import sel.prac.springboot.BlogApp.Exception.BlogAPIException;
import sel.prac.springboot.BlogApp.Exception.ResourceNotFoundException;
import sel.prac.springboot.BlogApp.Payload.CommentDTO;
import sel.prac.springboot.BlogApp.Repository.CommentRepository;
import sel.prac.springboot.BlogApp.Repository.PostRepository;
import sel.prac.springboot.BlogApp.Service.CommentServiceInterface;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentServiceInterface {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {

        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        Comment comment=mapToEntity(commentDTO);
        comment.setPost(post);
        comment.setCreated_date(new Date());
        comment.setUpdated_date(new Date());
        Comment updatedcoment=commentRepository.save(comment);
        return mapToDto(updatedcoment);

    }

    @Override
    public List<CommentDTO> getAllComments(long postId) {
        //get post

        List<Comment> allcomments =commentRepository.findByPostId(postId);

        return allcomments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentsById(long postId, long commentId) {
        //get post from post id

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","ID",postId));

        //get comment by comment id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","ID",commentId));

        //check comment id with post's postid
        if(!comment.getPost().getId().equals(post.getId())){
            //throw a checked exception
            throw new BlogAPIException("Comment ID not present in Post",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //return comment dto
        return mapToDto(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDto) {

        //get the post by postid
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        //get the comment from comment id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","ID",commentId));

        //if post id and comment id is relavant then
        if(!comment.getPost().getId().equals(post.getId())){
            //throw a checked exception
            throw new BlogAPIException("Comment ID not present in Post",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //set comment with comment dto
        comment.setName(commentDto.getName());
        comment.setComment_body((commentDto.getComment_body()));
        comment.setUpdated_date(new Date());

        //save to db
        Comment updatedDto=commentRepository.save(comment);

        return mapToDto(updatedDto) ;
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        //get the post by postid
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        //get the comment from comment id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","ID",commentId));

        //if post id and comment id is relavant then
        if(!comment.getPost().getId().equals(post.getId())){
            //throw a checked exception
            throw new BlogAPIException("Comment ID not present in Post",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        commentRepository.delete(comment);
    }

    private CommentDTO mapToDto(Comment comment){
        return  modelMapper.map(comment,CommentDTO.class);
    }
    private Comment mapToEntity(CommentDTO commentDTO){
        return  modelMapper.map(commentDTO,Comment.class);
    }
}
