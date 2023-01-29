package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driver.UseCase;
import hexagonal.app.shared.SelfValidatingEventPublisherPort;

/**
 * Decorator which calls {@link SelfValidatingEventPublisherPort} to ensure all
 * events were sent
 */
public record PublishedEventsEnforcer(UseCase delegate,
                                      SelfValidatingEventPublisherPort eventPublisherPort) implements UseCase {
    @Override
    public void execute() {
        delegate.execute();
        eventPublisherPort.ensureAllEventsWereSent();
    }
}
