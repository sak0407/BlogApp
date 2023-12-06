package sel.prac.springboot.BlogApp.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogAPIException extends RuntimeException{

    private String message;
    private HttpStatus status;


}
