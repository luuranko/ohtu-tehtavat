package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoRajapinta {
    public void lisaaTapahtuma(String tapahtuma);
    public ArrayList<String> getTapahtumat();
}
