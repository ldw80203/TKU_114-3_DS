import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Choose: ");

            if (choice == 1) {
                addContact();
            } else if (choice == 2) {
                searchContact();
            } else if (choice == 3) {
                updatePhone();
            } else if (choice == 4) {
                deleteContact();
            } else if (choice == 5) {
                printAllContacts();
            } else if (choice != 0) {
                System.out.println("Invalid option.");
            }
        } while (choice != 0);

        System.out.println("Goodbye.");
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=== Contact Book System ===");
        System.out.println("1. Add contact");
        System.out.println("2. Search contact");
        System.out.println("3. Update phone");
        System.out.println("4. Delete contact");
        System.out.println("5. List all contacts");
        System.out.println("0. Exit");
    }

    public static Contact findByCode(String code) {
        for (Contact contact : contacts) {
            if (contact.getCode().equalsIgnoreCase(code)) {
                return contact;
            }
        }
        return null;
    }

    public static void addContact() {
        String code = readRequiredText("Code: ");
        if (findByCode(code) != null) {
            System.out.println("Code already exists.");
            return;
        }

        String name = readRequiredText("Name: ");
        String phone = readText("Phone: ");
        String email = readText("Email: ");

        contacts.add(new Contact(code, name, phone, email));
        System.out.println("Contact added.");
    }

    public static void searchContact() {
        String code = readRequiredText("Code to search: ");
        Contact found = findByCode(code);

        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println(found);
        }
    }

    public static void updatePhone() {
        String code = readRequiredText("Code to update: ");
        Contact found = findByCode(code);

        if (found == null) {
            System.out.println("Contact not found.");
            return;
        }

        String phone = readText("New phone: ");
        found.setPhone(phone);
        System.out.println("Phone updated.");
    }

    public static void deleteContact() {
        String code = readRequiredText("Code to delete: ");
        Contact found = findByCode(code);

        if (found == null) {
            System.out.println("Contact not found.");
            return;
        }

        contacts.remove(found);
        System.out.println("Contact deleted.");
    }

    public static void printAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts.");
            return;
        }

        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String readRequiredText(String prompt) {
        String value;
        do {
            value = readText(prompt);
            if (value.isEmpty()) {
                System.out.println("This field cannot be blank.");
            }
        } while (value.isEmpty());
        return value;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
