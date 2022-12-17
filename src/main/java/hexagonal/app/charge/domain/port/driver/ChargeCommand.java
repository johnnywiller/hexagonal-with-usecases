package hexagonal.app.charge.domain.port.driver;

import hexagonal.app.payment.domain.PaymentId;

public record ChargeCommand(PaymentId paymentId) {

}
