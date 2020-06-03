package badocha.hubert;

public enum VoteCountingType {
    DHondt("Metoda D'Honta"), Sainte_Lague("Metoda Sainte-Laguë"), Hare_Niemeyer("Metoda Hare’a-Niemeyera");

    private final String description;

    VoteCountingType(String desc) {
        description = desc;
    }

    @Override public String toString() {
        return description;
    }
}
