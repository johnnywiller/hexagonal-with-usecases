package hexagonal.app.stock.domain.port.driven;

import hexagonal.app.stock.domain.User;

public interface SaveUserPort {
    void update(User user);
    // ... other methods related to saving or updating users, if any
}
