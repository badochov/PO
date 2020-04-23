package cover;

public class Element implements SubtractableFromQuerySet {
    int value;

    @Override
    public QuerySet subtractFromQuerySet(QuerySet qs) {
        return qs.subtract(this.value);
    }

    @Override
    public int intersectionSizeWithQuerySet(QuerySet qs) {
        return qs.contains(this.value) ? 1 : 0;
    }

    Element(int n) {
        this.value = n;
    }
}
