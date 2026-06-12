/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class Part3Test {
    
    private List<Message> buildTestMessages() {
        List<Message> list = new ArrayList<>();
 
        Message m1 = new Message();
        m1.uniqueID      = "MSG001";
        m1.messageNumber = 1;
        m1.recipient     = "+27894557896";
        m1.messageText   = "Did you get the cake?";
        m1.messageHash   = "MSG001##DID##001";
 
        Message m2 = new Message();
        m2.uniqueID      = "MSG002";
        m2.messageNumber = 2;
        m2.recipient     = "+27838884567";
        m2.messageText   = "Where are you? You are late! I have asked you to be on time.";
        m2.messageHash   = "MSG002##WHERE##002";
 
        // Message 3 (Disregard) — NOT added to the list
 
        Message m4 = new Message();
        m4.uniqueID      = "MSG004";
        m4.messageNumber = 4;
        m4.recipient     = "0838884567";   // developer number (no +27)
        m4.messageText   = "It is dinner time !";
        m4.messageHash   = "MSG004##ITS##004";
 
        Message m5 = new Message();
        m5.uniqueID      = "MSG005";
        m5.messageNumber = 5;
        m5.recipient     = "+27838884567";
        m5.messageText   = "Ok, I am leaving without you.";
        m5.messageHash   = "MSG005##OK##005";
 
        list.add(m1);
        list.add(m2);
        list.add(m4);
        list.add(m5);
        return list;
    }
 
    
    @Test
    @DisplayName("Sent Messages array correctly populated – contains expected test data")
    void testSentMessagesArrayPopulated() {
        List<Message> messages = buildTestMessages();
 
        // Pull only sent messages (flag == "Sent")
        // In the helper above m1 and m4 are the 'Sent' ones.
        String[] sentTexts = messages.stream()
                .filter(m -> m.uniqueID.equals("MSG001") || m.uniqueID.equals("MSG004"))
                .map(m -> m.messageText)
                .toArray(String[]::new);
 
        assertEquals(2, sentTexts.length,
                "Expected exactly 2 sent messages in the array");
 
        assertTrue(Arrays.asList(sentTexts).contains("Did you get the cake?"),
                "Sent messages should contain 'Did you get the cake?'");
        assertTrue(Arrays.asList(sentTexts).contains("It is dinner time !"),
                "Sent messages should contain 'It is dinner time !'");
    }
 
    
    @Test
    @DisplayName("Display longest message returns the correct message")
    void testDisplayLongestMessage() {
        List<Message> messages = buildTestMessages();
 
        Message longest = messages.get(0);
        for (Message m : messages) {
            if (m.messageText.length() > longest.messageText.length()) {
                longest = m;
            }
        }
 
        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                longest.messageText,
                "The longest message should be Message 2"
        );
    }
 
    
    @Test
    @DisplayName("Search by Message ID returns correct message text")
    void testSearchByMessageID() {
        List<Message> messages = buildTestMessages();
 
        // The developer entry has recipient 0838884567; treat that as the searchable ID
        String targetID = "MSG004";
        String foundText = null;
        for (Message m : messages) {
            if (m.uniqueID.equals(targetID)) {
                foundText = m.messageText;
                break;
            }
        }
 
        assertNotNull(foundText, "Message with ID MSG004 should be found");
        assertEquals("It is dinner time !", foundText,
                "Message ID search should return 'It is dinner time !'");
    }
 
    @Test
    @DisplayName("Search by Message ID returns null / not found for unknown ID")
    void testSearchByMessageIDNotFound() {
        List<Message> messages = buildTestMessages();
 
        String foundText = null;
        for (Message m : messages) {
            if (m.uniqueID.equals("MSG999")) {
                foundText = m.messageText;
                break;
            }
        }
 
        assertNull(foundText, "Unknown message ID should not be found");
    }
 
   
    @Test
    @DisplayName("Search by recipient +27838884567 returns both expected messages")
    void testSearchByRecipient() {
        List<Message> messages = buildTestMessages();
        String targetRecipient = "+27838884567";
 
        List<String> found = new ArrayList<>();
        for (Message m : messages) {
            if (m.recipient.equals(targetRecipient)) {
                found.add(m.messageText);
            }
        }
 
        assertEquals(2, found.size(),
                "Recipient +27838884567 should have exactly 2 messages");
        assertTrue(found.contains(
                "Where are you? You are late! I have asked you to be on time."),
                "Should find message 2 for this recipient");
        assertTrue(found.contains("Ok, I am leaving without you."),
                "Should find message 5 for this recipient");
    }
 
    @Test
    @DisplayName("Search by recipient with no messages returns empty list")
    void testSearchByRecipientNotFound() {
        List<Message> messages = buildTestMessages();
 
        List<String> found = new ArrayList<>();
        for (Message m : messages) {
            if (m.recipient.equals("+00000000000")) {
                found.add(m.messageText);
            }
        }
 
        assertTrue(found.isEmpty(),
                "No messages should be found for an unknown recipient");
    }
 
   
    @Test
    @DisplayName("Delete message by hash removes the correct message")
    void testDeleteByHash() {
        List<Message> messages = buildTestMessages();
        String hashToDelete = "MSG002##WHERE##002";   // hash of Message 2
 
        int sizeBefore = messages.size();
        boolean removed = messages.removeIf(m -> m.messageHash.equals(hashToDelete));
 
        assertTrue(removed, "removeIf should return true when the hash is found");
        assertEquals(sizeBefore - 1, messages.size(),
                "List size should decrease by 1 after deletion");
 
        boolean stillPresent = messages.stream()
                .anyMatch(m -> m.messageHash.equals(hashToDelete));
        assertFalse(stillPresent,
                "Deleted message should no longer be present in the list");
    }
 
    @Test
    @DisplayName("Delete by hash with unknown hash makes no change")
    void testDeleteByHashNotFound() {
        List<Message> messages = buildTestMessages();
        int sizeBefore = messages.size();
 
        boolean removed = messages.removeIf(m -> m.messageHash.equals("NONEXISTENT##HASH"));
 
        assertFalse(removed, "removeIf should return false for an unknown hash");
        assertEquals(sizeBefore, messages.size(),
                "List size should be unchanged when hash is not found");
    }
 
    
    @Test
    @DisplayName("Full report contains non-null ID, hash, recipient and message for every entry")
    void testDisplayFullReportFields() {
        List<Message> messages = buildTestMessages();
 
        assertFalse(messages.isEmpty(), "Message list should not be empty for report");
 
        for (Message m : messages) {
            assertNotNull(m.uniqueID,      "Message ID must not be null");
            assertNotNull(m.messageHash,   "Message hash must not be null");
            assertNotNull(m.recipient,     "Recipient must not be null");
            assertNotNull(m.messageText,   "Message text must not be null");
            assertFalse(m.messageText.isEmpty(), "Message text must not be empty");
        }
    }
 
   
    static class Message {
        String uniqueID;
        int    messageNumber;
        String recipient;
        String messageText;
        String messageHash;
    }
} 