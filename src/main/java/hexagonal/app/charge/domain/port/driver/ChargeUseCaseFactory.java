package hexagonal.app.charge.domain.port.driver;

import hexagonal.app.payment.domain.port.driver.UseCase;

public interface ChargeUseCaseFactory {

    ChargeUseCase useCase();

    interface ChargeUseCase extends UseCase<ChargeCommand> {

    }
}
