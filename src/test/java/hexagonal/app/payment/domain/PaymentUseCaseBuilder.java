package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.CreatePaymentUseCaseFactory.CreatePaymentUseCase;
import hexagonal.app.payment.domain.PortMockingCatalog.EventPublisherPortMock;
import hexagonal.app.payment.domain.PortMockingCatalog.PaymentIdentityPortMock;

public interface PaymentUseCaseBuilder extends
        PaymentIdentityPortMock<
                EventPublisherPortMock<
                        CreatePaymentUseCase>> {

    PaymentDomainConfiguration CONFIGURATION = new PaymentDomainConfiguration();

    static PaymentUseCaseBuilder aCreatePaymentUseCase() {
        return paymentIdentity -> publisher -> CONFIGURATION
                .createPaymentUseCaseFactory(
                        publisher,
                        paymentIdentity)
                .useCase();
    }

}
