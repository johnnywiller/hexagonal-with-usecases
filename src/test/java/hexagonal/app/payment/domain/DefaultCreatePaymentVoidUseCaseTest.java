package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driver.UseCase;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static hexagonal.app.payment.domain.PaymentUseCaseBuilder.aCreatePaymentUseCase;
import static org.joda.money.CurrencyUnit.USD;

class DefaultCreatePaymentVoidUseCaseTest {

    @ParameterizedTest
    @MethodSource("givenBasketsAndCorrespondingTotals")
    void shouldCreatePayment(Basket basket, Money basketTotal) {
        final CreatePaymentCommand command = new CreatePaymentCommand(basket);
        final UseCase useCase = aCreatePaymentUseCase(command)
                .withFixedIdentity()
                .withSuccessReservation()
                .expectingOnePublishedEvent(givenPaymentCreatedEventOfAmount(basketTotal));

        useCase.execute();
    }

    private static Basket givenBasketWithMultipleFruits() {
        return new Basket(Set.of(
                new Product("Banana", Money.of(USD, 0.5)),
                new Product("Apple", Money.of(USD, 2.3)),
                new Product("Watermelon", Money.of(USD, 4.5)),
                new Product("Pineapple", Money.of(USD, 4))
        ));
    }

    private static Basket givenBasketWithBanana() {
        return new Basket(Set.of(new Product("Banana", Money.of(USD, 0.5))));
    }

    public static Stream<Arguments> givenBasketsAndCorrespondingTotals() {
        return Stream.of(
                Arguments.of(givenBasketWithBanana(), Money.of(USD, 0.5)),
                Arguments.of(givenBasketWithMultipleFruits(), Money.of(USD, 11.3))
        );
    }

    private static PaymentCreatedEvent givenPaymentCreatedEventOfAmount(Money amount) {
        return new PaymentCreatedEvent(givenPaymentIdentity(), amount);
    }

    @Test
    void shouldNotCreatePaymentIfReservationFails() {
        final CreatePaymentCommand command = new CreatePaymentCommand(givenBasketWithBanana());
        final UseCase useCase = aCreatePaymentUseCase(command)
                .withFixedIdentity()
                .withRejectedReservation()
                .expectingZeroPublishedEvents();

        useCase.execute();
    }

    private static PaymentCreatedEvent givenPaymentCreatedEventOfFiveUSD() {
        return new PaymentCreatedEvent(givenPaymentIdentity(), Money.of(USD, 0.5));
    }

    private static PaymentId givenPaymentIdentity() {
        return new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16");
    }
}