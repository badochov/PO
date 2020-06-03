package badocha.hubert.party;

import badocha.hubert.Action;
import badocha.hubert.VoteCountingType;
import badocha.hubert.constituencies.Constituency;

public class RichParty extends MoneySensitiveParty {
    public RichParty(String partyName, int partyBudget, Action[] actionsAvailable,
                     Constituency[] constituenciesAvailable) {
        super(partyName, partyBudget, actionsAvailable, constituenciesAvailable);
    }

    @Override public void performAction(VoteCountingType voteCountingType) {
        performOperation(voteCountingType, -1);
    }
}
