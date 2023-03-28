package pl.rutkowski.bandcollection.user;

import jakarta.persistence.*;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    ApplicationUser users;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole() {
    }

    public UserRole(ApplicationUser users, Role role) {
        this.users = users;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getUsers() {
        return users;
    }

    public void setUsers(ApplicationUser users) {
        this.users = users;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
