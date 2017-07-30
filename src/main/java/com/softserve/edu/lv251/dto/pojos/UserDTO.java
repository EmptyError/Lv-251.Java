package com.softserve.edu.lv251.dto.pojos;

import com.softserve.edu.lv251.customannotations.PasswordMatches;
import com.softserve.edu.lv251.customannotations.ValidEmail;
import com.softserve.edu.lv251.customannotations.ValidPassword;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Added by Pavlo Kuchereshko.
 * Used for transferring Users entity into more convenient DTO object for registration purposes.
 */
@PasswordMatches
public class UserDTO {
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @ValidPassword
    private String password;
    private String matchingPassword;

    @NotBlank
    @ValidEmail
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
