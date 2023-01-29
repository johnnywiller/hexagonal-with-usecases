package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.PaymentPortMockingCatalog.CardReservationPortMock;
import hexagonal.app.payment.domain.PaymentPortMockingCatalog.PaymentIdentityPortMock;
import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.UseCase;
import hexagonal.app.shared.SelfValidatingEventPublisherPort;
import hexagonal.app.shared.SharedPortMockingCatalog.EventPublisherPortMock;

public interface PaymentUseCaseBuilder extends
        PaymentIdentityPortMock<
                CardReservationPortMock<
                        EventPublisherPortMock<
                                UseCase>>> {

    PaymentDomainConfiguration DOMAIN_CONFIGURATION =
            new PaymentDomainConfiguration();

    /**
     * Returns a fluent builder to construct a Create Payment UseCase. The
     * CreatePaymentCommand could also be provided in a fluent style as opposed
     * to an initial argument like done here.
     *
     * @param command
     * @return
     */
    static PaymentUseCaseBuilder aCreatePaymentUseCase(CreatePaymentCommand command) {
        return paymentIdentity -> cardReservation -> publisher ->
                makeUseCase(paymentIdentity, cardReservation, publisher,
                        command);
    }

    private static UseCase makeUseCase(PaymentIdentityPort paymentIdentity,
                                       CardReservationPort cardReservation,
                                       SelfValidatingEventPublisherPort publisher,
                                       CreatePaymentCommand command) {
        final UseCase useCase = DOMAIN_CONFIGURATION
                .createPaymentUseCaseFactory(
                        publisher,
                        paymentIdentity,
                        cardReservation,
                        command)
                .createPaymentUseCase();

        return new PublishedEventsEnforcer(useCase, publisher);
    }

}
