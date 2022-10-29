package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.PortMockingCatalog.CardReservationPortMock;
import hexagonal.app.payment.domain.PortMockingCatalog.EventPublisherPortMock;
import hexagonal.app.payment.domain.PortMockingCatalog.PaymentIdentityPortMock;
import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory.CreatePaymentUseCase;

public interface PaymentUseCaseBuilder extends
        PaymentIdentityPortMock<
                CardReservationPortMock<
                        EventPublisherPortMock<
                                CreatePaymentUseCase>>> {

    PaymentDomainConfiguration DOMAIN_CONFIGURATION = new PaymentDomainConfiguration();

    static PaymentUseCaseBuilder aCreatePaymentUseCase() {
        return paymentIdentity -> cardReservation -> publisher ->
                makeUseCase(paymentIdentity, cardReservation, publisher);
    }

    private static CreatePaymentUseCase makeUseCase(PaymentIdentityPort paymentIdentity, CardReservationPort cardReservation, EventPublisherPort publisher) {
        final CreatePaymentUseCase useCase = DOMAIN_CONFIGURATION
                .createPaymentUseCaseFactory(
                        publisher,
                        paymentIdentity,
                        cardReservation)
                .useCase();

        final PublishedEventsEnforcer publishedEventsEnforcer = new PublishedEventsEnforcer(useCase, publisher);

        return publishedEventsEnforcer;
    }

}
