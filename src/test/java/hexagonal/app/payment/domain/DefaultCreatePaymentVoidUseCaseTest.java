package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory.CreatePaymentUseCase;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static hexagonal.app.payment.domain.PaymentUseCaseBuilder.aCreatePaymentUseCase;
import static org.joda.money.CurrencyUnit.USD;

class DefaultCreatePaymentVoidUseCaseTest {

    @Test
    void shouldCreatePayment() {
        final CreatePaymentUseCase useCase = aCreatePaymentUseCase()
                .withFixedIdentity()
                .withSuccessReservation()
                .expectingOnePublishedEvent(givenPaymentCreatedEventOfFiveUSD());

        final Basket basket = givenBasketWithBanana();
        final CreatePaymentCommand command = new CreatePaymentCommand(basket);

        useCase.execute(command);
    }

    @Test
    void shouldNotCreatePaymentIfReservationFails() {
        final CreatePaymentUseCase useCase = aCreatePaymentUseCase()
                .withFixedIdentity()
                .withRejectedReservation()
                .expectingZeroPublishedEvents();

        final Basket basket = givenBasketWithBanana();
        final CreatePaymentCommand command = new CreatePaymentCommand(basket);

        useCase.execute(command);
    }

    private static Basket givenBasketWithBanana() {
        return new Basket(Set.of(new Product("Banana", Money.of(USD, 0.5))));
    }

    private static PaymentCreatedEvent givenPaymentCreatedEventOfFiveUSD() {
        return new PaymentCreatedEvent(givenPaymentIdentity(), Money.of(USD, 0.5));
    }

    private static PaymentId givenPaymentIdentity() {
        return new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16");
    }
}