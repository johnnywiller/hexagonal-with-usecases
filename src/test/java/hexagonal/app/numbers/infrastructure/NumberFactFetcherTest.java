package hexagonal.app.numbers.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class NumberFactFetcherTest {

    @Test
    void shouldFetchNumber() {
        NumberFactFetcher fetcher = new NumberFactFetcher();
        String fact = fetcher.fetch(20230723);
        assertThat(fact).isNotBlank();
    }
}
