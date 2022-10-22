package willer.app.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MakePaymentUseCase {

    private final EventPublisherPort eventPublisher;

    void execute(MakePaymentCommand command) {

        eventPublisher.publish(new PaymentCreatedEvent());
    }
}
