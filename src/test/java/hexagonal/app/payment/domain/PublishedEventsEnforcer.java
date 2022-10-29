package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory;
import org.mockito.Mockito;

public class PublishedEventsEnforcer implements CreatePaymentUseCaseFactory.CreatePaymentUseCase {
    private CreatePaymentUseCaseFactory.CreatePaymentUseCase useCase;
    private final EventPublisherPort eventPublisherPort;

    public PublishedEventsEnforcer(CreatePaymentUseCaseFactory.CreatePaymentUseCase useCase, EventPublisherPort eventPublisherPort) {
        this.useCase = useCase;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void execute(CreatePaymentCommand command) {
        useCase.execute(command);
        Mockito.verify(eventPublisherPort).publish(Mockito.any());
    }
}
