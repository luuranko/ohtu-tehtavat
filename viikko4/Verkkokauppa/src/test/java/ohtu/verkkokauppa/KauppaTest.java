package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k; 
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        when(viite.uusi()).thenReturn(2);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k = new Kauppa(varasto, pankki, viite); 
    }

    @Test
    public void aloitaAsiointiNollaaOstostenTiedot() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(5));           
    }

    @Test
    public void uusiViiteNumeroJokaiselleMaksutapahtumalle() {
        Viitegeneraattori mockViite = new Viitegeneraattori(); 
        Kauppa kauppa = new Kauppa(varasto, pankki, mockViite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(5));           
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("vili", "12345");
        verify(pankki).tilisiirto(eq("vili"), eq(2), eq("12345"), eq("33333-44455"), eq(10));           
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(5));   
    }

    @Test
    public void kaksiEriTuotettaOstetaan() {
        // määritellään että tuote numero 2 on suklaa jonka hinta on 3 ja saldo 5
         when(varasto.saldo(2)).thenReturn(5); 
         when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "suklaa", 3));
 
         // tehdään ostokset
         k.aloitaAsiointi();
         k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
         k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli suklaata
         k.tilimaksu("pekka", "12345");
 
         // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
         verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(8));    
    }

    @Test
    public void kaksiSamaaTuotettaOstetaan() {
       // määritellään että tuote numero 2 on suklaa jonka hinta on 3 ja saldo 5
         when(varasto.saldo(2)).thenReturn(5); 
         when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "suklaa", 3));
 
         // tehdään ostokset
         k.aloitaAsiointi();
         k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli suklaata
         k.lisaaKoriin(2);     // ostetaan uudestaan tuotetta numero 2 eli suklaata
         k.tilimaksu("pekka", "12345");
 
         // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
         verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(6));    
    }

    @Test
    public void kaksiEriTuotettaJoistaToinenLoppu() {
        // määritellään että tuote numero 2 on suklaa jonka hinta on 3 ja saldo 5
         when(varasto.saldo(2)).thenReturn(0); 
         when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "suklaa", 3));
 
         // tehdään ostokset
         k.aloitaAsiointi();
         k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
         k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli suklaata
         k.tilimaksu("pekka", "12345");
 
         // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
         verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(5));    
    }

    @Test
    public void ostostenPoistaminenKoristaToimii() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(5));
    }
}
