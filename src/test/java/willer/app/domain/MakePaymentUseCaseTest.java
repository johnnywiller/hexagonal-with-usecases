package willer.app.domain;

import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.joda.money.CurrencyUnit.USD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MakePaymentUseCaseTest {

    @Test
    void shouldCreatePayment() {
        EventPublisherPort mockPublisher = mock(EventPublisherPort.class);
        PaymentIdentityPort mockPaymentIdentity = mock(PaymentIdentityPort.class);
        when(mockPaymentIdentity.nextIdentity()).thenReturn(givenPaymentIdentity());

        final MakePaymentUseCase useCase = new MakePaymentUseCase(mockPublisher, mockPaymentIdentity);
        final Basket basket = givenBasketWithBanana();
        final MakePaymentCommand command = new MakePaymentCommand(basket);

        useCase.execute(command);

        verify(mockPublisher).publish(givenPaymentCreatedEvent());


    }

    private static Basket givenBasketWithBanana() {
        return new Basket(Set.of(new Product("Banana", Money.of(USD, 0.5))));
    }

    private static PaymentCreatedEvent givenPaymentCreatedEvent() {
        return new PaymentCreatedEvent(givenPaymentIdentity(), Money.of(USD, 0.5));
    }

    private static PaymentId givenPaymentIdentity() {
        return new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16");
    }
}