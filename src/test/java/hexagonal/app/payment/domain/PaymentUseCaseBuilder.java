package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.PortMockingCatalog.CardReservationPortMock;
import hexagonal.app.payment.domain.PortMockingCatalog.EventPublisherPortMock;
import hexagonal.app.payment.domain.PortMockingCatalog.PaymentIdentityPortMock;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory.CreatePaymentUseCase;

public interface PaymentUseCaseBuilder extends
        PaymentIdentityPortMock<
                CardReservationPortMock<
                        EventPublisherPortMock<
                                CreatePaymentUseCase>>> {

    PaymentDomainConfiguration DOMAIN_CONFIGURATION = new PaymentDomainConfiguration();

    static PaymentUseCaseBuilder aCreatePaymentUseCase() {
        return paymentIdentity -> cardReservation -> publisher -> DOMAIN_CONFIGURATION
                .createPaymentUseCaseFactory(
                        publisher,
                        paymentIdentity,
                        cardReservation)
                .useCase();
    }

}
