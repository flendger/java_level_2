import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PhoneBook implements Catalog{
    private Map<String, HashSet> mapBook = new HashMap<>();

    @Override
    public void add(String name, String number) {
        HashSet<PhoneBookEntry> entrySet = new HashSet<>();

        if (mapBook.containsKey(name)) {
            entrySet = mapBook.get(name);
        } else {
            mapBook.put(name, entrySet);
        }

        entrySet.add(new PhoneBookEntry(name, number));
    }

    @Override
    public Collection<PhoneBookEntry> get(String name) {
        if (mapBook.containsKey(name)) {
            return mapBook.get(name);
        }
        return null;
    }
}
