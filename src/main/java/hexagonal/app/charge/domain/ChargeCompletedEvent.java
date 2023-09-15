package hexagonal.app.charge.domain;

import hexagonal.app.shared.DomainEvent;

public record ChargeCompletedEvent(PaymentId paymentId) implements DomainEvent {
}
