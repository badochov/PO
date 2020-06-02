package badocha.hubert;

public class OmnivorousVoter extends MinMaxTraitVoter {
    OmnivorousVoter(String name, String surname, int[] weights, String partyName) {
        super(name, surname, convertWeights(weights), partyName);
    }

    OmnivorousVoter(String name, String surname, int[] weights) {
        super(name, surname, convertWeights(weights));
    }

    private static int[][] convertWeights(int[] weights) {
        int[][] converted = new int[weights.length][2];
        for (int i = 0; i < weights.length; i++) {
            converted[i] = new int[]{i, weights[i]};
        }

        return converted;
    }
}
