package willer.app.domain;

public interface EventPublisherPort {
    void publish(DomainEvent event);
}
