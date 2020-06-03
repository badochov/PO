package badocha.hubert.constituencies;

import badocha.hubert.Candidate;
import badocha.hubert.voter.Voter;

import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractConstituency implements Constituency {
    @Override public int getMandatesCount() {
        return getSize() / 10;
    }

    @Override public int getNumberOfVotes() {
        return getSize();
    }

    @Override public Map<Candidate, ArrayList<Voter>> getVotes() {
        return getVotes(getAllCandidates());
    }
}
