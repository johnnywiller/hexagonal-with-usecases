package hexagonal.app.payment.domain;

import hexagonal.app.shared.DomainEvent;
import org.joda.money.Money;

public record PaymentCreatedEvent(PaymentId paymentId,
                                  Money amount) implements DomainEvent {

}
