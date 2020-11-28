package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka io) {
        super(tuloskentta, syotekentta, nollaa, undo, io);
    }

    @Override
    public void suorita() {
        System.out.println("Calling MINUS");
        this.alkutila = Integer.parseInt(tuloskentta.getText());
        try {
            int luku = Integer.valueOf(syotekentta.getText());
            this.io.miinus(luku);
            tuloskentta.setText("" + io.tulos());
            syotekentta.setText("");
        } catch (NumberFormatException e) {
        }
    }
}
