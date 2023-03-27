package pl.rutkowski.bandcollection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/no-access")
    public String noAccess() {
        return "accessDenied";
    }
}
