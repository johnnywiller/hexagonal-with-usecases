package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory;
import hexagonal.app.shared.EventPublisherPort;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentDomainConfiguration {

    @Bean
    CreatePaymentUseCaseFactory createPaymentUseCaseFactory(EventPublisherPort eventPublisherAdapter,
                                                            PaymentIdentityPort paymentIdentityAdapter,
                                                            CardReservationPort cardReservationAdapter,
                                                            MeterRegistry simpleMeterRegistry) {
        return command -> {
            DefaultCreatePaymentUseCase useCase =
                    new DefaultCreatePaymentUseCase(
                            eventPublisherAdapter,
                            paymentIdentityAdapter,
                            cardReservationAdapter,
                            command);
            return new UseCaseMetricsDecorator(simpleMeterRegistry, useCase);
        };
    }
}
