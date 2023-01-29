package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driver.ReusableUseCase;
import hexagonal.app.shared.EventPublisherPort;
import hexagonal.app.shared.SelfValidatingEventPublisherPort;

/**
 * Decorator which calls
 * {@link SelfValidatingEventPublisherPort} to ensure all events were sent
 */
public class ReusableUseCasePublishedEventsEnforcer<T> implements ReusableUseCase<T> {
    private final ReusableUseCase<T> delegate;
    private final EventPublisherPort eventPublisherPort;

    public ReusableUseCasePublishedEventsEnforcer(ReusableUseCase<T> delegate,
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
