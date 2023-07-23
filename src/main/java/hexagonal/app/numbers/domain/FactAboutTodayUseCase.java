package hexagonal.app.numbers.domain;

import java.time.Clock;

public class FactAboutTodayUseCase {
    private final Clock fixed;

    public FactAboutTodayUseCase(Clock fixed) {
        this.fixed = fixed;
    }
}
