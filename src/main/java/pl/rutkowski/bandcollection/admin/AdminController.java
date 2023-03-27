package pl.rutkowski.bandcollection.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rutkowski.bandcollection.user.RoleDto;
import pl.rutkowski.bandcollection.user.UserService;
import pl.rutkowski.bandcollection.user.Users;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String adminPanel(Model model) {
        List<Users> users1 = userService.findAllWithoutCurrentUser();
        model.addAttribute("users", users1);
        return "admin";
    }

    @PostMapping("/editUser/{id}")
    public String edit(@PathVariable Long id, RoleDto roleDto) {
        userService.updateUserRole(id, roleDto);
        return "redirect:/admin";
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Optional<Users> usersOptional = userService.findById(id);
        if (usersOptional.isPresent()) {
            Users users = usersOptional.get();
            RoleDto roleDto = new RoleDto(users);
            users.getUserRole().forEach(role -> roleDto.getRoles().add(role.getRole()));
            model.addAttribute("roleDto", roleDto);
            return "adminEditRole";
        } else {
            return "redirect:/band/homepage";
        }
    }

}
