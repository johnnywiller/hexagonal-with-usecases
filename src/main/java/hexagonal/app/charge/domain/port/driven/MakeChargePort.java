package hexagonal.app.charge.domain.port.driven;

import hexagonal.app.charge.domain.ChargeResult;
import hexagonal.app.charge.domain.Reservation;

public interface MakeChargePort {

    ChargeResult charge(Reservation reservation);
}
