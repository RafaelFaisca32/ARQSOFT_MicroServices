package gorgeousSandwich.user.Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreatingUserDto {

    public Long id;
    public String email;
    public String password;
    public String taxIdentification;
    public String username;

    public CreatingUserDto(Long id,String email, String password, String taxIdentification, String username){
        this.id = id;
        this.email = email;
        this.password = password;
        this.taxIdentification = taxIdentification;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    //<editor-fold desc="Needed for testing shops and promotions">
    public CreatingUserDto(String email, String password, String taxIdentification, String username) throws NoSuchAlgorithmException {
        this.email = email;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(password.getBytes());
        byte[] bytes = m.digest();
        StringBuilder s = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        this.password = s.toString() + "A";
        this.taxIdentification = taxIdentification;
        this.username = username;
    }

    private CreatingUserDto(){}
}
