package hexagonal.app.charge.domain.port.driver;

/**
 * Represents a UseCase that can be reused with multiple commands. In this style
 * of use case the data is not encapsulated within the UC object, but instead
 * just provided as argument
 *
 * @param <T> the type of the command
 */
public interface ReusableUseCase<T> {
    void execute(T command);
}

