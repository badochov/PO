package badocha.hubert.constituencies;

import badocha.hubert.Candidate;
import badocha.hubert.voter.CandidateVoter;
import badocha.hubert.voter.Voter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingleConstituency implements Constituency {
    final private Map<String, ArrayList<Candidate>> candidates;
    final private ArrayList<Voter> voters;
    final private int size;

    SingleConstituency(int constituencySize) {
        candidates = new HashMap<>();
        voters = new ArrayList<>();
        size = constituencySize;
    }

    public void addCandidate(String p, Candidate c) {
        ArrayList<Candidate> partyCandidates = candidates.getOrDefault(p, new ArrayList<>());
        partyCandidates.add(c);
        candidates.replace(p, partyCandidates);
    }

    @Override
    public ArrayList<Candidate> getPartyCandidates(String partyName) {
        return candidates.get(partyName);
    }

    public void addVoter(Voter v) {
        voters.add(v);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public ArrayList<Voter> getVoters() {
        return voters;
    }

    @Override
    public Map<String, ArrayList<Candidate>> getAllCandidates() {
        return candidates;
    }

    @Override
    public Map<Candidate, ArrayList<Voter>> getVotes() {
        return getVotes(candidates);
    }

    @Override
    public Map<Candidate, ArrayList<Voter>> getVotes(Map<String, ArrayList<Candidate>> allCandidates) {
        Map<Candidate, ArrayList<Voter>> votes = new HashMap<>();
        for (Voter voter : voters) {
            Candidate candidate;
            if (voter instanceof CandidateVoter) {
                candidate = voter.getVote(candidates);
            } else {
                candidate = voter.getVote(allCandidates);
            }
            ArrayList<Voter> prevVotes = votes.getOrDefault(candidate, new ArrayList<>());
            prevVotes.add(voter);
            votes.put(candidate, prevVotes);
        }
        return votes;
    }
}
