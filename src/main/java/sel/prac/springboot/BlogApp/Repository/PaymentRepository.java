package sel.prac.springboot.BlogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sel.prac.springboot.BlogApp.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
