/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1java;

/**
 *
 * @author lenovo
 */
import static com.mycompany.part1java.MessageClass.JSON_FILE;
import static com.mycompany.part1java.MessageClass.deleteByHash;
import static com.mycompany.part1java.MessageClass.displayAllSenderRecipient;
import static com.mycompany.part1java.MessageClass.displayFullReport;
import static com.mycompany.part1java.MessageClass.displayLongestMessage;
import static com.mycompany.part1java.MessageClass.input;
import static com.mycompany.part1java.MessageClass.messages;
import static com.mycompany.part1java.MessageClass.searchByMessageID;
import static com.mycompany.part1java.MessageClass.searchByRecipient;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;



    
public class Part1Java {
    
    static Scanner input = new Scanner(System.in);
    static ArrayList<Message> messages = new ArrayList<>();
    public static void main(String[] args) {
        
        
        
  //================usre REGISTRATION================
   System.out.println("Welcome");
   System.out.println(" ===========================");
   System.out.println("  PLEASE ENTER YOUR DETAILS ");
   System.out.println("  ==========================  ");
   
     ///using string to store the name,lastname,username,Password,Cell phone number                                               
    String name;
    String Lastname;
    String Username = null;
    String Password = null;
    String CellPhoneNumber =  null;
    
    //***********Username Rgistration *************
        while (true) {
   // Display username rule
        System.out.println("\n=======Username Rules=========");
        System.out.println("Must contain an underscore(_)");
        System.out.println("Must be no more than 5 characters");
        System.out.println("===============================");
                
        System.out.print("Enter Username: ");
        Username = input.nextLine();
       
    if( Username.contains("_") && Username.length() <=5){
        System.out.println("\nUsername Successfully added");
        break;
    }
    else {
        System.out.println("\nEnter the correct uername format");
    }
  }
    
    //-------------Password Registration------------
  while (true) {
       // Display Passowrd
         System.out.println("\n=====Password Rules=========:");  
         System.out.println("Must contaain a capital Letter");
         System.out.println("must contain a sepecial char");
         System.out.println("==============================");
                 
         System.out.print("Enter Password: ");
         Password = input.nextLine();
         
        ///if all the requirment are met the 
    boolean mustHaveUpperCase = Password.matches(".*[A-Z].*");
    boolean mustHaveANumber = Password.matches(".*\\d.*");
    boolean mustHaveSymbol = Password.matches(".*[12#$%6&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?].*");
       
       //if all the requirment are met the code will stop and display "Pasword successfully captured"
       if(mustHaveUpperCase && mustHaveANumber && mustHaveSymbol)
       if(Password.length() <=8){
           System.out.println("\nPasword successfully captured");
           break;
       }        
       else{
            System.out.println(" Pasword unsuccessfully cpatured, Try Again");
       }         
          System.out.println("Registration Successfull");
  }    
  
  //----------- Cell Phone Registration -----------
  while (true){
      System.out.println("\n=====CELL PHONE rulse=======");
      System.out.println("Must start with the country internation code");
      System.out.println("must be length of 9");
      System.out.println("=================================");
      
      System.out.print("Enter cell phon numbers(+27xxxxxxxxx):");
      CellPhoneNumber =  input.nextLine();
      
  
  //cell phone check
  if(CellPhoneNumber.matches("^\\+27\\d{9}$")){
  System.out.println("numbers successfully added");
  break;
  }
  else {
      System.out.println("numbers Unsuccessfully added,  enter thre correct format");
          }
  }
  
    //Get full
    System.out.print("\nEnter Name:");
    name = input.nextLine();
    System.out.print("Enter lastname: ");
    Lastname = input.nextLine();
    
    System.out.print("--Login successfully--");
 
     //After the use enter the correct ditail coe will welcom the User
     System.out.println("\n==================================");
     System.out.println("WELCOME"+" "+ name +" "+ Lastname);
     System.out.println("==================================");
     System.out.println("--PLEASE ENTER YOUR LOGIN details--");
    
   
   while(true){
     System.out.println("Enter Username: ");
     String   LoginUsername = input.nextLine();
     
     System.out.println("Enter Password: ");
     String LoginPassword = input.nextLine();
            
     if(LoginUsername.equals(Username) && LoginPassword.equals(Password)){
        
        System.out.println("\n--Login Successfully--");
        
        break;
     }else{
         System .out.println("Incorrect username or password, Please enter the corret Details ");
     }
    
   }
     
     {
// ====================== PART 2: QUICKCHAT MAIN APPLICATION ======================
        System.out.println("\n=================================================");
        System.out.println("RE YA GO AMOGELA GO QUICKCHAT (WELCOME TO QUICKCHAT)");
        System.out.println("===================================================");

        // Main menu loop
        while (true) {
            System.out.println("\n....SELECT OPTION....");
            System.out.println("1: SEND MESSAGE");
            System.out.println("2: SHOW RECENT MESSAGES");
            System.out.println("3: QUIT");
            System.out.print("\nEnter your option: ");

            int option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                sendMessage();

            } else if (option == 2) {
                showMessage();

            } else if (option == 3) {
                System.out.println("\nThank you for using QuickChat. GOODBYE!");
                break;

            } else {
                System.out.println("Invalid option.");
            }
        }
     }
  }
    
    static void sendMessage() {

        System.out.print("\nHow many messages would you like to send: ");

        int numMessages;
         // Validate number of messages input
        while (true) {

            try {
                numMessages = Integer.parseInt(input.nextLine().trim());
                if (numMessages > 0) {
                    break;
                }
                System.out.print("Please enter a positive number: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        // Loop to send multiple messages
        for (int i = 1; i <= numMessages; i++) {
            System.out.println("\n--- Sending Message " + i + " of " + numMessages + " ---");

            
            // Recipient validation
            String recipient;
            while (true) {
                System.out.print("Enter recipient cell number (+27xxxxxxxxx): ");
                recipient = input.nextLine().trim();
                if (recipient.matches("^\\+27\\d{9}$")) {
                    break;
                }
                System.out.println("Invalid number! Format must be +27 followed by 9 digits.");
            }

            
            // Message validation
            String messageText;
            while (true) {
                System.out.print("Enter your message (max 250 characters): ");
                messageText = input.nextLine().trim();
                if (messageText.length() > 250) {
                    System.out.println("Message exceeds 250 characters.");
                } else if (messageText.isEmpty()) {

                    System.out.println("Message cannot be empty.");
                } else {
                    break;
                }
            }

            
            // Generate ID
            String uniqueId = String.format("%010d",
                    new Random().nextInt(1000000000));
            int messageCounter = messages.size() + 1;
            
            

            // Hash creation
            String firstTwo = uniqueId.substring(0, 2);

            String firstWord = messageText.split(" ")[0].toUpperCase();

            String lastWord = messageText.contains(" ")
                    ? messageText.substring(messageText.lastIndexOf(" ") + 1).toUpperCase()
                    : firstWord;
            String hash = firstTwo + ":" + messageCounter + ":" + firstWord + lastWord;
            
            

            // Create message object
            Message msg = new Message();

            msg.uniqueID = uniqueId;
            msg.messageNumber = messageCounter;
            msg.recipient = recipient;
            msg.messageText = messageText;
            msg.messageHash = hash;
            
            

            // Display message
            System.out.println("\n--- Message Info ---");
            System.out.println("Message ID   : " + msg.uniqueID);
            System.out.println("Message Hash : " + msg.messageHash);
            System.out.println("Recipient    : " + msg.recipient);
            System.out.println("Message      : " + msg.messageText);
            System.out.println("-----------------------");
            
            

            // Post-send options
            System.out.println("\nWhat would you like to do?");
            System.out.println("1 - Send Message");
            System.out.println("2 - Delete Message");
            System.out.println("3 - Save Message to send later");

            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(input.nextLine());

            if (choice == 1) {
                messages.add(msg);
                System.out.println("Message successfully sent!");
                saveMessagesToJsonFile();

            } else if (choice == 2) {

                System.out.println("Message deleted.");

            } else if (choice == 3) {
                messages.add(msg);
                System.out.println("Message successfully stored!");
               saveMessagesToJsonFile();
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
    // ====================== PART 3(POE): Store Date and Display Task Report ======================
     static void showMessage() {
             while(true){
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("1. Display sender and recipient of all stored messages");
            System.out.println("2. Display the longest stored message");
            System.out.println("3. Search by Message ID");
            System.out.println("4. Search messages for a particular recipient");
            System.out.println("5. Delete a message using Message Hash");
            System.out.println("6. Display full report of all stored messages");
            System.out.println("0. Back to main menu");
            System.out.print("Enter choice: ");
            
           int choice = getValidChoice();

            switch (choice) {
                case 1:
                    displayAllSenderRecipient();
                    break;
                case 2:
                    displayLongestMessage();
                    break;
                case 3:
                    searchByMessageID();
                    break;
                case 4:
                    searchByRecipient();
                    break;
                case 5:
                    deleteByHash();
                    break;
                case 6:
                    displayFullReport();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    return;                    // Exit submenu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
      static int getValidChoice() {
        while (true) {
            try {
                int choice = input.nextInt();
                input.nextLine();                 // <<< MOST IMPORTANT FIX: consume newline
                if (choice >= 0 && choice <= 6) {
                    return choice;
                } else {
                    System.out.print("Please enter a number between 0 and 6: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input! Please enter a number: ");
                input.nextLine();                 // Clear bad input
            }
        }
    }
    // ====================== ARRAY POPULATION (as required) ======================
    private static String[] getSentMessagesArray() {
        return messages.stream().map(m -> m.messageText).toArray(String[]::new);
    }

    private static String[] getMessageHashesArray() {
        return messages.stream().map(m -> m.messageHash).toArray(String[]::new);
    }

    private static String[] getMessageIDsArray() {
        return messages.stream().map(m -> m.uniqueID).toArray(String[]::new);
    }

    private static String[] getStoredMessagesArray() {
        return messages.stream().map(m -> m.messageText).toArray(String[]::new);
    }

    // ====================== SUB-MENU FEATURES ======================
   static void displayAllSenderRecipient() {
        if (messages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        System.out.println("\n=== SENDER & RECIPIENT ===");
        for (Message m : messages) {
            System.out.println("ID: " + m.uniqueID  + " | Recipient: " + m.recipient);
        }
    }

    static void displayLongestMessage() {
        if (messages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        Message longest = messages.get(0);
        for (Message m : messages) {
            if (m.messageText.length() > longest.messageText.length()) {
                longest = m;
            }
        }
        System.out.println("\nLongest Message (ID: " + longest.uniqueID + "):");
        System.out.println(longest.messageText);
        System.out.println("Length: " + longest.messageText.length() + " characters");
    }

   static void searchByMessageID() {
        System.out.print("Enter Message ID: ");
        String id = input.nextLine().trim();

        for (Message m : messages) {
            if (m.uniqueID.equals(id)) {
                System.out.println("Recipient: " + m.recipient);
                System.out.println("Message : " + m.messageText);
                return;
            }
        }
        System.out.println("Message ID not found.");
    }
               
  static void searchByRecipient() {
        System.out.print("Enter recipient number: ");
        String recip = input.nextLine().trim();
        
        boolean found = false;
        for (Message m : messages) {
            if (m.recipient.equals(recip)) {
                System.out.println("ID: " + m.uniqueID + " | " + m.messageText);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    static void deleteByHash() {
        System.out.print("Enter Message Hash to delete: ");
        String hash = input.nextLine().trim();
        
        boolean removed = messages.removeIf(m -> m.messageHash.equals(hash));
        
        if (removed) {
            System.out.println("Message deleted successfully.");
            saveMessagesToJsonFile();
        } else {
            System.out.println("No message found with that hash.");
        }
    }

   static void displayFullReport() {
        if (messages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FULL STORED MESSAGES REPORT");
        System.out.println("=".repeat(60));
        for (Message m : messages) {
            System.out.println("Message ID : " + m.uniqueID);
            System.out.println("Hash : " + m.messageHash);
            System.out.println("Recipient : " + m.recipient);
            System.out.println("Message : " + m.messageText);
            System.out.println("Length : " + m.messageText.length());
            System.out.println("-".repeat(50));
        }
    }

    // ====================== JSON HANDLING ======================
    static void saveMessagesToJsonFile() {
        try (FileWriter file = new FileWriter(JSON_FILE)) {
            JSONArray jsonArray = new JSONArray();
            for (Message msg : messages) {
                JSONObject obj = new JSONObject();
                obj.put("MessageID", msg.uniqueID);
                obj.put("MessageNumber", msg.messageNumber);
                obj.put("Recipient", msg.recipient);
                obj.put("Message", msg.messageText);
                obj.put("MessageHash", msg.messageHash);
                jsonArray.put(obj);
            }
            file.write(jsonArray.toString(4));   // Pretty print
            System.out.println("Messages saved to " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
        }
    }

    static void loadMessagesFromJson() {
        try {
            if (!Files.exists(Paths.get(JSON_FILE))) return;

            String content = new String(Files.readAllBytes(Paths.get(JSON_FILE)));
            if (content.trim().isEmpty()) return;

            JSONArray jsonArray = new JSONArray(content);
            messages.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Message msg = new Message();
                msg.uniqueID = obj.getString("MessageID");
                msg.messageNumber = obj.getInt("MessageNumber");
                msg.recipient = obj.getString("Recipient");
                msg.messageText = obj.getString("Message");
                msg.messageHash = obj.getString("MessageHash");
                messages.add(msg);
            }
            System.out.println("Loaded " + messages.size() + " messages from JSON.");
        } catch (Exception e) {
            System.out.println("Error loading JSON: " + e.getMessage());
        }
    }
 }
