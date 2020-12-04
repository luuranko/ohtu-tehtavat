package statistics.matcher;

import statistics.Player;

public class All implements Matcher {

    @Override
    public boolean matches(Player p) {
        return true;
    }
    
    @Override
    public String toString() {
        String s = "All()";
        return s;
    }
}
