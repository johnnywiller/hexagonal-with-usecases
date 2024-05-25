package hexagonal.app.charge.domain.port.driven;

import hexagonal.app.charge.domain.PaymentId;
import hexagonal.app.charge.domain.Reservation;

public interface GetReservationPort {

    Reservation byPaymentId(PaymentId paymentId);
}
