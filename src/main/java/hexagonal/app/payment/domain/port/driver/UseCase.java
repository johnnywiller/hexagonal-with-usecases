package hexagonal.app.payment.domain.port.driver;

/**
 *
 * @param <T> the type of the command
 */
public interface UseCase<T> {
    void execute(T command);
}

