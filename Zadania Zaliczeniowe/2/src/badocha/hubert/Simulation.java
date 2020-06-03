package badocha.hubert;

import badocha.hubert.constituencies.Constituency;
import badocha.hubert.party.Party;
import badocha.hubert.voter.Voter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Simulation {
    private final Party[] parties;
    private final Constituency[] constituencies;

    Simulation(Party[] partiesInSimulation, Constituency[] constituenciesInSimulation) {
        parties = partiesInSimulation;
        constituencies = constituenciesInSimulation;
    }

    private static double valueAfterComa(double d) {
        return d - (int) d;
    }

    private static int comparePartyVotes(Map.Entry<String, Double> e1,
                                         Map.Entry<String, Double> e2) {
        return Double.compare(valueAfterComa(e1.getValue()), valueAfterComa(e1.getValue()));
    }

    public void simulate() {
        for (VoteCountingType voteCountingType : VoteCountingType.values()) {
            simulate(voteCountingType);
            System.out.println("===================================");
            System.out.println();
        }
    }

    private Party[] deepCloneParties(Constituency[] constituenciesClone) {
        return Arrays.stream(parties).map(p -> p.copy(constituenciesClone)).toArray(Party[]::new);
    }

    private Constituency[] deepCloneConstituencies() {
        return Arrays.stream(constituencies).map(Constituency::copy).toArray(Constituency[]::new);
    }

    public void simulate(VoteCountingType voteCountingType) {
        Constituency[] constituenciesClone = deepCloneConstituencies();
        Party[] partiesCopy = deepCloneParties(constituenciesClone);

        boolean canAnyPartyPerformOperation = true;
        while (canAnyPartyPerformOperation) {
            canAnyPartyPerformOperation = false;
            for (Party party : partiesCopy) {
                if (party.canPerformAction()) {
                    canAnyPartyPerformOperation = true;
                    party.performAction(voteCountingType);
                }
            }
        }

        displayResults(voteCountingType, constituencies);
    }

    private Map<String, Integer> getVotesForParties(Map<Candidate, ArrayList<Voter>> votes) {
        Map<String, Integer> partyVotes = new HashMap<>();
        votes.forEach((key, value) -> partyVotes.merge(key.getParty(), value.size(), Integer::sum));
        return partyVotes;
    }

    private Map<String, Integer> getNumberOfMandates(VoteCountingType voteCountingType,
                                                     Map<String, Integer> partyVotes,
                                                     Constituency constituency) {
        switch (voteCountingType) {
            case DHondt:
                return getNumberOfMandatesDHondt(partyVotes, constituency);
            case Sainte_Lague:
                return getNumberOfMandatesSainteLague(partyVotes, constituency);
            case Hare_Niemeyer:
                return getNumberOfMandatesHareNiemeyer(partyVotes, constituency);
        }
        return null;
    }

    private Map<String, Integer> getNumberOfMandatesDHondt(Map<String, Integer> partyVotes,
                                                           Constituency constituency) {
        return getNumberOfMandatesIterativeMethod(partyVotes, constituency, (i) -> i + 1);
    }

    private Map<String, Integer> getNumberOfMandatesSainteLague(
            Map<String, Integer> partyVotes,
            Constituency constituency) {
        return getNumberOfMandatesIterativeMethod(partyVotes, constituency, (i) -> 2 * i + 1);
    }

    private Map<String, Integer> getNumberOfMandatesIterativeMethod(Map<String, Integer> partyVotes,
                                                                    Constituency constituency,
                                                                    Function<Integer, Integer> f) {
        Map<String, Integer> mandates = new HashMap<>();
        Function<String, Integer> getCoefficient =
                (String name) -> partyVotes.getOrDefault(name, 0) /
                        f.apply(mandates.getOrDefault(name, 0));
        for (int i = 1; i <= constituency.getMandatesCount(); i++) {
            String partyNameMax = partyVotes.keySet().toArray(String[]::new)[0];
            for (String partyName : partyVotes.keySet()) {
                if (getCoefficient.apply(partyName) > getCoefficient.apply(partyNameMax)) {
                    partyNameMax = partyName;
                }
            }

            mandates.merge(partyNameMax, 1, Integer::sum);
        }


        return mandates;
    }

    private Map<String, Integer> getNumberOfMandatesHareNiemeyer(
            Map<String, Integer> partyVotes,
            Constituency constituency) {
        Map<String, Double> Qs = new HashMap<>();
        partyVotes.forEach(
                (party, votes) -> Qs.put(party,
                        ((double) constituency.getMandatesCount() * votes) /
                                constituency.getNumberOfVotes()));
        Map<String, Integer> mandates = new HashMap<>();
        Qs.forEach((party, Q) -> mandates.put(party, Q.intValue()));
        int sum = Qs.values().stream().mapToInt(Double::intValue).reduce(0, Integer::sum);
        if (sum < constituency.getMandatesCount()) {
            Qs.entrySet().stream().sorted(Simulation::comparePartyVotes)
                    .limit(constituency.getMandatesCount() - sum)
                    .forEach((e) -> mandates.merge(e.getKey(), 1, Integer::sum));
        }
        return mandates;
    }

    private void displayResults(VoteCountingType voteCountingType, Constituency[] constituencies) {
        System.out.println(voteCountingType);
        System.out.println();


        Map<String, Integer> results = new HashMap<>();
        for (Constituency constituency : constituencies) {
            var votes = constituency.getVotes();
            var constituencyResults =
                    getNumberOfMandates(voteCountingType, getVotesForParties(votes),
                            constituency);
            assert constituencyResults != null;
            constituencyResults
                    .forEach((key, value) -> results.merge(key, value, Integer::sum));

            System.out.println(constituency.getName());
            displayVotersAndTheirVotes(votes);
            displayCandidatesAndNumberOfVotes(votes);
            displayResults(constituencyResults);
            System.out.println("------------------------------------");
        }
        displayResults(results);


    }

    private void displayVotersAndTheirVotes(Map<Candidate, ArrayList<Voter>> votes) {
        System.out.println();
        System.out.println("Wyborcy i ich głosy:");
        System.out.println();
        for (var vote : votes.entrySet()) {
            for (Voter voter : vote.getValue()) {
                System.out.println(voter.toString() + " -> " + vote.getKey());
            }
        }
    }

    private void displayCandidatesAndNumberOfVotes(Map<Candidate, ArrayList<Voter>> votes) {
        System.out.println();
        System.out.println("Kandydaci i liczba głosów na nich:");
        System.out.println();
        for (var vote : votes.entrySet()) {
            System.out.println(vote.getKey() + " -> " + vote.getValue().size());
        }
    }


    private void displayResults(Map<String, Integer> results) {
        results.forEach(
                (partyName, score) -> System.out
                        .println(partyName + " -> " + score.toString()));
    }
}
