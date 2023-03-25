package pl.rutkowski.bandcollection.musician;

public enum Role {
    GUITAR("Gitara"), BASS("Gitara basowa"), DRUM("Perkusja"),
    KEYBOARD("Klawisze"), VOCAL("Wokal"), OTHER("Inne");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
