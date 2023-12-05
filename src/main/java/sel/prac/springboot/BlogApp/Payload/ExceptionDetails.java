package sel.prac.springboot.BlogApp.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ExceptionDetails {
    private String message;
    private String errordetails;
    private Date timestamp;

}
