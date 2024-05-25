package hexagonal.app.charge.domain.port.driver;


import hexagonal.app.charge.domain.PaymentId;

public record ChargeCommand(PaymentId paymentId) {

}
