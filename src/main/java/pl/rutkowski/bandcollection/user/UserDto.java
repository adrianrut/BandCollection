package pl.rutkowski.bandcollection.user;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserDto {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 100)
    private String lastName;
    @Past
    private LocalDate dateOfBirth;
    @NotBlank
    @Email
    @Size(min = 5, max = 100)
    private String email;
    @NotBlank
    @Size(min = 8, max = 100, message = "Hasło musi posiadać min. 8 znaków")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Hasło musi posiadać min. jedną małą literę"),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Hasło musi posiadać min. jedną dużą literę"),
            @Pattern(regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+", message = "Hasło musi posiadać min. jeden znak specjalny"),
            })
    private String password;
    private boolean newsletter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
