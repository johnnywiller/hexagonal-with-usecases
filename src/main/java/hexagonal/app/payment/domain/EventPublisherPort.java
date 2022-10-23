package hexagonal.app.payment.domain;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}
