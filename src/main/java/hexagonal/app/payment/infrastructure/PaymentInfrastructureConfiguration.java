package hexagonal.app.payment.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory;
import hexagonal.app.shared.EventPublisherPort;

@Configuration
@EnableScheduling
public class PaymentInfrastructureConfiguration {

    @Bean
    EventPublisherPort eventPublisherAdapter() {
        return event -> {

        };
    }

    @Bean
    PaymentIdentityPort paymentIdentityAdapter() {
        return () -> null;
    }

    @Bean
    CardReservationPort cardReservationAdapter() {
        return () -> null;
    }

    /**
     * Scheduler that creates payments, for sampling purposes
     * @param useCaseFactory driver port to the domain
     * @return a scheduler bean
     */
    @Bean
    PaymentScheduler paymentScheduler(CreatePaymentUseCaseFactory useCaseFactory) {
        return new PaymentScheduler(useCaseFactory);
    }
}
