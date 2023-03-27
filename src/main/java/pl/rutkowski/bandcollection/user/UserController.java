package pl.rutkowski.bandcollection.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUsers(Model model) {
        List<Users> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "addUser";
    }

    @PostMapping("/add-user")
    public String addNewUser(UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/user";
    }

    @GetMapping("/user-panel/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Users users = userService.findById(id).orElseThrow();
        List<UserRole> userRoles = userService.findRoleByUserId(id);
        model.addAttribute("roles", userRoles);
        model.addAttribute("user", users);
        return "userEdit";
    }

    @PostMapping("/user-panel/{id}")
    public String updateUser(@PathVariable Long id, Users user) {
        userService.updateUser(id, user);
        return "redirect:/band/homepage";
    }
}
