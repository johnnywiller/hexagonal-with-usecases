package hexagonal.app.payment.infrastructure;

import hexagonal.app.payment.domain.CardReservationResult;
import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory;
import hexagonal.app.shared.EventPublisherPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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

    /**
     * For now, we just fake a success reservation result,
     * in reality we would have an adapter communicating with some backend
     * to reserve the money on the card
     *
     * @return an always success {@link CardReservationPort} adapter
     */
    @Bean
    CardReservationPort cardReservationAdapter() {
        return CardReservationResult::success;
    }

    /**
     * Scheduler that creates payments, for sampling purposes
     *
     * @param useCaseFactory driver port to the domain
     * @return a scheduler bean
     */
    @Bean
    PaymentScheduler paymentScheduler(CreatePaymentUseCaseFactory useCaseFactory) {
        return new PaymentScheduler(useCaseFactory);
    }
}
