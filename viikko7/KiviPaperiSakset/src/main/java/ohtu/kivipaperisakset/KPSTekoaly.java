package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {

    ITekoaly tekoaly;

    public KPSTekoaly(boolean paha) {
        if (paha) {
            tekoaly = new TekoalyParannettu(20);
        } else {
            tekoaly = new Tekoaly();
        }
    }

    @Override
    protected String toisenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(edellinenSiirto);
        return tokanSiirto;
    }
}