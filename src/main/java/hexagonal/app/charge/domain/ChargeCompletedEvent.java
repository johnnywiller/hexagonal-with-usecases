package hexagonal.app.charge.domain;

import hexagonal.app.payment.domain.PaymentId;
import hexagonal.app.shared.DomainEvent;

public record ChargeCompletedEvent(PaymentId paymentId) implements DomainEvent {
}
