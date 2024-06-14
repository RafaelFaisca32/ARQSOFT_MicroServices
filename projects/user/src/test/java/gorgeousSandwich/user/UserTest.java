package gorgeousSandwich.user;

import gorgeousSandwich.user.Domain.User;
import gorgeousSandwich.user.Shared.domain.valueobjects.Email;
import gorgeousSandwich.user.Shared.domain.valueobjects.Password;
import gorgeousSandwich.user.Shared.domain.valueobjects.TaxIdentification;
import gorgeousSandwich.user.Shared.domain.valueobjects.Username;
import gorgeousSandwich.user.Shared.exceptions.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    String invalidString = "";
    String validStringEmail = "blahblah@isep.ipp.pt";
    String validStringPassword = "Umapassword3";
    String validStringUsername = "1180658";
    String validTaxId = "234234234";


    @Test
    void businessValidation() {

        try {
            User userEmail = new TestUser(new Email(invalidString), new Password(validStringPassword), new TaxIdentification(validTaxId), new Username(validStringUsername));
            fail(String.format("Email " + invalidString + " cannot be neither null nor empty and needs to have one @"));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException ignored) {
        }

        try {
            User userPassword = new TestUser(new Email(validStringEmail), new Password(invalidString), new TaxIdentification(validTaxId), new Username(validStringUsername));
            fail(String.format("Password " + invalidString  + " cannot be neither null nor empty, needs to be at least 8 characters long, have 1 number and 1 capitalized letter. "));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException ignored) {
        }

        try {
            User userTaxId = new TestUser(new Email(validStringEmail), new Password(validStringPassword), new TaxIdentification(invalidString), new Username(validStringUsername));
            fail(String.format("Tax Identification " + invalidString + " cannot be neither null nor empty and it needs to be 9 characters long"));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException ignored) {
        }

        try {
            User userUsername = new TestUser(new Email(validStringEmail), new Password(validStringPassword), new TaxIdentification(validTaxId), new Username(invalidString));
            fail(String.format("Username " + invalidString  + " cannot be neither null nor empty."));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException ignored) {
        }
    }


    @Test
    void getEmail() {
        try {
            Email email = new Email(validStringEmail);
            User u = new TestUser(email, new Password(validStringPassword), new TaxIdentification(validTaxId), new Username(validStringUsername));
            assertTrue(email.equals(u.getEmail()));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException e) {
        }

    }

    @Test
    void getPassword() {
        try {
            Password password = new Password(validStringPassword);
            User u = new TestUser(new Email(validStringEmail), password, new TaxIdentification(validTaxId), new Username(validStringUsername));
            assertTrue(password.equals(u.getPassword()));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException e) {
        }
    }

    @Test
    void getTaxIdentification() {
        try {
            TaxIdentification taxId = new TaxIdentification(validTaxId);
            User u = new TestUser(new Email(validStringEmail), new Password(validStringPassword), taxId, new Username(validStringUsername));
            assertTrue(taxId.equals(u.getTaxIdentification()));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException e) {
        }
    }

    @Test
    void getUsername() {
        try {
            Username username = new Username(validStringUsername);
            User u = new TestUser(new Email(validStringEmail), new Password(validStringPassword), new TaxIdentification(validTaxId), username);
            assertTrue(username.equals(u.getUsername()));
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException e) {
        }
    }

    private static class TestUser extends User {

        TestUser(Email email, Password password, TaxIdentification taxIdentification, Username username) {
            super((long)1,email,password,taxIdentification,username);
        }


    }
}

