package pl.rutkowski.bandcollection.user;

public enum Role {
    ROLE_USER ("Użytkownik"), ROLE_ADMIN("Administrator");
    private final String description;


    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
