package sel.prac.springboot.BlogApp.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sel.prac.springboot.BlogApp.Entity.Order;
import sel.prac.springboot.BlogApp.Entity.Payment;
import sel.prac.springboot.BlogApp.Exception.PaymentException;
import sel.prac.springboot.BlogApp.Payload.OrderRequestDTO;
import sel.prac.springboot.BlogApp.Payload.OrderResponseDTO;
import sel.prac.springboot.BlogApp.Repository.OrderRepository;
import sel.prac.springboot.BlogApp.Repository.PaymentRepository;
import sel.prac.springboot.BlogApp.Service.OrderService;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    @Transactional(rollbackFor = PaymentException.class)
    public OrderResponseDTO orderPlaced(OrderRequestDTO orderRequestDTO) {


        Order order=orderRequestDTO.getOrder();
        order.setStatus("INProgress");
        order.setOrderTrackingNumber(String.valueOf(UUID.randomUUID()));
        orderRepository.save(order);

        Payment payment=orderRequestDTO.getPayment();

        if(!payment.getType().equals("DEBIT")){
           throw new PaymentException("Payment card do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponseDTO orderResponseDTO=new OrderResponseDTO();

        orderResponseDTO.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponseDTO.setStatus(order.getStatus());
        orderResponseDTO.setMessage("Success");
        return orderResponseDTO;
    }
}
