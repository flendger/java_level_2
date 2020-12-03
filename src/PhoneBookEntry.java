public class PhoneBookEntry {
    private String name;
    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneBookEntry that = (PhoneBookEntry) o;

        if (!name.equals(that.name)) return false;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }

    public PhoneBookEntry(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneBookEntry{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
