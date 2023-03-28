package pl.rutkowski.bandcollection.security;

import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.rutkowski.bandcollection.user.UserRepository;
import pl.rutkowski.bandcollection.user.ApplicationUser;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ApplicationUser> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            ApplicationUser user = userOptional.get();
            Set<SimpleGrantedAuthority> roles = user.getUserRole()
                    .stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().name()))
                    .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
        }

        throw new UsernameNotFoundException("Username " + email + "not found");
    }

}
