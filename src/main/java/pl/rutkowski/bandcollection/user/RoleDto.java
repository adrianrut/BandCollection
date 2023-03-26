package pl.rutkowski.bandcollection.user;

import java.util.HashSet;
import java.util.Set;

public class RoleDto {
    private long id;
    Set<Role> roles = new HashSet<>();
    private Users user;

    public RoleDto(Users user) {
        this.id = user.getId();
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }
}
