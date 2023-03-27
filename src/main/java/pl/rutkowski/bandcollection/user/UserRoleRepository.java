package pl.rutkowski.bandcollection.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findRoleByUsersId(Long id);

    void deleteUserRoleByUsersId(Long userId);
}
