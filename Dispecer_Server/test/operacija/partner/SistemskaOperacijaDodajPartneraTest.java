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
public class SistemskaOperacijaDodajPartneraTest {

    private SistemskaOperacijaDodajPartnera so;
    private PoslovniPartner partner;
    private int idNovogPartnera = -1;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaDodajPartnera();
        partner = new PoslovniPartner();
        partner.setMesto(new Mesto(1, "Beograd", "Srbija", 11000));
    }

    @After
    public void tearDown() throws Exception {
        if (idNovogPartnera != -1) {
            PoslovniPartner zaBrisanje = new PoslovniPartner();
            zaBrisanje.setIdPoslovnogPartnera(idNovogPartnera);
            DBBroker.getInstance().delete(zaBrisanje);
            DBBroker.getInstance().getConnection().commit();
            idNovogPartnera = -1;
        }
        so = null;
        partner = null;
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajeNazivPartnera() throws Exception {
        partner.setNazivPartnera("");
        partner.setPib("123456789");
        partner.setAdresaPartnera("Adresa 1");
        partner.setEmailPartnera("test@test.com");
        so.validiraj(partner);
    }

    @Test(expected = Exception.class)
    public void testValidirajPIBNemaDevetCifara() throws Exception {
        partner.setNazivPartnera("Test Partner PIB");
        partner.setPib("12345");
        partner.setAdresaPartnera("Adresa 1");
        partner.setEmailPartnera("test@test.com");
        so.validiraj(partner);
    }

    @Test(expected = Exception.class)
    public void testValidirajPIBSaSlovima() throws Exception {
        partner.setNazivPartnera("Test Partner Slova");
        partner.setPib("12A456789");
        partner.setAdresaPartnera("Adresa 1");
        partner.setEmailPartnera("test@test.com");
        so.validiraj(partner);
    }

    @Test(expected = Exception.class)
    public void testValidirajNeispravanFormatEmaila() throws Exception {
        partner.setNazivPartnera("Test Partner Email");
        partner.setPib("123456780");
        partner.setAdresaPartnera("Adresa 1");
        partner.setEmailPartnera("nevalidanEmail.com");
        so.validiraj(partner);
    }

    @Test(expected = Exception.class)
    public void testValidirajPartnerVecPostoji() throws Exception {
        partner.setNazivPartnera("DELHAIZE SERBIA d.o.o.");
        partner.setPib("100032989");
        partner.setAdresaPartnera("Bilo koja adresa");
        partner.setEmailPartnera("test@test.com");
        partner.setMesto(new Mesto()); // prazno mesto - ne filtriramo po gradu/drzavi
        so.validiraj(partner);
    }

    @Test
    public void testIzvrsiUspesnoDodavanjePartnera() throws Exception {
        long broj = System.currentTimeMillis() % 1000000000L;
        partner.setNazivPartnera("JUnit Test Partner " + broj);
        partner.setPib(String.format("%09d", broj));
        partner.setAdresaPartnera("Test adresa 15");
        partner.setEmailPartnera("junittest" + broj + "@test.com");

        so.templateIzvrsi(partner);

        assertTrue(so.getBrojDodatih() > 0);
        idNovogPartnera = so.getBrojDodatih();
    }
}
