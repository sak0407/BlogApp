package sel.prac.springboot.BlogApp.Payload;

import lombok.Getter;
import lombok.Setter;
import sel.prac.springboot.BlogApp.Entity.Order;
import sel.prac.springboot.BlogApp.Entity.Payment;


@Getter
@Setter
public class OrderRequestDTO {
    private Order order;
    private Payment payment;
}
