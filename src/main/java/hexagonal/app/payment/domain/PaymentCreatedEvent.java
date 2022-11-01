package hexagonal.app.payment.domain;

import org.joda.money.Money;

public record PaymentCreatedEvent(PaymentId paymentId,
                                  Money amount) implements DomainEvent {

}
