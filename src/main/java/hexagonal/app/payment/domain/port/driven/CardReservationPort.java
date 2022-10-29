package hexagonal.app.payment.domain.port.driven;

import hexagonal.app.payment.domain.CardReservationResult;

public interface CardReservationPort {

    CardReservationResult reserveAmount();
}
