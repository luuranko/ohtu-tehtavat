package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {

    Tekoaly tekoaly;

    public KPSTekoaly() {
        tekoaly = new Tekoaly();
    }

    @Override
    protected String toisenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(edellinenSiirto);
        return tokanSiirto;
    }
}