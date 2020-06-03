package badocha.hubert.party;

import badocha.hubert.Action;
import badocha.hubert.VoteCountingType;
import badocha.hubert.constituencies.Constituency;

import java.util.Random;

public class OwnStrategyParty extends Party {
    public OwnStrategyParty(String partyName, int partyBudget, Action[] actionsAvailable,
                            Constituency[] constituenciesAvailable) {
        super(partyName, partyBudget, actionsAvailable, constituenciesAvailable);
    }

    @Override public void performAction(VoteCountingType voteCountingType) {
        var availableActions = getAvailableActions();
        int index = new Random().nextInt(availableActions.size());
        applyAction(availableActions.get(index));
    }
}
