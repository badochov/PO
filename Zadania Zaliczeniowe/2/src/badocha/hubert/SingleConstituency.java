package badocha.hubert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingleConstituency {
    final private Map<String, ArrayList<Candidate>> candidates;
    final private ArrayList<Voter> voters;
    SingleConstituency(int size){
        candidates = new HashMap<>();
        voters = new ArrayList<>();
    }

    public void addCandidate(String p,Candidate c){
        ArrayList<Candidate> partyCandidates = candidates.getOrDefault(p, new ArrayList<>());
        partyCandidates.add(c);
        candidates.replace(p, partyCandidates);
    }

    public ArrayList<Candidate> getPartyCandidates(String p){
        return candidates.get(p);
    }

    public void addVoter(Voter v){
        voters.add(v);
    }
}
