package hexagonal.app;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class HexagonalInPracticeApplicationTest {

    @Test
    void writeDocumentationSnippets() {
        var modules = ApplicationModules.of(HexagonalInPracticeApplication.class).verify();

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

}