package hexagonal.app.payment.infrastructure;

import hexagonal.app.payment.domain.port.driven.CardReservationPort;
import hexagonal.app.payment.domain.port.driven.PaymentIdentityPort;
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

    @Bean
    CardReservationPort cardReservationAdapter() {
        return () -> null;
    }
}
