package hexagonal.app.payment.domain;

import hexagonal.app.payment.domain.port.driver.UseCase;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Simple decorator to count calls to the usecase via Micrometer
 *
 * @param meterRegistry
 * @param delegate
 */
public record UseCaseMetricsDecorator(MeterRegistry meterRegistry,
                                      DefaultCreatePaymentUseCase delegate) implements UseCase {
    @Override
    public void execute() {
        Counter counter = meterRegistry.counter(
                "CreatePaymentUseCaseCalls");
        counter.increment();
        delegate.execute();
    }
}
