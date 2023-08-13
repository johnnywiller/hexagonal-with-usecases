package hexagonal.app.stock.domain.port.driven;

import hexagonal.app.stock.domain.InsufficientBalanceEvent;

public interface EventPublisher {

    void publish(InsufficientBalanceEvent balanceEvent);
}
