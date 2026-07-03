/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import controller.ServerController;
import domen.Dispecer;
import domen.Rola;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaLoginTest {

    private SistemskaOperacijaLogin so;
    private Dispecer dispecer;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaLogin();
        dispecer = new Dispecer();
    }

    @After
    public void tearDown() {
        so = null;
        dispecer = null;
        ServerController.getInstance().resetujListe();
    }

    @Test(expected = Exception.class)
    public void testValidirajProsledjenNullObjekat() throws Exception {
        so.validiraj(null);
    }

    @Test(expected = Exception.class)
    public void testValidirajPraznoKorisnickoIme() throws Exception {
        dispecer.setKorisnickoIme("");
        dispecer.setLozinka("anica");
        so.validiraj(dispecer);
    }

    @Test(expected = Exception.class)
    public void testValidirajPraznaLozinka() throws Exception {
        dispecer.setKorisnickoIme("anica");
        dispecer.setLozinka("");
        so.validiraj(dispecer);
    }

    @Test
    public void testValidirajIspravanObjekat() throws Exception {
        dispecer.setKorisnickoIme("anica");
        dispecer.setLozinka("anica");
        so.validiraj(dispecer);
        assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testValidirajDispecerVecUlogovan() throws Exception {
        Dispecer vecUlogovan = new Dispecer();
        vecUlogovan.setKorisnickoIme("anica");
        ServerController.getInstance().getUlogovaniDispeceri().add(vecUlogovan);

        dispecer.setKorisnickoIme("anica");
        dispecer.setLozinka("anica");
        so.validiraj(dispecer);
    }

    @Test
    public void testIzvrsiUspesnaPrijava() throws Exception {
        dispecer.setKorisnickoIme("anica");
        dispecer.setLozinka("anica");
        so.templateIzvrsi(dispecer);

        assertNotNull(so.getUlogovani());
        assertEquals("Ana", so.getUlogovani().getImeDispecera());
        assertEquals(Rola.ADMINISTRATOR, so.getUlogovani().getRola());
    }

    @Test(expected = Exception.class)
    public void testIzvrsiPogresnaLozinka() throws Exception {
        dispecer.setKorisnickoIme("anica");
        dispecer.setLozinka("pogresnaLozinka123");
        so.templateIzvrsi(dispecer);
    }

    @Test(expected = Exception.class)
    public void testIzvrsiNepostojeceKorisnickoIme() throws Exception {
        dispecer.setKorisnickoIme("nepostojeciKorisnik999");
        dispecer.setLozinka("bilokoja");
        so.templateIzvrsi(dispecer);
    }
}
