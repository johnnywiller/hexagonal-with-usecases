package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driver.ChargeCommand;
import hexagonal.app.charge.domain.port.driver.ChargeUseCaseFactory.ChargeUseCase;
import hexagonal.app.payment.domain.PaymentId;
import org.junit.jupiter.api.Test;

class DefaultChargeUseCaseTest {

    @Test
    void shouldCreateChargeIfReservationIsOk() {
        final ChargeUseCase useCase = ChargeUseCaseBuilder.aChargeUseCase()
                .withReservationOf100Euros()
                .expectingOnePublishedEvent(new ChargeCompletedEvent(new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16")));

        useCase.execute(new ChargeCommand(new PaymentId("some id")));
    }

    @Test
    void shouldSendCustomerEmailIfChargeFails() {
        ChargeUseCaseBuilder.aChargeUseCase()
                .withReservationOf100Euros()
                .withDeclinedChargeResult()
                .expecting
    }
}