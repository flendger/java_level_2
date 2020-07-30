import java.util.Collection;

public interface Catalog {
    void add(String name, String number);

    Collection get(String name);
}
