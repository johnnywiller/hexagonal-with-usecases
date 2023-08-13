package hexagonal.app.stock.domain.port.driven;

import hexagonal.app.stock.domain.Stock;

public interface FindStockPort {
    Stock byTickerSymbol(String tickerSymbol);
    // ... other methods
}