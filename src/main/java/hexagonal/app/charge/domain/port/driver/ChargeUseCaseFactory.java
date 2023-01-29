package hexagonal.app.charge.domain.port.driver;

public interface ChargeUseCaseFactory {

    ChargeUseCase useCase();

    interface ChargeUseCase extends ReusableUseCase<ChargeCommand> {

    }
}
