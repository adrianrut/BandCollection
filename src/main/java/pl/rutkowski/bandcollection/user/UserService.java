package pl.rutkowski.bandcollection.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers() {
        return  userRepository.findAll();
    }

    public void addUser(UserDto userDto) {
        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setDateOfBirth(userDto.getDateOfBirth());
        userRepository.save(users);
    }
}
