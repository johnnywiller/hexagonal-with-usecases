package hexagonal.app.shared;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}
