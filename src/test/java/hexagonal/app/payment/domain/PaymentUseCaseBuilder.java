package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.PaymentPortMockingCatalog.CardReservationPortMock;
import hexagonal.app.payment.domain.PaymentPortMockingCatalog.PaymentIdentityPortMock;
import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory.CreatePaymentUseCase;
import hexagonal.app.shared.EventPublisherPort;
import hexagonal.app.shared.PublishedEventsEnforcer;
import hexagonal.app.shared.SharedPortMockingCatalog.EventPublisherPortMock;

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

    private static CreatePaymentUseCase makeUseCase(PaymentIdentityPort paymentIdentity,
                                                    CardReservationPort cardReservation,
                                                    EventPublisherPort publisher) {
        final CreatePaymentUseCase useCase = DOMAIN_CONFIGURATION
                .createPaymentUseCaseFactory(
                        publisher,
                        paymentIdentity,
                        cardReservation)
                .useCase();

        /*
         not ideal to create this double layer of CreatePaymentUseCase
         but necessary since a
         UseCase<CreatePaymentCommand> is not the same as a CreatePaymentUseCase,
         avoiding this double layer would involve some sort of AOP/proxy
        */
        return new PublishedEventsEnforcer<>(useCase, publisher)::execute;
    }

}
