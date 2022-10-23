package hexagonal.app.payment.domain;

import org.joda.money.Money;

import java.util.Set;

import static org.joda.money.CurrencyUnit.USD;

public record Basket(Set<Product> products) {
    Money total() {
        return products().stream()
                .map(Product::value)
                .reduce(Money.zero(USD),
                        Money::plus);
    }
}
