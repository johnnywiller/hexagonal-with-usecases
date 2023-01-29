package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.UseCase;
import hexagonal.app.shared.EventPublisherPort;
import lombok.Value;
import org.joda.money.Money;

@Value
public class DefaultCreatePaymentUseCase implements UseCase {

    EventPublisherPort eventPublisher;
    PaymentIdentityPort paymentIdentityPort;
    CardReservationPort cardReservationPort;
    CreatePaymentCommand command;

    @Override
    public void execute() {
        Money paymentAmount = command.basket().total();
        final PaymentCreatedEvent createdPayment = new PaymentCreatedEvent(
                paymentIdentityPort.nextIdentity(),
                paymentAmount);

        CardReservationResult reservationResult =
                cardReservationPort.reserveAmount();

        if (reservationResult.isSuccessful()) {
            eventPublisher.publish(createdPayment);
        }
    }
}
