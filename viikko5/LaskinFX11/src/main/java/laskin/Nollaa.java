package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka io) {
        super(tuloskentta, syotekentta, nollaa, undo, io);
    }

    @Override
    public void suorita() {
        System.out.println("Calling NULL");
        this.alkutila = Integer.parseInt(tuloskentta.getText());
        this.io.nollaa();
        tuloskentta.setText("" + io.tulos());
        syotekentta.setText("");
    }
}
