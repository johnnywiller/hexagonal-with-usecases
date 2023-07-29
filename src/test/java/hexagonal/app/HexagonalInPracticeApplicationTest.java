package hexagonal.app;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class HexagonalInPracticeApplicationTest {

    @Test
    void writeDocumentationSnippets() {
        ApplicationModules modules = ApplicationModules.of(HexagonalInPracticeApplication.class).verify();
        modules.verify();
        modules.forEach(System.out::println);


    }

}