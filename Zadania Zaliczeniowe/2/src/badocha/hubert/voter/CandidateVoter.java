package badocha.hubert.voter;

import badocha.hubert.Candidate;

import java.util.ArrayList;
import java.util.Map;

public class CandidateVoter extends Voter {
    private final String party;
    private final int pos;

    public CandidateVoter(String name, String surname, String partyName, int position) {
        super(name, surname);
        party = partyName;
        pos = position - 1;
    }

    @Override
    public Candidate getVote(Map<String, ArrayList<Candidate>> candidates) {
        return candidates.get(party).get(pos);
    }
}
