package ohtu.verkkokauppa;

public interface PankkiRajapinta {
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}