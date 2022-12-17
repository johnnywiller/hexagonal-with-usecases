package hexagonal.app.charge.domain;

import hexagonal.app.charge.domain.port.driven.GetReservationPort;
import hexagonal.app.charge.domain.port.driven.MakeChargePort;
import hexagonal.app.payment.domain.PaymentId;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public interface ChargePortMockingCatalog {

    interface ReservationPortMock<NEXT_STEP> {
        NEXT_STEP withReservation(GetReservationPort getReservation);

        default NEXT_STEP withReservationOf100Euros() {
            return withReservation(paymentId -> new Reservation(
                    new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16"),
                    Money.of(CurrencyUnit.EUR, 100),
                    "token from bank"));
        }
    }

    interface MakeChargePortMock<NEXT_STEP> {
        NEXT_STEP withChargeResult(MakeChargePort makeCharge);

        default NEXT_STEP withDeclinedCharge() {
            return withChargeResult(reservation -> ChargeResult.rejected());
        }
        default NEXT_STEP withSuccessfulCharge() {
            return withChargeResult(reservation -> ChargeResult.success());
        }
    }

}
