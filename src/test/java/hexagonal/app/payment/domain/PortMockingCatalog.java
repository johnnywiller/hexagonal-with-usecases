package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;

public interface PortMockingCatalog {

    interface EventPublisherPortMock<NEXT_STEP> {
        NEXT_STEP withPublisher(EventPublisherPort publisher);

        default NEXT_STEP expectingZeroPublishedEvents() {
            return withPublisher(expectingZeroEventsMock());
        }

        default NEXT_STEP expectingOnePublishedEvent(PaymentCreatedEvent paymentCreatedEvent) {
            final EventPublisherPort mock = event -> {
                if (!event.equals(paymentCreatedEvent))
                    throw new RuntimeException("Invalid event, the expected is " + paymentCreatedEvent);
            };
            return withPublisher(mock);
        }

        private static EventPublisherPort expectingZeroEventsMock() {
            final EventPublisherPort mock = event -> {
                throw new RuntimeException("Event not expected");
            };
            return mock;
        }
    }

    interface PaymentIdentityPortMock<NEXT_STEP> {
        NEXT_STEP withPaymentIdentity(PaymentIdentityPort paymentIdentity);

        default NEXT_STEP withFixedIdentity() {
            PaymentIdentityPort paymentIdentity = () -> new PaymentId("c473159b-d25b-4068-af1e-60cd71d91c16");
            return withPaymentIdentity(paymentIdentity);
        }
    }
}
