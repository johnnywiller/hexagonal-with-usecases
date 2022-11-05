package hexagonal.app.shared;

public interface SharedPortMockingCatalog {

    interface EventPublisherPortMock<NEXT_STEP> {
        NEXT_STEP withPublisher(EventPublisherPort publisher);

        default NEXT_STEP expectingZeroPublishedEvents() {
            return withPublisher(expectingZeroEventsMock());
        }

        default NEXT_STEP expectingOnePublishedEvent(DomainEvent expectedDomainEvent) {
            return withPublisher(expectingOneEventMock(expectedDomainEvent));
        }

        private static EventPublisherPort expectingOneEventMock(DomainEvent paymentCreatedEvent) {
            return aMockWhichChecksCorrectArgumentIsPassed(paymentCreatedEvent);
        }


        /**
         * This mocked object checks the validity of passed {@link DomainEvent}
         * plus self validates if the method was ever called
         */
        private static SelfValidatingEventPublisherPort aMockWhichChecksCorrectArgumentIsPassed(
                DomainEvent expectedDomainEvent) {
            return new SelfValidatingEventPublisherPort() {
                boolean sent = false;

                @Override
                public void ensureAllEventsWereSent() {
                    if (!sent)
                        throw new RuntimeException("One event was expected to be sent = " + expectedDomainEvent);
                }

                @Override
                public void publish(DomainEvent event) {
                    if (!event.equals(expectedDomainEvent))
                        throw new RuntimeException(String.format("Invalid event, the expected is %s but it was %s", expectedDomainEvent, event));
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
}
