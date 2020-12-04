package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {

    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }
    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "Or(";
        for (Matcher m: matchers) {
            s += m.toString();
        }
        s += ")";
        return s;
    }
    
}