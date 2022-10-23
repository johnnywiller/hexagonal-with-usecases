package willer.app.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MakePaymentUseCase {

    private final EventPublisherPort eventPublisher;
    private final PaymentIdentityPort paymentIdentityPort;

    void execute(MakePaymentCommand command) {

        final PaymentCreatedEvent createdPayment = new PaymentCreatedEvent(paymentIdentityPort.nextIdentity());
        eventPublisher.publish(createdPayment);
    }
}
