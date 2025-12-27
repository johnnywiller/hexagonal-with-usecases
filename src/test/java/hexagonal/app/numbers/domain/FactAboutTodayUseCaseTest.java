package hexagonal.app.numbers.domain;

import java.time.Clock;
import static java.time.Clock.fixed;
import java.time.Instant;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class FactAboutTodayUseCaseTest {

    @Test
    void numberShouldBeExtractedFromDate() {
        Clock fixed = fixed(Instant.parse("2023-07-23T01:00:00.00Z"), UTC);
        FactAboutTodayUseCase factAboutTodayUseCase = new FactAboutTodayUseCase(fixed);
        NumberFact fact = factAboutTodayUseCase.execute();
        assertThat(fact.number()).isEqualTo(20230723);
    }
}
