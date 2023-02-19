package pl.rutkowski.bandcollection.musician;

import jakarta.persistence.*;
import pl.rutkowski.bandcollection.Role;
import pl.rutkowski.bandcollection.band.Band;

@Entity
public class Musician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Band band;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
