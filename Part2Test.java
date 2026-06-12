/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.part1java.MessageClass;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
public class Part2Test {
    
     private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        MessageClass.messages.clear(); // Clear messages before each test
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }


    private String getOutput() {
        return outContent.toString().trim();
    }

    private void simulateInput(String... inputs) {
        String input = String.join("\n", inputs) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }


    @Test
    @DisplayName("Test 1: Message ID Generation")
    void testMessageIDGeneration() {
        simulateInput("1", "1", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?", "1");

        MessageClass.main(new String[0]);

        assertTrue(getOutput().contains("Message ID generated"));
    }

    @Test
    @DisplayName("Test 2: Send Message - Success Path")
    void testSendMessageSuccess() {
        simulateInput("1", "1", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?", "1");

        MessageClass.main(new String[0]);

        String output = getOutput();

        assertTrue(output.contains("Message successfully sent"));
        assertEquals(1, MessageClass.messages.size());
    }

    @Test
    @DisplayName("Test 3: Store Message")
    void testStoreMessage() {
        simulateInput("1", "1", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?", "3");

        MessageClass.main(new String[0]);

        String output = getOutput();

        assertTrue(output.contains("Message successfully stored"));
        assertEquals(1, MessageClass.messages.size());
    }

    @Test
    @DisplayName("Test 4: Disregard/Delete Message")
    void testDeleteMessage() {
        simulateInput("1", "1", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?", "2");

        MessageClass.main(new String[0]);

        String output = getOutput();

        assertTrue(output.contains("Message deleted"));
        assertEquals(0, MessageClass.messages.size());
    }

    @Test
    @DisplayName("Test 5: Message Length Validation")
    void testMessageLengthValidation() {

        String longMessage = "A".repeat(251);

        simulateInput(
                "1",
                "1",
                "+27718693002",
                longMessage,
                "Hi Mike, can you join us for dinner tonight?",
                "1"
        );

        MessageClass.main(new String[0]);

        assertTrue(getOutput().contains("Message exceeds 250 characters"));
    }

    @Test
    @DisplayName("Test 6: Recipient Number Validation")
    void testRecipientNumberValidation() {

        simulateInput(
                "1",
                "1",
                "08575975889",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                "1"
        );

        MessageClass.main(new String[0]);

        assertTrue(getOutput().contains("Invalid number! Format must be +27"));
    }

    @Test
    @DisplayName("Test 7: Message Hash Generation")
    void testMessageHashGeneration() {

        simulateInput(
                "1",
                "1",
                "+27718693002",
                "Hi tonight",
                "1"
        );

        MessageClass.main(new String[0]);

        String output = getOutput();
        assertTrue(output.contains(":HITONIGHT"));

    }

    @Test
    @DisplayName("Test 8: JSON Output Format")
    void testJSONOutput() {

        simulateInput(
                "1",
                "1",
                "+27718693002",
                "Test message",
                "1"
        );

        MessageClass.main(new String[0]);

        String output = getOutput();

        assertTrue(output.contains("\"MessageID\""));
        assertTrue(output.contains("\"Recipient\""));
        assertTrue(output.contains("\"Message\""));
        assertTrue(output.contains("\"MessageHash\""));
    }

    @Test
    @DisplayName("Test 9: Show Recent Messages")
    void testShowRecentMessages() {

        simulateInput(
    "1",
    "1",
    "+27718693002",
    "Hello",
    "1",
    "3"
);

        MessageClass.main(new String[0]);

        String output = getOutput();

        assertTrue(output.contains("STORED MESSAGES"));
        assertTrue(output.contains("Hello"));
    }

    @Test
    @DisplayName("Test 10: Multiple Messages")
    void testMultipleMessages() {

        simulateInput(
                "1", "2",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                "1",

                "+278575975889",
                "Hi Keegan, did you receive the payment?",
                "1"
        );

        MessageClass.main(new String[0]);

        assertEquals(2, MessageClass.messages.size());
    }
}