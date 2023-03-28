package pl.rutkowski.bandcollection.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class LoginController {

    @GetMapping("/login")
    String loginForm(@RequestParam(required = false) String registrationSuccess, Model model) {
        boolean showSuccessMessage = Objects.equals(registrationSuccess, "true");
        model.addAttribute("successMessage", showSuccessMessage);
        return "loginForm";
    }
}
