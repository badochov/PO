package badocha.hubert.constituencies;

public abstract class AbstractConstituency implements Constituency{
    @Override public int getMandatesCount() {
        return getSize() / 10;
    }

    @Override public int getNumberOfVotes() {
        return getSize();
    }
}
