package sel.prac.springboot.BlogApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "COMMENT_BODY",nullable = false)
    private String comment_body;
    @Column(name = "CREATED_DATE")
    private Date created_date;
    @Column(name = "UPDATED_DATE")
    private Date updated_date;

}
