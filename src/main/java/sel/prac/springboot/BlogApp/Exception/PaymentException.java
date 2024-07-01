package sel.prac.springboot.BlogApp.Exception;

public class PaymentException extends RuntimeException{
    public PaymentException(String message){
        super(message);
    }
}
