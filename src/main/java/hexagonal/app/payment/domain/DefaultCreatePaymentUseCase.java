package hexagonal.app.payment.domain;

import org.joda.money.Money;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.UseCase;
import hexagonal.app.shared.EventPublisherPort;

public class DefaultCreatePaymentUseCase implements UseCase {

    private final EventPublisherPort eventPublisher;
    private final PaymentIdentityPort paymentIdentityPort;
    private final CardReservationPort cardReservationPort;
    private final CreatePaymentCommand command;

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
