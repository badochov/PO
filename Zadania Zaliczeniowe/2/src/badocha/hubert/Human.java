package badocha.hubert;

public abstract class Human {
    private final String name;
    private final String surname;

    public Human(String first, String sur) {
        name = first;
        surname = sur;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
