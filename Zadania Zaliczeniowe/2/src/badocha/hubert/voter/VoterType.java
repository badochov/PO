package badocha.hubert.voter;


public enum VoterType {
    PartyElectorate(VoterStyle.SingleParty, VoterTraits.No),
    CandidateElectorate(VoterStyle.SingleCandidate, VoterTraits.No),
    MinOneTraitMultiParty(VoterStyle.Multi, VoterTraits.Single),
    MaxOneTraitMultiParty(VoterStyle.Multi, VoterTraits.Single),
    OmnivorousMultiParty(VoterStyle.Multi, VoterTraits.Multi),
    MinOneTraitSingleParty(VoterStyle.SingleParty, VoterTraits.Single),
    MaxOneTraitSingleParty(VoterStyle.SingleParty, VoterTraits.Single),
    OmnivorousSingleParty(VoterStyle.SingleParty, VoterTraits.Multi);

    private final VoterStyle style;
    private final VoterTraits traits;

    VoterType(VoterStyle voterStyle, VoterTraits voterTraits) {
        style = voterStyle;
        traits = voterTraits;
    }

    public static boolean isMin(VoterType voterType) {
        return voterType == MinOneTraitMultiParty || voterType == MinOneTraitSingleParty;
    }

    public static VoterType getVoterType(int type) {
        switch (type) {
            case 1:
                return PartyElectorate;
            case 2:
                return CandidateElectorate;
            case 3:
                return MinOneTraitMultiParty;
            case 4:
                return MaxOneTraitMultiParty;
            case 5:
                return OmnivorousMultiParty;
            case 6:
                return MinOneTraitSingleParty;
            case 7:
                return MaxOneTraitSingleParty;
            case 8:
                return OmnivorousSingleParty;
            default:
                return null;
        }
    }

    public boolean isSinglePartyVoter() {
        return style != VoterStyle.Multi;
    }

    public boolean isSingleCandidateVoter() {
        return style == VoterStyle.SingleCandidate;
    }

    public boolean usesTraits() {
        return traits != VoterTraits.No;
    }

    public boolean usesOneTrait() {
        return traits == VoterTraits.Single;
    }
}

enum VoterStyle {
    SingleParty,
    SingleCandidate,
    Multi
}

enum VoterTraits {
    No,
    Single,
    Multi
}