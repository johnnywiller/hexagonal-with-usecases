package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driven.GetReservationPort;
import hexagonal.app.charge.domain.port.driven.MakeChargePort;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory;
import hexagonal.app.shared.EventPublisherPort;
import org.springframework.context.annotation.Bean;

public class ChargeDomainConfiguration {

    @Bean
    ChargeUseCaseFactory createChargeUseCaseFactory(EventPublisherPort publisher,
                                                    MakeChargePort makeChargePort,
                                                    GetReservationPort reservationPort) {
        return () -> new DefaultChargeUseCase(reservationPort, makeChargePort, publisher);
    }
}
