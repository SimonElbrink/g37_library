package se.lexicon.workshop.g37_library.model.form;

import java.time.LocalDate;

public class AppUserForm {

    private String username;
    private String password;
    private LocalDate regDate;
    //Instead of nested details object.
    private String email;
    private String fullName;
    private LocalDate birthDate;


    public AppUserForm(String username, String password, LocalDate regDate, String email, String fullName, LocalDate birthDate) {
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
