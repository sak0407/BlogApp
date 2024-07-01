package sel.prac.springboot.BlogApp.Service;

import sel.prac.springboot.BlogApp.Payload.OrderRequestDTO;
import sel.prac.springboot.BlogApp.Payload.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO orderPlaced(OrderRequestDTO orderRequestDTO);
}
