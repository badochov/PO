package badocha.hubert;

public class Candidate extends Human{
    private int[] traits;

    Candidate(String name, String surname, int[] ts) {
        super(name, surname);
        traits = ts;
    }

    public int getTrait(int t){
        return traits[t];
    }
}
