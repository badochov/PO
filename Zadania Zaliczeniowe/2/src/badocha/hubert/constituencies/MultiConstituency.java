package badocha.hubert.constituencies;

import badocha.hubert.Candidate;
import badocha.hubert.voter.Voter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiConstituency extends AbstractConstituency {
    private final ArrayList<Constituency> constituencies;

    public MultiConstituency(ArrayList<Constituency> mergedConstituencies) {
        constituencies = mergedConstituencies;
    }

    private static <T> ArrayList<T> mergeLists(ArrayList<T> l1, ArrayList<T> l2) {
        l1.addAll(l2);
        return l1;
    }

    @Override
    public Map<String, ArrayList<Candidate>> getAllCandidates() {
        Map<String, ArrayList<Candidate>> candidates = new HashMap<>();
        for (Constituency constituency : constituencies) {
            for (var partyCandidates : constituency.getAllCandidates().entrySet()) {
                candidates.merge(partyCandidates.getKey(), partyCandidates.getValue(),
                        MultiConstituency::mergeLists);
            }
        }
        return candidates;
    }

    @Override
    public ArrayList<Candidate> getPartyCandidates(String partyName) {
        ArrayList<Candidate> candidates = new ArrayList<>();
        for (Constituency constituency : constituencies) {
            candidates.addAll(constituency.getPartyCandidates(partyName));
        }
        return candidates;
    }

    @Override
    public Map<Candidate, ArrayList<Voter>> getVotes() {
        return getVotes(getAllCandidates());
    }

    @Override
    public Map<Candidate, ArrayList<Voter>> getVotes(
            Map<String, ArrayList<Candidate>> allCandidates) {
        Map<Candidate, ArrayList<Voter>> votes = new HashMap<>();
        for (Constituency constituency : constituencies) {
            for (var partyCandidates : constituency.getVotes(allCandidates).entrySet()) {
                votes.merge(partyCandidates.getKey(), partyCandidates.getValue(),
                        MultiConstituency::mergeLists);
            }
        }
        return votes;
    }

    @Override
    public int getSize() {
        return constituencies.stream().map(Constituency::getSize).reduce(0, Integer::sum);
    }

    @Override
    public ArrayList<Voter> getVoters() {
        return constituencies.stream().map(Constituency::getVoters)
                .reduce(new ArrayList<>(), MultiConstituency::mergeLists);
    }

    @Override public MultiConstituency copy() {
        return new MultiConstituency((ArrayList<Constituency>) constituencies.stream().map(
                Constituency::copy).collect(Collectors.toList()));
    }

    @Override public String getName() {
        final String[] name = {"("};
        constituencies.forEach(constituency -> name[0] += constituency.getName() + " + ");
        return name[0].substring(0, name[0].length() - 3) + ")";
    }

}
