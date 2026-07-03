/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.partner;

import baza.DBBroker;
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
public class SistemskaOperacijaIzmeniPoslovnogPartneraTest {

    private SistemskaOperacijaIzmeniPoslovnogPartnera so;
    private PoslovniPartner partner;
    private int idTestPartnera;

    @Before
    public void setUp() throws Exception {
        so = new SistemskaOperacijaIzmeniPoslovnogPartnera();

        // Prvo kreiramo privremenog partnera nad kojim cemo raditi izmenu
        SistemskaOperacijaDodajPartnera dodaj = new SistemskaOperacijaDodajPartnera();
        long broj = System.nanoTime() % 1000000000L;
        PoslovniPartner noviPartner = new PoslovniPartner();
        noviPartner.setNazivPartnera("Partner za izmenu " + broj);
        noviPartner.setPib(String.format("%09d", broj));
        noviPartner.setAdresaPartnera("Stara adresa 1");
        noviPartner.setEmailPartnera("izmena" + broj + "@test.com");
        noviPartner.setMesto(new Mesto(1, "Beograd", "Srbija", 11000));
        dodaj.templateIzvrsi(noviPartner);

        idTestPartnera = dodaj.getBrojDodatih();

        partner = new PoslovniPartner();
        partner.setIdPoslovnogPartnera(idTestPartnera);
        partner.setNazivPartnera(noviPartner.getNazivPartnera());
        partner.setPib(noviPartner.getPib());
        partner.setAdresaPartnera("Stara adresa 1");
        partner.setEmailPartnera(noviPartner.getEmailPartnera());
        partner.setMesto(new Mesto(1, "Beograd", "Srbija", 11000));
    }

    @After
    public void tearDown() throws Exception {
        PoslovniPartner zaBrisanje = new PoslovniPartner();
        zaBrisanje.setIdPoslovnogPartnera(idTestPartnera);
        DBBroker.getInstance().delete(zaBrisanje);
        DBBroker.getInstance().getConnection().commit();
        so = null;
        partner = null;
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajeNazivPartnera() throws Exception {
        partner.setNazivPartnera("");
        so.validiraj(partner);
    }

    @Test(expected = Exception.class)
    public void testValidirajPIBNemaDevetCifara() throws Exception {
        partner.setPib("111");
        so.validiraj(partner);
    }

    @Test
    public void testIzvrsiUspesnaIzmenaPartnera() throws Exception {
        partner.setAdresaPartnera("Nova adresa 99");
        so.templateIzvrsi(partner);

        assertTrue(so.isUspesno());
    }
}
