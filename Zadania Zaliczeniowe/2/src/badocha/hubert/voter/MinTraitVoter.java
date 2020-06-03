package badocha.hubert.voter;

public class MinTraitVoter extends MinMaxTraitVoter {
    public MinTraitVoter(String name, String surname, int traitNumber, String partyName) {
        super(name, surname, new int[][]{{traitNumber, -1}}, partyName);
    }

    public MinTraitVoter(String name, String surname, int traitNumber) {
        super(name, surname, new int[][]{{traitNumber, -1}});
    }

    @Override public MinTraitVoter copy() {
        return new MinTraitVoter(name, surname, traitWeights[0][0], party);
    }
}
