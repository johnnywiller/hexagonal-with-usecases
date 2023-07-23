package hexagonal.app.numbers.domain;

import java.time.Clock;
import static java.time.Clock.fixed;
import java.time.Instant;
import static java.time.ZoneOffset.UTC;
import org.junit.jupiter.api.Test;

public class FactAboutTodayUseCaseTest {

    @Test
    void shouldReturnFactAboutToday() {
        Clock fixed = fixed(Instant.now(), UTC);
        FactAboutTodayUseCase factAboutTodayUseCase = new FactAboutTodayUseCase(fixed);
    }
}
