package hexagonal.app.payment.domain.port.driven;

import hexagonal.app.payment.domain.PaymentId;

public interface PaymentIdentityPort {

    PaymentId nextIdentity();
}
