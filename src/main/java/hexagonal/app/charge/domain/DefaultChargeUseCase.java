package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driven.GetReservationPort;
import hexagonal.app.charge.domain.port.driver.ChargeCommand;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultChargeUseCase implements ChargeUseCaseFactory.ChargeUseCase {

    private final GetReservationPort getReservationPort;

    @Override
    public void execute(ChargeCommand command) {
        final Reservation reservation = getReservationPort.byPaymentId(command.paymentId());


    }
}
