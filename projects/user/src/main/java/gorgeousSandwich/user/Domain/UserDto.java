package gorgeousSandwich.user.Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDto {

    public Long id;
    public String email;
    public String password;
    public String taxIdentification;
    public String username;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTaxIdentification() {
        return taxIdentification;
    }

    public void setTaxIdentification(String taxIdentification) {
        this.taxIdentification = taxIdentification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDto(Long id, String email, String password, String taxIdentification, String username) throws NoSuchAlgorithmException {
        this.id = id;
        this.email = email;
        this.password = password;
        this.taxIdentification = taxIdentification;
        this.username = username;
    }
}
