package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driven.GetReservationPort;
import hexagonal.app.charge.domain.port.driver.ChargeCommand;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory;
import hexagonal.app.shared.EventPublisherPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultChargeUseCase implements ChargeUseCaseFactory.ChargeUseCase {

    private final GetReservationPort getReservationPort;
    private final EventPublisherPort eventPublisherPort;

    @Override
    public void execute(ChargeCommand command) {
        final Reservation reservation = getReservationPort.byPaymentId(command.paymentId());

        chargeUsingReservation(reservation);

        final ChargeCompletedEvent chargeCompletedEvent = new ChargeCompletedEvent(reservation.payment());
        eventPublisherPort.publish(chargeCompletedEvent);
    }

    private ChargeResult chargeUsingReservation(Reservation reservation) {
        return new ChargeResult(reservation.payment());
    }
}
