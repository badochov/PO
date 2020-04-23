package cover;

import java.util.ArrayList;

public class SolutionSet implements SubtractableFromQuerySet {
    ArrayList<SubtractableFromQuerySet> elements;

    @Override
    public QuerySet subtractFromQuerySet(QuerySet qs) {
        for (SubtractableFromQuerySet el : this.elements) {
            el.subtractFromQuerySet(qs);
        }

        return qs;
    }

    @Override
    public int intersectionSizeWithQuerySet(QuerySet qs) {
        int size = 0;

        for (SubtractableFromQuerySet el : this.elements) {
            size += el.intersectionSizeWithQuerySet(qs);
        }

        return size;
    }

    SolutionSet() {
        this.elements = new ArrayList<>();
    }

    public SolutionSet addElement(SubtractableFromQuerySet el) {
        this.elements.add(el);

        return this;
    }


}
