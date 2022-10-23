package willer.app.domain;

import org.junit.jupiter.api.Test;

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
        final Basket basket = new Basket();
        final MakePaymentCommand command = new MakePaymentCommand(basket);

        useCase.execute(command);

        verify(mockPublisher).publish(givenPaymentCreatedEvent());


    }

    private static PaymentCreatedEvent givenPaymentCreatedEvent() {
        return new PaymentCreatedEvent(givenPaymentIdentity());
    }

    private static PaymentId givenPaymentIdentity() {
        return new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16");
    }
}