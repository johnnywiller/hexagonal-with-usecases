package willer.app.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import willer.app.domain.Payment;

@RestController
public class PaymentController {

    @GetMapping
    public Payment getPayment() {
        return new Payment();
    }
}
