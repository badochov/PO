package badocha.hubert.party;

public enum PartyStrategy {
    Cheap, OwnStrategy, Rich, Greedy;


    public static PartyStrategy getStrategy(String token) {
        switch (token) {
            case "R":
                return Rich;
            case "S":
                return Cheap;
            case "W":
                return OwnStrategy;
            case "Z":
                return Greedy;
            default:
                return null;
        }
    }
}
