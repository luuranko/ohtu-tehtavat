
package ohtu.intjoukkosovellus;


public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alustus(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alustus(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alustus(kapasiteetti, kasvatuskoko);
    }

    private void alustus(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            System.out.println("Syötä epänegatiivinen kapasiteetti ja kasvatuskoko");
            return;
        }
        taulukko = new int[kapasiteetti];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = 0;
        }
        this.kasvatuskoko = kasvatuskoko;
        alkioidenLkm = 0;
    }
    
    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        if (alkioidenLkm == taulukko.length) {
            int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
            taulukko = kopioiTaulukko(taulukko, uusiTaulukko, taulukko.length);
        }
        taulukko[alkioidenLkm] = luku;
        alkioidenLkm++;
        return true;
    }

    public boolean kuuluu(int luku) {
        boolean kuuluu = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                kuuluu = true;
                break;
            }
        }
        return kuuluu;
    }

    public boolean poista(int luku) {
        int ind = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                ind = i;
                taulukko[ind] = 0;
                break;
            }
        }
        if (ind != -1) {
            for (int i = ind; i < alkioidenLkm - 1; i++) {
                apu = taulukko[i];
                taulukko[i] = taulukko[i + 1];
                taulukko[i + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private int[] kopioiTaulukko(int[] vanha, int[] uusi, int raja) {
        for (int i = 0; i < raja; i++) {
            uusi[i] = vanha[i];
        }
        return uusi;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += taulukko[i];
            tuotos += ", ";
        }
        if (alkioidenLkm > 0) {
            tuotos += taulukko[alkioidenLkm - 1];
        }
        tuotos += "}";
        return tuotos;
    }
    

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        taulu = kopioiTaulukko(taulukko, taulu, taulu.length);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] eka = a.toIntArray();
        int[] toka = b.toIntArray();
        for (int i = 0; i < eka.length; i++) {
            x.lisaa(eka[i]);
        }
        for (int i = 0; i < toka.length; i++) {
            x.lisaa(toka[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] eka = a.toIntArray();
        int[] toka = b.toIntArray();
        for (int i = 0; i < eka.length; i++) {
            for (int j = 0; j < toka.length; j++) {
                if (eka[i] == toka[j]) {
                    y.lisaa(toka[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] eka = a.toIntArray();
        int[] toka = b.toIntArray();
        for (int i = 0; i < eka.length; i++) {
            z.lisaa(eka[i]);
        }
        for (int i = 0; i < toka.length; i++) {
            z.poista(toka[i]);
        }
        return z;
    }
        
}
