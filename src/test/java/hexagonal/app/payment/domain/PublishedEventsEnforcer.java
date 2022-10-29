package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory;

/**
 * Decorator which after performing the specified function, also calls
 * {@link SelfValidatingEventPublisherPort} to ensure all events were sent
 */
public class PublishedEventsEnforcer implements CreatePaymentUseCaseFactory.CreatePaymentUseCase {
    private CreatePaymentUseCaseFactory.CreatePaymentUseCase useCase;
    private final EventPublisherPort eventPublisherPort;

    public PublishedEventsEnforcer(CreatePaymentUseCaseFactory.CreatePaymentUseCase useCase,
                                   EventPublisherPort eventPublisherPort) {
        this.useCase = useCase;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void execute(CreatePaymentCommand command) {
        useCase.execute(command);
        ((SelfValidatingEventPublisherPort) eventPublisherPort).ensureAllEventsWereSent();
    }
}
