package willer.app.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MakePaymentUseCaseTest {

    @Test
    void shouldCreatePayment() {
        EventPublisherPort mockPublisher = Mockito.mock(EventPublisherPort.class);
        final MakePaymentUseCase useCase = new MakePaymentUseCase(mockPublisher);
        final Basket basket = new Basket();
        final MakePaymentCommand command = new MakePaymentCommand(basket);

        useCase.execute(command);
        Mockito.verify(mockPublisher).publish(new PaymentCreatedEvent());


    }
}