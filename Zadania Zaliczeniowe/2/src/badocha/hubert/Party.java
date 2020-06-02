package badocha.hubert;

public abstract class Party {
    private final String name;
    protected int budget;

    Party(String partyName, int partyBudget) {
        name = partyName;
        budget = partyBudget;
    }


    public String getName() {
        return name;
    }

    public static Party[] convertToParties(String[] names, int[] budgets, StrategyEnum[] strategies) {
        int partiesCount = names.length;
        Party[] parties = new Party[partiesCount];
        for(int i =0;i<partiesCount;i++){
            switch (strategies[i]){
                case Cheap:
                    parties[i] = new CheapParty(names[i], budgets[i]);
                case Additional:
                    parties[i] = new OwnStrategyParty(names[i], budgets[i]);
                case Rich:
                    parties[i] = new RichParty(names[i], budgets[i]);
                case Greedy:
                    parties[i] = new GreedyParty(names[i], budgets[i]);
            }

        }

        return parties;
    }
}
