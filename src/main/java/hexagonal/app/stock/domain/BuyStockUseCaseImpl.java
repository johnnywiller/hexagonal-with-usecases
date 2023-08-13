package hexagonal.app.stock.domain;

import hexagonal.app.stock.domain.port.driven.EventPublisher;
import hexagonal.app.stock.domain.port.driven.FindStockPort;
import hexagonal.app.stock.domain.port.driven.FindUserPort;
import hexagonal.app.stock.domain.port.driven.SaveUserPort;
import hexagonal.app.stock.domain.port.driver.BuyStockUseCase;

public class BuyStockUseCaseImpl implements BuyStockUseCase {

    private final FindUserPort findUserPort;
    private final SaveUserPort saveUserPort;
    private final FindStockPort findStockPort;
    private final EventPublisher publisher;

    public BuyStockUseCaseImpl(FindUserPort findUserPort,
                               SaveUserPort saveUserPort,
                               FindStockPort findStockPort,
                               EventPublisher publisher) {
        this.findUserPort = findUserPort;
        this.saveUserPort = saveUserPort;
        this.findStockPort = findStockPort;
        this.publisher = publisher;
    }

    @Override
    public void buyStock(String userId, String tickerSymbol, int numberOfShares) {
        User user = findUserPort.byId(userId);
        Stock stock = findStockPort.byTickerSymbol(tickerSymbol);
        double totalCost = stock.currentPrice() * numberOfShares;

        if (user.accountBalance() < totalCost) {
            var balanceEvent = new InsufficientBalanceEvent(userId, tickerSymbol, numberOfShares, totalCost);
            publisher.publish(balanceEvent);
            throw new RuntimeException("Not enough funds to buy the stock");
        }

        User updatedUser = user.withBalance(user.accountBalance() - totalCost);
        saveUserPort.update(updatedUser);
        publisher.publish(new StockPurchaseIntentEvent(userId, tickerSymbol, numberOfShares));
    }
}
