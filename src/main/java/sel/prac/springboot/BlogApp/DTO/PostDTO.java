package sel.prac.springboot.BlogApp.DTO;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class PostDTO {
    private Long id;
    private String topic;
    private String description;
    private String content;
    private Date createdDate;
    private Date updateDate;



}
