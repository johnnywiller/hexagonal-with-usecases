package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import org.mockito.Mockito;

import static org.mockito.Mockito.doAnswer;

public interface PortMockingCatalog {

    interface EventPublisherPortMock<NEXT_STEP> {
        NEXT_STEP withPublisher(EventPublisherPort publisher);

        default NEXT_STEP expectingZeroPublishedEvents() {
            return withPublisher(expectingZeroEventsMock());
        }

        default NEXT_STEP expectingOnePublishedEvent(PaymentCreatedEvent paymentCreatedEvent) {
            return withPublisher(expectingOneEventMock(paymentCreatedEvent));
        }

        private static EventPublisherPort expectingOneEventMock(PaymentCreatedEvent paymentCreatedEvent) {
            return aMockWhichChecksCorrectArgumentIsPassed(paymentCreatedEvent);
        }

        /**
         * Little mockito hack to validate the correctness of the passed event
         * since when() can't work fine with void methods. If the event is
         * correct we don't want to do anything
         */
        private static EventPublisherPort aMockWhichChecksCorrectArgumentIsPassed(PaymentCreatedEvent paymentCreatedEvent) {
            final EventPublisherPort mock = Mockito.mock(EventPublisherPort.class);
            doAnswer(invocation -> {
                        final DomainEvent event = invocation.getArgument(0);
                        if (!event.equals(paymentCreatedEvent))
                            throw new RuntimeException("Invalid event, the expected is " + paymentCreatedEvent);
                        return null;
                    }).when(mock)
                    .publish(Mockito.any());
            return mock;
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
