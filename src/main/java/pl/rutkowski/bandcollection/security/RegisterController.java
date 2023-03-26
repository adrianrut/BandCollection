package pl.rutkowski.bandcollection.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.rutkowski.bandcollection.user.UserDto;
import pl.rutkowski.bandcollection.user.UserService;



@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/register/success";
    }

    @GetMapping("/register/success")
    public String confirmRegistration() {
        return "registerSuccess";
    }
}
