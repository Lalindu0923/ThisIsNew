import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private final List<Contact> contacts = new ArrayList<>();
    private int nextId = 1;

    public Contact addContact(String name, String number) {
        Contact c = new Contact(nextId++, name, number);
        contacts.add(c);
        return c;
    }

    public List<Contact> listContacts() {
        return new ArrayList<>(contacts);
    }

    public Contact findById(int id) {
        for (Contact c : contacts) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

    public boolean updateContact(int id, String name, String number) {
        Contact c = findById(id);
        if (c == null)
            return false;
        c.setName(name);
        c.setNumber(number);
        return true;
    }

    public boolean deleteContact(int id) {
        Contact c = findById(id);
        if (c == null)
            return false;
        return contacts.remove(c);
    }
}
