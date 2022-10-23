package hexagonal.app.payment.domain;

public interface PaymentIdentityPort {

    PaymentId nextIdentity();
}
