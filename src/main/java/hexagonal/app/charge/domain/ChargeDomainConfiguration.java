package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driven.GetReservationPort;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory;
import hexagonal.app.shared.EventPublisherPort;

public class ChargeDomainConfiguration {
    ChargeUseCaseFactory createChargeUseCaseFactory(EventPublisherPort publisher,
                                                    GetReservationPort reservationPort) {
        return () -> new DefaultChargeUseCase(reservationPort, publisher);
    }
}
