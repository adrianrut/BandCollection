package pl.rutkowski.bandcollection.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    String loginForm() {
        return "loginForm";
    }
}
