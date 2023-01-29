package hexagonal.app.payment.domain.port.driver;

/**
 * Factory for usecases that create a payment
 */
public interface CreatePaymentUseCaseFactory {

    UseCase createPaymentUseCase();

}
