package cover;

public interface SubtractableFromQuerySet {
    QuerySet subtractFromQuerySet(QuerySet qs);

    int intersectionSizeWithQuerySet(QuerySet qs);
}
