package badocha.hubert;

import java.util.ArrayList;
import java.util.Map;

public class MaxTraitVoter extends MinMaxTraitVoter {
    MaxTraitVoter(String name, String surname, int traitNumber, String partyName) {
        super(name, surname, new int[][]{{traitNumber, 1}}, partyName);
    }

    MaxTraitVoter(String name, String surname, int traitNumber) {
        super(name, surname, new int[][]{{traitNumber, 1}});
    }

}
