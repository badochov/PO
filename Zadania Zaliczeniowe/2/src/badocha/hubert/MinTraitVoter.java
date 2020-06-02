package badocha.hubert;

import java.util.ArrayList;
import java.util.Map;

public class MinTraitVoter extends MinMaxTraitVoter {
    MinTraitVoter(String name, String surname, int traitNumber, String partyName) {
        super(name, surname, new int[][]{{traitNumber, -1}}, partyName);
    }

    MinTraitVoter(String name, String surname, int traitNumber) {
        super(name, surname, new int[][]{{traitNumber, -1}});
    }
}
