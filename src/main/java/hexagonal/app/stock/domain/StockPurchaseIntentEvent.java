package hexagonal.app.stock.domain;

public record StockPurchaseIntentEvent(String userId, String tickerSymbol, int numberOfShares) {}