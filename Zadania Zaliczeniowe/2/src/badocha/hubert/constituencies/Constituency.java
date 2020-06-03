package badocha.hubert.constituencies;

import badocha.hubert.Candidate;
import badocha.hubert.voter.Voter;

import java.util.ArrayList;
import java.util.Map;

public interface Constituency {
    Map<String, ArrayList<Candidate>> getAllCandidates();

    ArrayList<Candidate> getPartyCandidates(String partyName);

    Map<Candidate, ArrayList<Voter>> getVotes();

    Map<Candidate, ArrayList<Voter>> getVotes(Map<String, ArrayList<Candidate>> allCandidates);

    int getSize();

    ArrayList<Voter> getVoters();

}
