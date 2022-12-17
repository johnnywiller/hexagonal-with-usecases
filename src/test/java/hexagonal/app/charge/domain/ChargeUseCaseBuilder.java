package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.ChargePortMockingCatalog.MakeChargePortMock;
import hexagonal.app.charge.domain.ChargePortMockingCatalog.ReservationPortMock;
import hexagonal.app.charge.domain.port.driven.GetReservationPort;
import hexagonal.app.charge.domain.port.driven.MakeChargePort;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory.ChargeUseCase;
import hexagonal.app.shared.EventPublisherPort;
import hexagonal.app.shared.PublishedEventsEnforcer;
import hexagonal.app.shared.SharedPortMockingCatalog.EventPublisherPortMock;

public interface ChargeUseCaseBuilder extends
        ReservationPortMock<
                MakeChargePortMock<
                        EventPublisherPortMock<
                                ChargeUseCase>>> {

    ChargeDomainConfiguration DOMAIN_CONFIGURATION = new ChargeDomainConfiguration();

    static ChargeUseCaseBuilder aChargeUseCase() {
        return reservation -> charge -> publisher ->
                makeUseCase(reservation, charge, publisher);
    }

    private static ChargeUseCase makeUseCase(GetReservationPort reservationPort,
                                             MakeChargePort makeChargePort,
                                             EventPublisherPort publisher) {
        final ChargeUseCase useCase = DOMAIN_CONFIGURATION
                .createChargeUseCaseFactory(
                        publisher,
                        makeChargePort,
                        reservationPort)
                .useCase();

        /*
         not ideal to create this double layer of ChargeUseCase
         but necessary since a
         UseCase<ChargeCommand> is not the same as a ChargeUseCase,
         avoiding this double layer would involve some sort of AOP/proxy
        */
        return new PublishedEventsEnforcer<>(useCase, publisher)::execute;
    }

}
