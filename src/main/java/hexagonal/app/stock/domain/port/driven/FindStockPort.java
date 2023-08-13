package hexagonal.app.stock.domain.port.driven;

import hexagonal.app.stock.domain.Stock;

public interface FindStockPort {
    Stock findByTickerSymbol(String tickerSymbol);
    // ... other methods
}