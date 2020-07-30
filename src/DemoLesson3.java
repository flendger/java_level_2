import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DemoLesson3 {
    public static void main(String[] args) {
        doTask1();
        doTask2();
    }

    public static void doTask1(){
        String[] strArray = {"a", "b", "c", "a", "d", "e", "d", "c", "a"};
        Map<String, Integer> mapStr = new HashMap<>();

        for (int i = 0; i < strArray.length; i++) {
            int count;
            if (mapStr.containsKey(strArray[i])) {
                count = mapStr.get(strArray[i]) + 1;
            } else {
                count = 1;
            }
            mapStr.put(strArray[i], count);
        }

        System.out.println(mapStr);

    }

    public static void doTask2(){
        PhoneBook myBook = new PhoneBook();
        myBook.add("ivanov", "111");
        myBook.add("petrov", "222");
        myBook.add("ivanov", "333");

        String name;
        Collection foundEntries;

        name = "ivanov";
        foundEntries = myBook.get(name);
        if (foundEntries != null){
            for (Object entry:foundEntries
            ) {
                System.out.println(entry);
            }
        } else {
            System.out.println(String.format("Записи под фамилией \"%s\" отсутствуют...", name));
        }

        name = "ivanov1";
        foundEntries = myBook.get(name);
        try {
            for (Object entry:foundEntries
            ) {
                System.out.println(entry);
            }
        } catch (NullPointerException e) {
            System.out.println(String.format("Записи под фамилией \"%s\" отсутствуют...", name));
        }
    }
}
