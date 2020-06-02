package badocha.hubert;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class PartyVoter extends Voter {
    private final String party;

    PartyVoter(String name, String surname, String partyName) {
        super(name, surname);
        party = partyName;
    }

    @Override
    public Candidate getVote(Map<String, ArrayList<Candidate>> candidates) {
        ArrayList<Candidate> partyCandidates = candidates.get(party);
        return partyCandidates.get(new Random().nextInt(partyCandidates.size()));
    }
}
