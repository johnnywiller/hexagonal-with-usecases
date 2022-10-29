package hexagonal.app.payment.domain.port.driver;

public interface UseCase<T> {
    void execute(T command);
}

