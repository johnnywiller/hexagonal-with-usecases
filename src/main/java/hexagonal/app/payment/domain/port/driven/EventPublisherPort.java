package hexagonal.app.payment.domain.port.driven;

import hexagonal.app.payment.domain.DomainEvent;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}
