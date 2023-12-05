package sel.prac.springboot.BlogApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import sel.prac.springboot.BlogApp.Payload.ExceptionDetails;

import java.util.Date;

@ControllerAdvice
public class BlogCustomGlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){

        ExceptionDetails exceptionDetails=new ExceptionDetails(ex.getMessage(), request.getDescription(false),new Date());

        return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleAllGlobalException(Exception ex,WebRequest request){

        ExceptionDetails exceptionDetails=new ExceptionDetails((ex.getMessage()),request.getDescription(false),new Date());

        return new ResponseEntity<ExceptionDetails>(exceptionDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
