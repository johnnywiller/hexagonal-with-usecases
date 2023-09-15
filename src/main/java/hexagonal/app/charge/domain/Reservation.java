package hexagonal.app.charge.domain;

import org.joda.money.Money;

public record Reservation(PaymentId payment, Money amount, String tokenFromBank) {
}
