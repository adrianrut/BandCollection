package pl.rutkowski.bandcollection.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByEmail(String email);
    Optional<ApplicationUser> findById(Long id);

}
