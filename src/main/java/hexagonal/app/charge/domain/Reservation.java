package hexagonal.app.charge.domain;

import hexagonal.app.payment.domain.PaymentId;
import org.joda.money.Money;

public record Reservation(PaymentId payment, Money amount, String tokenFromBank) {
}
