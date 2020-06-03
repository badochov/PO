package badocha.hubert;

public class Candidate extends Human {
    public static final int MAX_TRAIT_VALUE = 100;
    public static final int MIN_TRAIT_VALUE = -100;
    private final int[] traits;

    Candidate(String name, String surname, int[] ts) {
        super(name, surname);
        traits = ts;
    }

    public int getTrait(int t) {
        return traits[t];
    }

    public void performActions(Action action) {
        for (int i = 0; i < traits.length; i++) {
            traits[i] = Math.max(MIN_TRAIT_VALUE,
                    Math.min(MAX_TRAIT_VALUE, traits[i] + action.getChange(i)));
        }
    }

}
