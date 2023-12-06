package sel.prac.springboot.BlogApp.Payload;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import sel.prac.springboot.BlogApp.Entity.Post;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;
    private String name;
    private String comment_body;
    private Date created_date;
    private Date updated_date;

}
