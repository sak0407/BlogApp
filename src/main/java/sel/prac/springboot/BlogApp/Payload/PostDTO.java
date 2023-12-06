package sel.prac.springboot.BlogApp.Payload;

import lombok.Data;
import sel.prac.springboot.BlogApp.Entity.Comment;

import java.util.Date;
import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String topic;
    private String description;
    private String content;
    private Set<CommentDTO> comment;
    private Date createdDate;
    private Date updateDate;
}
