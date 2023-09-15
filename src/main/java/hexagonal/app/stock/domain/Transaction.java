package hexagonal.app.stock.domain;

public record Transaction(String userId, String stockTicker, int numberOfShares, double purchasePrice) {}