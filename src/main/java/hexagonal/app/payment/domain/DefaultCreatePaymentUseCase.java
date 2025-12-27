package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.UseCase;
import hexagonal.app.shared.EventPublisherPort;
import org.joda.money.Money;

public class DefaultCreatePaymentUseCase implements UseCase {

    EventPublisherPort eventPublisher;
    PaymentIdentityPort paymentIdentityPort;
    CardReservationPort cardReservationPort;
    CreatePaymentCommand command;

    public DefaultCreatePaymentUseCase(EventPublisherPort eventPublisher,
                                       PaymentIdentityPort paymentIdentityPort,
                                       CardReservationPort cardReservationPort,
                                       CreatePaymentCommand command) {
        this.eventPublisher = eventPublisher;
        this.paymentIdentityPort = paymentIdentityPort;
        this.cardReservationPort = cardReservationPort;
        this.command = command;
    }

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
