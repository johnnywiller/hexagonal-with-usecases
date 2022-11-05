package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;

public interface PaymentPortMockingCatalog {
    
    interface PaymentIdentityPortMock<NEXT_STEP> {
        NEXT_STEP withPaymentIdentity(PaymentIdentityPort paymentIdentity);

        default NEXT_STEP withFixedIdentity() {
            PaymentIdentityPort paymentIdentity = () -> new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16");
            return withPaymentIdentity(paymentIdentity);
        }
    }

    interface CardReservationPortMock<NEXT_STEP> {
        NEXT_STEP withCardReservation(CardReservationPort cardReservation);

        default NEXT_STEP withSuccessReservation() {
            return withCardReservation(CardReservationResult::success);
        }

        default NEXT_STEP withRejectedReservation() {
            return withCardReservation(CardReservationResult::rejected);
        }
    }
}
