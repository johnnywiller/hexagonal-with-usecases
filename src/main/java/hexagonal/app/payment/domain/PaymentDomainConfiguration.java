package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.EventPublisherPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory;
import org.springframework.context.annotation.Bean;

public class PaymentDomainConfiguration {

    @Bean
    CreatePaymentUseCaseFactory createPaymentUseCaseFactory(EventPublisherPort eventPublisherAdapter,
                                                            PaymentIdentityPort paymentIdentityAdapter) {
        return () -> new DefaultCreatePaymentUseCase(
                eventPublisherAdapter,
                paymentIdentityAdapter);
    }
}
