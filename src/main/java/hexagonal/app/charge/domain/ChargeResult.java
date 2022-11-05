package hexagonal.app.charge.domain;

import hexagonal.app.payment.domain.PaymentId;

public record ChargeResult(PaymentId paymentId) {
}
