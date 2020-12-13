package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    @Override
    protected String toisenSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = getScanner().nextLine();
        return tokanSiirto;
    }
}