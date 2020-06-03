package badocha.hubert.voter;

import badocha.hubert.Candidate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public abstract class MinMaxTraitVoter extends Voter {
    private final int[][] traitWeights;
    private final String party;

    MinMaxTraitVoter(String name, String surname, int[][] weights, String partyName) {
        super(name, surname);
        traitWeights = weights;
        party = partyName;
    }

    MinMaxTraitVoter(String name, String surname, int[][] traitNumber) {
        this(name, surname, traitNumber, "");
    }

    private int getTraitsSum(Candidate c) {
        int sum = 0;
        for (int[] weight : traitWeights) {
            sum += c.getTrait(weight[0]) * weight[1];
        }
        return sum;
    }

    protected ArrayList<Candidate> getAscendingSortedByTraitCandidates(
            Map<String, ArrayList<Candidate>> candidates) {
        ArrayList<Candidate> availableCandidates;
        if (party.equals("")) {
            availableCandidates = new ArrayList<>();
            for (ArrayList<Candidate> c : candidates.values()) {
                availableCandidates.addAll(c);
            }
        } else {
            availableCandidates = candidates.get(party);
        }

        availableCandidates.sort(Comparator.comparingInt(this::getTraitsSum));

        return availableCandidates;
    }


    @Override
    public Candidate getVote(Map<String, ArrayList<Candidate>> candidates) {
        ArrayList<Candidate> sortedCandidates = getAscendingSortedByTraitCandidates(candidates);
        return sortedCandidates.get(sortedCandidates.size() - 1);
    }
}
