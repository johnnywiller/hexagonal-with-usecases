package willer.app.domain;

import lombok.Value;
import org.joda.money.Money;

@Value
public class PaymentCreatedEvent implements DomainEvent {

    PaymentId paymentId;
    Money amount;
}
