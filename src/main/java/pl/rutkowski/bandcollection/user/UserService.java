package pl.rutkowski.bandcollection.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> getUsers() {
        return  userRepository.findAll();
    }

    public void addUser(UserDto userDto) {
        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setDateOfBirth(userDto.getDateOfBirth());
        users.setEmail(userDto.getEmail());
        List<UserRole> roles = Collections.singletonList(new UserRole(users, Role.ROLE_USER));
        users.setUserRole(new HashSet<>(roles));
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        users.setPassword(encryptedPassword);
        users.setNewsletter(userDto.isNewsletter());
        userRepository.save(users);
    }


}
