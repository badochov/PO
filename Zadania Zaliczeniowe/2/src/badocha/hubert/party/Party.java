package badocha.hubert.party;

import badocha.hubert.Action;
import badocha.hubert.Candidate;
import badocha.hubert.Pair;
import badocha.hubert.VoteCountingType;
import badocha.hubert.constituencies.Constituency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public abstract class Party {
    private final String name;
    protected int budget;
    protected final Action[] actions;
    protected Constituency[] constituencies;

    static protected int totalPrice(Pair<Action, Constituency> p) {
        return totalPrice(p.getFirst(), p.getSecond());
    }

    static protected int totalPrice(Action action, Constituency constituency) {
        return action.getPrice() * constituency.getSize() / 10;
    }

    protected ArrayList<Pair<Action, Constituency>> getAvailableActions() {
        ArrayList<Pair<Action, Constituency>> pairs = new ArrayList<>();
        for (Action action : actions) {
            for (Constituency constituency : constituencies) {
                if (totalPrice(action, constituency) <= budget) {
                    pairs.add(new Pair<>(action, constituency));
                }
            }
        }

        return pairs;
    }

    Party(String partyName, int partyBudget, Action[] actionsAvailable,
          Constituency[] constituenciesAvailable) {
        name = partyName;
        budget = partyBudget;
        actions = getActionsSortedByPrice(actionsAvailable);
        constituencies = getConstituenciesSortedByPrice(constituenciesAvailable);
    }

    public void setConstituencies(Constituency[] constituenciesAvailable) {
        constituencies = getConstituenciesSortedByPrice(constituenciesAvailable);
    }

    public String getName() {
        return name;
    }

    abstract public void performAction(VoteCountingType voteCountingType);

    public final boolean canPerformAction() {
        return budget >= totalPrice(actions[0], constituencies[0]);
    }

    private Action[] getActionsSortedByPrice(Action[] actions) {
        return Arrays.stream(actions).sorted(Comparator.comparingInt(Action::getPrice))
                .toArray(Action[]::new);
    }

    private Constituency[] getConstituenciesSortedByPrice(Constituency[] actions) {
        return Arrays.stream(actions).sorted(Comparator.comparingInt(Constituency::getSize))
                .toArray(Constituency[]::new);
    }

    protected void applyAction(Pair<Action, Constituency> pair){
        for (Candidate candidate : pair.getSecond().getPartyCandidates(getName())) {
            candidate.performActions(pair.getFirst());
        }
    }
}
