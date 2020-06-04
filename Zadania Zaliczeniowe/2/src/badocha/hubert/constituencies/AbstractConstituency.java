package badocha.hubert.constituencies;

import badocha.hubert.Votes;

public abstract class AbstractConstituency implements Constituency {
    @Override public int getMandatesCount() {
        return getSize() / 10;
    }

    @Override public int getNumberOfVotes() {
        return getSize();
    }

    @Override public Votes getVotes() {
        return getVotes(getAllCandidates());
    }
}
