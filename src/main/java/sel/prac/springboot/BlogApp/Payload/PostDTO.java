package sel.prac.springboot.BlogApp.Payload;

import lombok.Data;

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
