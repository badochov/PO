package badocha.hubert;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Parser implements AutoCloseable {
    final private Scanner sc;

    Parser(String filename) throws FileNotFoundException {
        sc = new Scanner(new File(filename));
    }

    public int[] parseGeneralData() {
        int generalDataFields = 4;
        int[] data = new int[generalDataFields];
        for (int i = 0; i < generalDataFields; i++) {
            data[i] = sc.nextInt();
        }
        return data;
    }

    public int[][] parseMergedConstituencies() {
        int n = sc.nextInt();
        int[][] merged = new int[n][2];
        for (int i = 0; i < n; i++) {
            String mergedTuple = sc.next();
            mergedTuple = mergedTuple.substring(1, mergedTuple.length() - 1);
            merged[i] = Stream.of(mergedTuple.split(",")).mapToInt(Integer::parseInt).toArray();
        }
        return merged;
    }

    public String[] parsePartiesNames(int p) {
        String[] names = new String[p];
        for (int i = 0; i < p; i++) {
            names[i] = sc.next();
        }

        return names;
    }

    public int[] parsePartiesBudgets(int p) {
        int[] budgets = new int[p];
        for (int i = 0; i < p; i++) {
            budgets[i] = sc.nextInt();
        }
        return budgets;
    }

    public StrategyEnum[] parsePartiesStrategies(int p) {
        StrategyEnum[] budgets = new StrategyEnum[p];
        for (int i = 0; i < p; i++) {
            budgets[i] = StrategyEnum.valueOf(sc.next());
        }
        return budgets;
    }

    public int[] parseNumberOfVoters(int n) {
        int[] budgets = new int[n];
        for (int i = 0; i < n; i++) {
            budgets[i] = sc.nextInt();
        }
        return budgets;
    }

    public SingleConstituency[] parseConstituencies(int count, int traitsCount, int[] voters, int partiesCount) {
        SingleConstituency[] constituencies = new SingleConstituency[count];
        parseCandidates(constituencies, traitsCount, voters, partiesCount);
        parseNumberOfVoters(constituencies, traitsCount, voters);
        return constituencies;
    }

    private void parseCandidates(SingleConstituency[] constituencies, int traitsCount, int[] voters, int partiesCount) {
        for (int c = 0; c < constituencies.length; c++) {
            for (int p = 0; p < partiesCount; p++) {
                for (int v = 0; v < voters[c] / 10; v++) {
                    String name = sc.next();
                    String surname = sc.next();
                    sc.nextInt(); // redundant constituency number
                    String partyName = sc.next();
                    sc.nextInt(); // redundant position on list
                    int[] traits = new int[traitsCount];
                    for (int t = 0; t < traitsCount; t++) {
                        traits[t] = sc.nextInt();
                    }
                    Candidate candidate = new Candidate(name, surname, traits);
                    constituencies[c].addCandidate(partyName, candidate);
                }
            }
        }
    }

    public void parseNumberOfVoters(SingleConstituency[] constituencies, int traitsCount, int[] voters) {
        for (int c = 0; c < constituencies.length; c++) {
            for (int v = 0; v < voters[c]; v++) {
                String name = sc.next();
                String surname = sc.next();
                sc.nextInt(); // redundant constituency number
                VoterType type = VoterType.getVoterType(sc.nextInt());
                Voter voter;
                assert type != null;
                if (!type.usesTraits()) {
                    voter = getPartyOrCandidateVoter(name, surname, type);
                } else {
                    if (type.usesOneTrait()) {
                        voter = getSingleTraitVoter(name, surname, type);
                    } else {
                        voter = getOmnivorousVoter(name, surname, traitsCount, type);
                    }
                }
                constituencies[c].addVoter(voter);
            }
        }
    }

    public Action[] parseActions(int changeCount, int traitsCount) {
        Action[] actions = new Action[changeCount];
        for (int i = 0; i < changeCount; i++) {
            int[] traitsChanges = new int[traitsCount];
            for (int t = 0; t < traitsCount; t++) {
                traitsChanges[t] = sc.nextInt();
            }
            actions[i] = new Action(traitsChanges);
        }

        return actions;
    }

    private OmnivorousVoter getOmnivorousVoter(String name, String surname, int traitsCount, VoterType type) {
        int[] traits = new int[traitsCount];
        for (int i = 0; i < traitsCount; i++) {
            traits[i] = sc.nextInt();
        }
        if (type.isSinglePartyVoter()) {
            return new OmnivorousVoter(name, surname, traits, sc.next());
        }
        return new OmnivorousVoter(name, surname, traits);
    }

    private MinMaxTraitVoter getSingleTraitVoter(String name, String surname, VoterType type) {
        int trait = sc.nextInt();

        if (VoterType.isMin(type)) {
            if (type.isSinglePartyVoter()) {
                return new MinTraitVoter(name, surname, trait, sc.next());
            }
            return new MinTraitVoter(name, surname, trait);
        }
        if (type.isSinglePartyVoter()) {
            return new MaxTraitVoter(name, surname, trait, sc.next());
        }
        return new MaxTraitVoter(name, surname, trait);
    }

    private Voter getPartyOrCandidateVoter(String name, String surname, VoterType type) {
        String partyName = sc.next();
        if (type.isSingleCandidateVoter()) {
            return new CandidateVoter(name, surname, partyName, sc.nextInt());
        }
        return new PartyVoter(name, surname, partyName);
    }

    public void close() {
        sc.close();
    }
}
