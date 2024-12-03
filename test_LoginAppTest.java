package test;

import main.LoginApp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAppTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "monemonemone";
    private static Connection conn;

    @BeforeAll
    public static void setUp() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    private String authenticateAndReturnUserName(String email) {
        return new LoginApp().authenticateUser(email);
    }

    // Test Case 1: Valid User Login
    @Test
    public void testLoginValidUser() {
        String email = "johndoe@example.com";
        String expectedUserName = "John Doe";
        String actualUserName = authenticateAndReturnUserName(email);
        assertNotNull(actualUserName);
        assertEquals(expectedUserName, actualUserName);
    }

    // Test Case 2: Invalid User Login
    @Test
    public void testLoginInvalidUser() {
        String email = "nonexistent@example.com";
        assertNull(authenticateAndReturnUserName(email));
    }

    // Test Case 3: Empty Email Field
    @Test
    public void testLoginEmptyEmail() {
        assertNull(authenticateAndReturnUserName(""));
    }

    // Test Case 4: Empty Password Field
    @Test
    public void testLoginEmptyPassword() {
        String email = "johndoe@example.com";
        assertNotNull(authenticateAndReturnUserName(email));
    }

    // Test Case 5: Invalid Email Format
    @Test
    public void testInvalidEmailFormat() {
        String email = "invalidEmailFormat";
        // Assuming authenticateUser() returns null if the email is invalid or improperly formatted
        assertNull(authenticateAndReturnUserName(email), "Login should fail with invalid email format.");
    }



}
