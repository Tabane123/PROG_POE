/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author lenovo
 */
public class Part1Test {
    
   static boolean checkUsername(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }
 
    /**
     * Returns a descriptive result for the username check.
     * Success : "Welcome <user first name>, <user last name> it is great to see you."
     * Failure : "Username is not correctly formatted; please ensure that your username
     *            contains an underscore and is no more than five characters in length."
     */
    static String usernameResult(String username) {
        if (checkUsername(username)) {
            // Spec shows a welcome message on success; name placeholders used here.
            return "Welcome <user first name>, <user last name> it is great to see you.";
        }
        return "Username is not correctly formatted; please ensure that your username "
             + "contains an underscore and is no more than five characters in length.";
    }
 
    /**
     * Checks password complexity rules:
     *  - at least one uppercase letter
     *  - at least one digit
     *  - at least one special character
     *  - no more than 8 characters  (spec: "must contain … ≤ 8 chars")
     */
    static boolean checkPassword(String password) {
        if (password == null) return false;
        boolean hasUpper  = password.matches(".*[A-Z].*");
        boolean hasDigit  = password.matches(".*\\d.*");
        boolean hasSymbol = password.matches(".*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?].*");
        return hasUpper && hasDigit && hasSymbol && password.length() <= 8;
    }
 
    /**
     * Returns a descriptive result for the password check.
     */
    static String passwordResult(String password) {
        if (checkPassword(password)) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted; please ensure that the password "
             + "contains at least eight characters, a capital letter, a number, "
             + "and a special character.";
    }
 
    /**
     * Checks cell phone rules: starts with +27 followed by exactly 9 digits.
     */
    static boolean checkCellPhone(String cell) {
        return cell != null && cell.matches("^\\+27\\d{9}$");
    }
 
    /**
     * Returns a descriptive result for the cell phone check.
     */
    static String cellPhoneResult(String cell) {
        if (checkCellPhone(cell)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell number is incorrectly formatted or does not contain an "
             + "international code; please correct the number and try again.";
    }
 
    /**
     * Simulates login: returns true only when both credentials match.
     */
    static boolean login(String storedUser, String storedPass,
                         String enteredUser, String enteredPass) {
        return storedUser.equals(enteredUser) && storedPass.equals(enteredPass);
    }
 
 
 
    @Test
    @DisplayName("Username correctly formatted: 'kyl_1' → welcome message")
    void testUsernameCorrectlyFormatted() {
        String result = usernameResult("kyl_1");
        assertEquals(
            "Welcome <user first name>, <user last name> it is great to see you.",
            result,
            "Valid username should return the welcome message"
        );
    }
 
    @Test
    @DisplayName("Username incorrectly formatted: 'kyle!!!!!!!' → failure message")
    void testUsernameIncorrectlyFormatted() {
        String result = usernameResult("kyle!!!!!!!");
        assertEquals(
            "Username is not correctly formatted; please ensure that your username "
          + "contains an underscore and is no more than five characters in length.",
            result,
            "Invalid username should return the formatted failure message"
        );
    }
 
    @Test
    @DisplayName("Password meets complexity: 'Ch&&sec@ke99!' → 'Password successfully captured.'")
    void testPasswordMeetsComplexity() {
        String result = passwordResult("Ch&&sec@ke99!");
        assertEquals("Password successfully captured.", result,
                "Complex password should be accepted");
    }
 
    @Test
    @DisplayName("Password does not meet complexity: 'password' → failure message")
    void testPasswordDoesNotMeetComplexity() {
        String result = passwordResult("password");
        assertEquals(
            "Password is not correctly formatted; please ensure that the password "
          + "contains at least eight characters, a capital letter, a number, "
          + "and a special character.",
            result,
            "Simple password should be rejected"
        );
    }
 
    
 
    @Test
    @DisplayName("Cell phone correctly formatted: '+27838968976' → success message")
    void testCellPhoneCorrectlyFormatted() {
        String result = cellPhoneResult("+27838968976");
        assertEquals("Cell phone number successfully captured.", result,
                "Valid +27 number should be accepted");
    }
 
    @Test
    @DisplayName("Cell phone incorrectly formatted: '08966553' → failure message")
    void testCellPhoneIncorrectlyFormatted() {
        String result = cellPhoneResult("08966553");
        assertEquals(
            "Cell number is incorrectly formatted or does not contain an "
          + "international code; please correct the number and try again.",
            result,
            "Number without +27 and wrong length should be rejected"
        );
    }
 
    
 
    @Test
    @DisplayName("Login successful → returns true")
    void testLoginSuccessful() {
        assertTrue(
            login("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!"),
            "Correct credentials should return true"
        );
    }
 
    @Test
    @DisplayName("Login failed (wrong credentials) → returns false")
    void testLoginFailed() {
        assertFalse(
            login("kyl_1", "Ch&&sec@ke99!", "wrong", "wrong"),
            "Incorrect credentials should return false"
        );
    }
 
  
 
    @Test
    @DisplayName("Username correctly formatted returns true (boolean check)")
    void testUsernameCorrectlyFormattedBoolean() {
        assertTrue(checkUsername("kyl_1"),
                "'kyl_1' should pass the username boolean check");
    }
 
    @Test
    @DisplayName("Username incorrectly formatted returns false (boolean check)")
    void testUsernameIncorrectlyFormattedBoolean() {
        assertFalse(checkUsername("kyle!!!!!!!"),
                "'kyle!!!!!!!' should fail the username boolean check");
    }
 
    @Test
    @DisplayName("Password meets complexity returns true (boolean check)")
    void testPasswordMeetsComplexityBoolean() {
        assertTrue(checkPassword("Ch&&sec@ke99!"),
                "Complex password should return true");
    }
 
    @Test
    @DisplayName("Password does not meet complexity returns false (boolean check)")
    void testPasswordDoesNotMeetComplexityBoolean() {
        assertFalse(checkPassword("password"),
                "Simple password should return false");
    }
 
    @Test
    @DisplayName("Cell phone correctly formatted returns true (boolean check)")
    void testCellPhoneCorrectlyFormattedBoolean() {
        assertTrue(checkCellPhone("+27838968976"),
                "Valid +27 number should return true");
    }
 
    @Test
    @DisplayName("Cell phone incorrectly formatted returns false (boolean check)")
    void testCellPhoneIncorrectlyFormattedBoolean() {
        assertFalse(checkCellPhone("08966553"),
                "Invalid number should return false");
    }
}