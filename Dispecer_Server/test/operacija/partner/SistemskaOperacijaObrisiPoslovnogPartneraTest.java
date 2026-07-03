/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.partner;

import domen.Mesto;
import domen.PoslovniPartner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author mikir
 */
public class SistemskaOperacijaObrisiPoslovnogPartneraTest {

    private SistemskaOperacijaObrisiPoslovnogPartnera so;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaObrisiPoslovnogPartnera();
    }

    @After
    public void tearDown() {
        so = null;
    }

    @Test(expected = Exception.class)
    public void testValidirajNijeInstancaPoslovnogPartnera() throws Exception {
        so.validiraj(new Mesto());
    }

    @Test
    public void testIzvrsiUspesnoBrisanjePartnera() throws Exception {
        SistemskaOperacijaDodajPartnera dodaj = new SistemskaOperacijaDodajPartnera();
        long broj = System.nanoTime() % 1000000000L;
        PoslovniPartner noviPartner = new PoslovniPartner();
        noviPartner.setNazivPartnera("Partner za brisanje " + broj);
        noviPartner.setPib(String.format("%09d", broj));
        noviPartner.setAdresaPartnera("Adresa za brisanje");
        noviPartner.setEmailPartnera("brisanje" + broj + "@test.com");
        noviPartner.setMesto(new Mesto(1, "Beograd", "Srbija", 11000));
        dodaj.templateIzvrsi(noviPartner);

        PoslovniPartner zaBrisanje = new PoslovniPartner();
        zaBrisanje.setIdPoslovnogPartnera(dodaj.getBrojDodatih());

        so.templateIzvrsi(zaBrisanje);

        assertTrue(so.isUspesno());
    }

    @Test(expected = Exception.class)
    public void testIzvrsiBrisanjePartneraKojiImaNaloge() throws Exception {
        PoslovniPartner partnerSaNalozima = new PoslovniPartner();
        partnerSaNalozima.setIdPoslovnogPartnera(1);

        so.templateIzvrsi(partnerSaNalozima);
    }
}
