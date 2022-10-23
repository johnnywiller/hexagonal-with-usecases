package hexagonal.app.payment.infrastructure;

import hexagonal.app.payment.domain.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping
    public Payment getPayment() {
        return new Payment();
    }
}
