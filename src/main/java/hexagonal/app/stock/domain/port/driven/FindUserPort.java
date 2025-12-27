package hexagonal.app.stock.domain.port.driven;

import hexagonal.app.stock.domain.User;

public interface FindUserPort {
    User byId(String id);
    // ... other methods related to finding users, if any
}

