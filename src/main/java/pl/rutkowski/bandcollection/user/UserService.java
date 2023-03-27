package pl.rutkowski.bandcollection.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
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

    public List<Users> findAllWithoutCurrentUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getEmail().equals(currentUser.getName()))
                .collect(Collectors.toList());
    }

    public Long findUserId() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = currentUser.getName();
        List<Users> all = userRepository.findAll();
        for (Users users : all) {
            if (Objects.equals(users.getEmail(), userName)) {
                return users.getId();
            }
        }
        return null;
    }

    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Modifying
    @Transactional
    public void updateUserRole(Long id, RoleDto roleDto) {
        userRoleRepository.deleteUserRoleByUsersId(id);
        Users userToUpdate = userRepository.findById(id).orElseThrow();
        Set<UserRole> roles = roleDto.getRoles().stream()
                .map(role -> new UserRole(userToUpdate, role))
                .collect(Collectors.toSet());
        userToUpdate.setUserRole(roles);
        userRoleRepository.saveAll(userToUpdate.getUserRole());
    }

    public void updateUser(Long id, Users users) {
        Users userToUpdate = userRepository.findById(id).orElseThrow();
        userToUpdate.setFirstName(users.getFirstName());
        userToUpdate.setLastName(users.getLastName());
        userToUpdate.setNewsletter(users.isNewsletter());
        userToUpdate.setDateOfBirth(users.getDateOfBirth());
        userToUpdate.setPassword(users.getPassword());
        userRepository.save(userToUpdate);
    }

    public List<UserRole> findRoleByUserId(Long id) {
        return userRoleRepository.findRoleByUsersId(id);
    }
}
