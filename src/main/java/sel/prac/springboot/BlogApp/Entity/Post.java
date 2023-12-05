package sel.prac.springboot.BlogApp.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TOPIC",unique = true,nullable = false)
    private String topic;
    @Column(name = "DESCRIPTION",nullable = false)
    private String description;
    @Column(name = "CONTENT",nullable = false)
    private String content;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updateDate;

}
