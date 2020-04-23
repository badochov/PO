package cover;

public class Infinite implements SubtractableFromQuerySet {
    private int a;
    private int r;

    @Override
    public QuerySet subtractFromQuerySet(QuerySet qs) {
        for (int i = 0; this.nthElement(i) <= qs.maxPossibleEl(); i++) {
            qs.subtract(this.nthElement(i));
        }
        return qs;
    }

    @Override
    public int intersectionSizeWithQuerySet(QuerySet qs) {
        int size = 0;

        for (int i = 0; this.nthElement(i) <= qs.maxPossibleEl(); i++) {
            size += qs.contains(this.nthElement(i)) ? 1 : 0;
        }
        return size;
    }

    Infinite(int a, int r) {
        this.a = a;
        this.r = r;
    }

    public int nthElement(int n) {
        return this.a + this.r * n;
    }
}
