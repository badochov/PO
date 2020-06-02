package badocha.hubert;

import java.util.ArrayList;
import java.util.Map;

public class CandidateVoter extends Voter {
    private final String party;
    private final int pos;

    CandidateVoter(String name, String surname, String partyName, int position) {
        super(name, surname);
        party = partyName;
        pos = position - 1;
    }

    @Override
    public Candidate getVote(Map<String, ArrayList<Candidate>> candidates) {
        return candidates.get(party).get(pos);
    }
}
