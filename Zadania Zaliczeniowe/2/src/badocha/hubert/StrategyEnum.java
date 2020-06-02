package badocha.hubert;

public enum StrategyEnum {
    Cheap, Additional, Rich, Greedy;


    public static StrategyEnum getEnum(String token){
        switch (token){
            case "R":
                return Rich;
            case "S":
                return Cheap;
            case "W":
                return Additional;
            case "Z":
                return Greedy;
            default:
                return null;
        }
    }
}
