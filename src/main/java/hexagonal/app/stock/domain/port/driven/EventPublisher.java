package hexagonal.app.stock.domain.port.driven;

import org.jmolecules.event.annotation.DomainEventPublisher;
import org.jmolecules.event.types.DomainEvent;

public interface EventPublisher {

    @DomainEventPublisher
    void publish(DomainEvent event);
}
