package cover;

public class Finite implements SubtractableFromQuerySet {
    private int a;
    private int r;
    private int max_value;

    @Override
    public QuerySet subtractFromQuerySet(QuerySet qs) {
        for (int i = 0; this.nthElement(i) <= qs.maxPossibleEl() && this.nthElement(i) > 0; i++) {
            qs.subtract(this.nthElement(i));
        }
        return qs;
    }

    Finite(int a, int r, int max_value) {
        this.a = a;
        this.r = r;
        this.max_value = max_value;
    }

    public int nthElement(int n) {
        int value = this.a + this.r * n;
        return value > this.max_value ? -1 : value;
    }

    @Override
    public int intersectionSizeWithQuerySet(QuerySet qs) {
        int size = 0;

        for (int i = 0; this.nthElement(i) <= qs.maxPossibleEl() && this.nthElement(i) > 0; i++) {
            size += qs.contains(this.nthElement(i)) ? 1 : 0;
        }
        return size;
    }
}