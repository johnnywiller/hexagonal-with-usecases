package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;

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
         * This mocked object checks the validity of passed {@link DomainEvent}
         * plus self validates if the method was ever called
         */
        private static SelfValidatingEventPublisherPort aMockWhichChecksCorrectArgumentIsPassed(
                PaymentCreatedEvent paymentCreatedEvent) {
            return new SelfValidatingEventPublisherPort() {
                boolean sent = false;

                @Override
                public void ensureAllEventsWereSent() {
                    if (!sent)
                        throw new RuntimeException("One event was expected to be sent = " + paymentCreatedEvent);
                }

                @Override
                public void publish(DomainEvent event) {
                    if (!event.equals(paymentCreatedEvent))
                        throw new RuntimeException("Invalid event, the expected is " + paymentCreatedEvent);
                    sent = true;
                }
            };
        }

        private static EventPublisherPort expectingZeroEventsMock() {
            return new SelfValidatingEventPublisherPort() {
                @Override
                public void ensureAllEventsWereSent() {
                }

                @Override
                public void publish(DomainEvent event) {
                    throw new RuntimeException("Event not expected = " + event);
                }
            };
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
