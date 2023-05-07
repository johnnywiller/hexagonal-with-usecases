package hexagonal.app.payment.domain;

import static java.net.InetAddress.getLocalHost;

import java.net.UnknownHostException;
import java.util.Random;

import hexagonal.app.payment.domain.port.driver.UseCase;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;

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
        // TODO place tagging in a more appropriate place
        Counter counter;
        try {
            Tags tags = Tags.of(Tag.of("env", randomEnvTag()),
                                Tag.of("host", getLocalHost()
                                        .getCanonicalHostName()));
            counter = meterRegistry.counter(
                    "CreatePaymentUseCaseCalls", tags);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        counter.increment();

        delegate.execute();
    }

    private String randomEnvTag() {
        return new String[]{"dev", "sit", "prd"}[new Random().nextInt(3)];
    }
}
