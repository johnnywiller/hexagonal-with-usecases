package hexagonal.app.charge.domain.port.driven;

import hexagonal.app.charge.domain.Reservation;
import hexagonal.app.payment.domain.PaymentId;

public interface GetReservationPort {

    Reservation byPaymentId(PaymentId paymentId);
}
