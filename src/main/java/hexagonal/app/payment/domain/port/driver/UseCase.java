package hexagonal.app.payment.domain.port.driver;

/**
 * Represents a classic UseCase where there's just a void, argument-less execute
 * method. The use case command is instead passed during UC construction.
 */
public interface UseCase {
    void execute();
}

