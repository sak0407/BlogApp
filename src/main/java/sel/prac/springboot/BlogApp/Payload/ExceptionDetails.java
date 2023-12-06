package sel.prac.springboot.BlogApp.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ExceptionDetails {
    private Date timestamp;
    private String message;
    private String errordetails;

}
