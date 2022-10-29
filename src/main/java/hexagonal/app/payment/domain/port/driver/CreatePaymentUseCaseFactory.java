package hexagonal.app.payment.domain.port.driver;

import hexagonal.app.payment.domain.CreatePaymentCommand;

public interface CreatePaymentUseCaseFactory {

    CreatePaymentUseCase useCase();

    interface CreatePaymentUseCase {
        void execute(CreatePaymentCommand command);
    }
}
