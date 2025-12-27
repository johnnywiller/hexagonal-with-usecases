package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driven.GetReservationPort;
import hexagonal.app.charge.domain.port.driven.MakeChargePort;
import hexagonal.app.charge.domain.port.driver.ChargeCommand;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory;
import hexagonal.app.shared.EventPublisherPort;

public class DefaultChargeUseCase implements ChargeUseCaseFactory.ChargeUseCase {

    private final GetReservationPort getReservationPort;
    private final MakeChargePort makeChargePort;
    private final EventPublisherPort eventPublisherPort;

    public DefaultChargeUseCase(GetReservationPort getReservationPort,
                                MakeChargePort makeChargePort,
                                EventPublisherPort eventPublisherPort) {
        this.getReservationPort = getReservationPort;
        this.makeChargePort = makeChargePort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void execute(ChargeCommand command) {
        final Reservation reservation = getReservationPort.byPaymentId(command.paymentId());

        ChargeResult chargeResult = chargeUsingReservation(reservation);

        if(chargeResult.isSuccessful()) {
            final ChargeCompletedEvent chargeCompletedEvent = new ChargeCompletedEvent(reservation.payment());
            eventPublisherPort.publish(chargeCompletedEvent);
        }
    }

    private ChargeResult chargeUsingReservation(Reservation reservation) {
        ChargeResult chargeResult = makeChargePort.charge(reservation);
        return chargeResult;
    }
}
