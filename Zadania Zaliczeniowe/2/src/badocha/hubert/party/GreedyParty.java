package badocha.hubert.party;

import badocha.hubert.Action;
import badocha.hubert.Pair;
import badocha.hubert.VoteCountingType;
import badocha.hubert.constituencies.Constituency;

public class GreedyParty extends Party {
    public GreedyParty(String partyName, int partyBudget, Action[] actionsAvailable,
                       Constituency[] constituenciesAvailable) {
        super(partyName, partyBudget, actionsAvailable, constituenciesAvailable);
    }

    @Override public void performAction(VoteCountingType voteCountingType) {
        var availableActions = getAvailableActions();

        var bestAction = getAvailableActions().get(0);
        int maxValue = Integer.MIN_VALUE;
        for (var action : availableActions) {
            int value = calculateActionValue(action);
            if(value > maxValue){
                value = maxValue;

            }
        }

        applyAction(bestAction);
    }

    private int calculateActionValue(Pair<Action, Constituency> pair){
        return 0;
    }
}
