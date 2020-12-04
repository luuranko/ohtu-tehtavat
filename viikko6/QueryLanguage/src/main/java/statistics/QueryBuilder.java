package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    
    Matcher matcher;

    public QueryBuilder() {
        this.matcher = new All();
    }

    public Matcher build() {
        Matcher m = matcher;
        this.matcher = new All();
        return m;
    }

    public QueryBuilder playsIn(String joukkue) {
        Matcher and = new And(matcher, new PlaysIn(joukkue));
        this.matcher = and;
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        Matcher and = new And(matcher, new HasAtLeast(value, category));
        this.matcher = and;
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        Matcher and = new And(matcher, new HasFewerThan(value, category));
        this.matcher = and;
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        Matcher or = new Or(matchers);
        this.matcher = or;
        return this;
    }
}
