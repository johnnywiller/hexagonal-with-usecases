package hexagonal.app.shared.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootRedirectController {

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index.html";
    }
}

