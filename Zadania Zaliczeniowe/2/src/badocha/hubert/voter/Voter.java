package badocha.hubert.voter;

import badocha.hubert.Candidate;
import badocha.hubert.Human;

import java.util.ArrayList;
import java.util.Map;

abstract public class Voter extends Human {
    Voter(String name, String surname) {
        super(name, surname);
    }

    abstract public Candidate getVote(Map<String, ArrayList<Candidate>> candidates);
}
