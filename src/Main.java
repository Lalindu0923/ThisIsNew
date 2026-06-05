import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();

        // Demo mode for automated verification: run `java -cp out Main demo`
        if (args.length > 0 && "demo".equalsIgnoreCase(args[0])) {
            manager.addContact("Alice", "111-222");
            manager.addContact("Bob", "222-333");
            List<Contact> demoList = manager.listContacts();
            System.out.println("Demo contacts:");
            for (Contact c : demoList)
                System.out.println(c);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("--- Contact CRUD Menu ---");
            System.out.println("1) Add contact");
            System.out.println("2) List contacts");
            System.out.println("3) Update contact by id");
            System.out.println("4) Delete contact by id");
            System.out.println("5) Exit");
            System.out.print("Choose option: ");

            String line = scanner.nextLine().trim();
            if (line.isEmpty())
                continue;
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Number: ");
                    String number = scanner.nextLine().trim();
                    Contact added = manager.addContact(name, number);
                    System.out.println("Added: " + added);
                }
                case 2 -> {
                    List<Contact> all = manager.listContacts();
                    if (all.isEmpty())
                        System.out.println("No contacts");
                    else
                        for (Contact c : all)
                            System.out.println(c);
                }
                case 3 -> {
                    System.out.print("ID to update: ");
                    String idStr = scanner.nextLine().trim();
                    try {
                        int id = Integer.parseInt(idStr);
                        Contact found = manager.findById(id);
                        if (found == null) {
                            System.out.println("Not found");
                            break;
                        }
                        System.out.print("New name (enter to keep): ");
                        String newName = scanner.nextLine().trim();
                        if (newName.isEmpty())
                            newName = found.getName();
                        System.out.print("New number (enter to keep): ");
                        String newNumber = scanner.nextLine().trim();
                        if (newNumber.isEmpty())
                            newNumber = found.getNumber();
                        boolean ok = manager.updateContact(id, newName, newNumber);
                        System.out.println(ok ? "Updated" : "Update failed");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid id");
                    }
                }
                case 4 -> {
                    System.out.print("ID to delete: ");
                    String idStr = scanner.nextLine().trim();
                    try {
                        int id = Integer.parseInt(idStr);
                        boolean ok = manager.deleteContact(id);
                        System.out.println(ok ? "Deleted" : "Not found");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid id");
                    }
                }
                case 5 -> {
                    System.out.println("Goodbye");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Unknown option");
            }
        }
    }
}
