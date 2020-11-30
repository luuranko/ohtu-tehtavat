package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka io) {
        super(tuloskentta, syotekentta, nollaa, undo, io);
    }

    
    public void suorita() {
        this.alkutila = Integer.parseInt(tuloskentta.getText());
        try {
            int luku = Integer.valueOf(syotekentta.getText());
            this.io.plus(luku);
            tuloskentta.setText("" + io.tulos());
            syotekentta.setText("");
        } catch (NumberFormatException e) {
        }
    }
    
}
