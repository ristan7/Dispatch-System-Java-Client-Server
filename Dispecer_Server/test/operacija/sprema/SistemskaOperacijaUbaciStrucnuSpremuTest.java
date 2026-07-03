/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.sprema;

import baza.DBBroker;
import domen.StrucnaSprema;
import domen.TipStrucneSpreme;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author mikir
 */
public class SistemskaOperacijaUbaciStrucnuSpremuTest {

    private SistemskaOperacijaUbaciStrucnuSpremu so;
    private StrucnaSprema sprema;
    private int idNoveSpreme = -1;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaUbaciStrucnuSpremu();
        sprema = new StrucnaSprema();
    }

    @After
    public void tearDown() throws Exception {
        if (idNoveSpreme != -1) {
            StrucnaSprema zaBrisanje = new StrucnaSprema();
            zaBrisanje.setIdStrucneSpreme(idNoveSpreme);
            DBBroker.getInstance().delete(zaBrisanje);
            DBBroker.getInstance().getConnection().commit();
            idNoveSpreme = -1;
        }
        so = null;
        sprema = null;
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajeNazivStrucneSpreme() throws Exception {
        sprema.setNazivStrucneSpreme("");
        sprema.setTip(TipStrucneSpreme.SREDNJA);
        so.validiraj(sprema);
    }

    @Test(expected = Exception.class)
    public void testValidirajStrucnaSpremaVecPostoji() throws Exception {
        sprema.setNazivStrucneSpreme("Saobracajna skola");
        sprema.setTip(TipStrucneSpreme.SREDNJA);
        so.validiraj(sprema);
    }

    @Test
    public void testIzvrsiUspesnoDodavanjeStrucneSpreme() throws Exception {
        long broj = System.nanoTime() % 1000000L;
        sprema.setNazivStrucneSpreme("JUnit Test Sprema " + broj);
        sprema.setTip(TipStrucneSpreme.MASTER);

        so.templateIzvrsi(sprema);

        assertTrue(so.getBrojDodatih() > 0);
        idNoveSpreme = so.getBrojDodatih();
    }
}
