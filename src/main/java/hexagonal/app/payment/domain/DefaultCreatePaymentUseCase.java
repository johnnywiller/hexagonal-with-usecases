package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory.CreatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;

@RequiredArgsConstructor
public class DefaultCreatePaymentUseCase implements CreatePaymentUseCase {

    private final EventPublisherPort eventPublisher;
    private final PaymentIdentityPort paymentIdentityPort;
    private final CardReservationPort cardReservationPort;

    @Override
    public void execute(CreatePaymentCommand command) {
        Money paymentAmount = command.basket().total();
        final PaymentCreatedEvent createdPayment = new PaymentCreatedEvent(
                paymentIdentityPort.nextIdentity(),
                paymentAmount);

        CardReservationResult reservationResult = cardReservationPort.reserveAmount();

        if (reservationResult.isSuccessful()) {
            eventPublisher.publish(createdPayment);
        }
    }



}
