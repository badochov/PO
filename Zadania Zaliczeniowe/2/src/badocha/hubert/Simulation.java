package badocha.hubert;

import badocha.hubert.constituencies.Constituency;
import badocha.hubert.party.Party;

public class Simulation {
    private final Party[] parties;
    private final Constituency[] constituencies;

    Simulation(Party[] partiesInSimulation, Constituency[] constituenciesInSimulation) {
        parties = partiesInSimulation;
        constituencies = constituenciesInSimulation;
    }

    public void simulate() {
        for (VoteCountingType voteCountingType : VoteCountingType.values()) {
            simulate(voteCountingType);
        }
    }

    private Party[] deepCloneParties() {
        return parties;
    }

    private Constituency[] deepCloneConstituencies() {
        return constituencies;
    }

    public void simulate(VoteCountingType voteCountingType) {
        Party[] partiesCopy = deepCloneParties();
        Constituency[] constituenciesClone = deepCloneConstituencies();
        for (Party party : partiesCopy) {
            party.setConstituencies(constituenciesClone);
        }

        boolean canAnyPartyPerformOperation = true;
        while (canAnyPartyPerformOperation) {
            canAnyPartyPerformOperation = false;
            for (Party party : partiesCopy) {
                if (party.canPerformAction()) {
                    canAnyPartyPerformOperation = true;
                    party.performAction(voteCountingType);
                }
            }
        }

        countResults(voteCountingType, constituencies);
    }

    private void countResults(VoteCountingType voteCountingType, Constituency[] constituencies) {
        switch (voteCountingType) {
            case DHont:
                break;
            case Sainte_Lague:
                break;
            case Hare_Niemeyer:
                break;
        }
    }
}
