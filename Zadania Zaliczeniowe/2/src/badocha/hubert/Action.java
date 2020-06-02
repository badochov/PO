package badocha.hubert;

public class Action {
    private final int[] changes;

    Action(int[] traitChanges) {
        changes = traitChanges;
    }

    public int getPrice() {
        int sum = 0;
        for (int change : changes) {
            sum += Math.abs(change);
        }
        return sum;
    }

    public int[] getChanges(){
        return changes;
    }

}
