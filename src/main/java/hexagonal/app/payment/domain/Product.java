package hexagonal.app.payment.domain;

import org.joda.money.Money;

public record Product(String name, Money value) {
}
