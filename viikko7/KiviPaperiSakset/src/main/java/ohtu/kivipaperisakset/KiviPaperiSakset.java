package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperiSakset {
    private static final Scanner scanner = new Scanner(System.in);
    protected String edellinenSiirto;
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        
        String ekanSiirto = ensimmaisenSiirto();
        String tokanSiirto = toisenSiirto();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            ekanSiirto = ensimmaisenSiirto();
            edellinenSiirto = ekanSiirto;
            tokanSiirto = toisenSiirto();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected String ensimmaisenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    abstract protected String toisenSiirto();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected Scanner getScanner() {
        return KiviPaperiSakset.scanner;
    }
}
