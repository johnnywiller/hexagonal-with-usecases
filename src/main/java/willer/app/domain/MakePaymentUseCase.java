package willer.app.domain;

import lombok.RequiredArgsConstructor;
import org.joda.money.Money;

@RequiredArgsConstructor
public class MakePaymentUseCase {

    private final EventPublisherPort eventPublisher;
    private final PaymentIdentityPort paymentIdentityPort;

    void execute(MakePaymentCommand command) {
        Money paymentAmount = command.basket().total();
        final PaymentCreatedEvent createdPayment = new PaymentCreatedEvent(
                paymentIdentityPort.nextIdentity(),
                paymentAmount);
        eventPublisher.publish(createdPayment);
    }

}
