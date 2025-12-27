package hexagonal.app.stock.domain;

import org.jmolecules.event.types.DomainEvent;

public record InsufficientBalanceEvent(String userId,
                                       String tickerSymbol,
                                       int numberOfShares,
                                       double attemptedPurchaseAmount)
        implements DomainEvent {}


