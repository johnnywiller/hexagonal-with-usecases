package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.EventPublisherPort;

/**
 * Main purpose of this interface is to add a behaviour that ensures all events
 * were sent. Without it, the event publisher mock can only validate when an
 * event is actually sent, but if they were never sent it wouldn't catch up
 */
public interface SelfValidatingEventPublisherPort extends EventPublisherPort {

    /**
     * Tests if all events were sent
     */
    void ensureAllEventsWereSent();
}
