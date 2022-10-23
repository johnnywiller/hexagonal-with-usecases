package hexagonal.app.payment.domain;

public interface CreatePaymentUseCaseFactory {

    CreatePaymentUseCase useCase();

    interface CreatePaymentUseCase {
        void execute(CreatePaymentCommand command);
    }
}
