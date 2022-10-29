package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driver.UseCase;

/**
 * Decorator which after performing the specified function, also calls
 * {@link SelfValidatingEventPublisherPort} to ensure all events were sent
 */
public class PublishedEventsEnforcer<T> implements UseCase<T> {
    private final UseCase<T> delegate;
    private final EventPublisherPort eventPublisherPort;

    public PublishedEventsEnforcer(UseCase<T> delegate,
                                   EventPublisherPort eventPublisherPort) {
        this.delegate = delegate;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void execute(T command) {
        delegate.execute(command);
        ((SelfValidatingEventPublisherPort) eventPublisherPort).ensureAllEventsWereSent();
    }
}
