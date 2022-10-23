package hexagonal.app.payment.domain;

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
