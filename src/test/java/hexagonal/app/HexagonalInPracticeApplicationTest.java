package hexagonal.app;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class HexagonalInPracticeApplicationTest {

    @Test
    void writeDocumentationSnippets() {
        ApplicationModules modules = ApplicationModules.of(HexagonalInPracticeApplication.class).verify();
        modules.verify();
        modules.forEach(System.out::println);
        modules.getSharedModules().forEach(System.out::println);
        System.out.println("modules.getSource() = " + modules.getSource());
        System.out.println("modules.getSystemName() = " + modules.getSystemName());
    }

}