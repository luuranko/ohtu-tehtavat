package ohtuesimerkki;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ohtuesimerkki.Statistics;

public class StatisticsTest {
     
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  

    @Test
    public void lukija() {
        assertEquals(readerStub, stats.reader);
    }

    @Test
    public void pelaajanEtsintaOn() {
        assertEquals("Semenko", stats.search("Semenko").getName());
    }

    @Test
    public void pelaajanEtsintaEiOle() {
        assertEquals(null, stats.search("Lauri"));
    }

    @Test
    public void tiimiOn() {
        assertEquals(3, stats.team("EDM").size());
    }

    @Test
    public void tiimiEiOle() {
        assertEquals(0, stats.team("Lauri").size());
    }
    
    @Test
    public void parhaat() {
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }
}
