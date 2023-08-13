package hexagonal.app.stock.domain;

import org.jmolecules.event.types.DomainEvent;

public record StockPurchaseIntentEvent(String userId,
                                       String tickerSymbol,
                                       int numberOfShares)
        implements DomainEvent {
}