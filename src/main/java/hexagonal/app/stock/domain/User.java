package hexagonal.app.stock.domain;

public record User(String id, double accountBalance) {

    public User withBalance(double newBalance) {
        return new User(this.id, newBalance);
    }
}




