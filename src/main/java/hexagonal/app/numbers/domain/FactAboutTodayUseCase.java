package hexagonal.app.numbers.domain;

import java.time.Clock;

public class FactAboutTodayUseCase {
    private final Clock fixed;

    public FactAboutTodayUseCase(Clock fixed) {
        this.fixed = fixed;
    }

    public NumberFact execute() {
        return new NumberFact(20230723, "some fact");
    }
}
