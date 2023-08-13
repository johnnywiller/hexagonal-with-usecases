package hexagonal.app.stock.domain.port.driver;

public interface BuyStockUseCase {
    void buyStock(String userId, String tickerSymbol, int numberOfShares);
}
