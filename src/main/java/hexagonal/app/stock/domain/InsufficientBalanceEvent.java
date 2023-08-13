package hexagonal.app.stock.domain;

public record InsufficientBalanceEvent(String userId, String tickerSymbol, int numberOfShares, double attemptedPurchaseAmount) {}


