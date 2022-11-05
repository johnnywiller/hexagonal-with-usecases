package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driver.ChargeCommand;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory.ChargeUseCase;
import hexagonal.app.payment.domain.PaymentId;
import org.junit.jupiter.api.Test;

class DefaultChargeUseCaseTest {


    @Test
    void shouldCreateCharge() {
        final ChargeUseCase useCase = ChargeUseCaseBuilder.aChargeUseCase()
                .withReservationOf100Euros()
                .expectingOnePublishedEvent(new ChargeCompletedEvent(new PaymentId("some id")));

        useCase.execute(new ChargeCommand(new PaymentId("some id")));
    }
}