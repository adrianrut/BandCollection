package pl.rutkowski.bandcollection.user;

import java.util.HashSet;
import java.util.Set;

public class RoleDto {
    private long id;
    private Set<Role> roles = new HashSet<>();
    private ApplicationUser user;

    public RoleDto() {
    }

    public RoleDto(ApplicationUser user) {
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public ApplicationUser getUser() {
        return user;
    }
}
