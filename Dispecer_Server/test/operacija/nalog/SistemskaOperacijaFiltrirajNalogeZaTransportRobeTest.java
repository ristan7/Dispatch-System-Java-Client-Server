/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import domen.Dispecer;
import domen.Mesto;
import domen.NalogZaTransportRobe;
import domen.PoslovniPartner;
import domen.StavkaNaloga;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author mikir
 */
public class SistemskaOperacijaFiltrirajNalogeZaTransportRobeTest {

    private SistemskaOperacijaFiltrirajNalogeZaTransportRobe so;
    private NalogZaTransportRobe kriterijum;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaFiltrirajNalogeZaTransportRobe();
        kriterijum = new NalogZaTransportRobe();
    }

    @After
    public void tearDown() {
        so = null;
        kriterijum = null;
    }

    @Test(expected = Exception.class)
    public void testValidirajBezIkakvihKriterijuma() throws Exception {
        so.validiraj(kriterijum);
    }

    @Test(expected = Exception.class)
    public void testValidirajStavkaBezRobe() throws Exception {
        StavkaNaloga stavka = new StavkaNaloga();
        stavka.setRoba(null);

        ArrayList<StavkaNaloga> stavke = new ArrayList<>();
        stavke.add(stavka);
        kriterijum.setStavke(stavke);

        so.validiraj(kriterijum);
    }

    @Test(expected = Exception.class)
    public void testValidirajPartnerBezKriterijuma() throws Exception {
        PoslovniPartner partner = new PoslovniPartner();
        partner.setMesto(new Mesto());
        kriterijum.setPoslovniPartner(partner);

        so.validiraj(kriterijum);
    }

    @Test
    public void testIzvrsiPretragaPoPartneru() throws Exception {
        PoslovniPartner partner = new PoslovniPartner();
        partner.setNazivPartnera("Delhaize");
        partner.setMesto(new Mesto());
        kriterijum.setPoslovniPartner(partner);

        so.templateIzvrsi(kriterijum);

        assertNotNull(so.getLista());
        assertTrue(so.getLista().size() >= 1);
    }

    @Test(expected = Exception.class)
    public void testIzvrsiPretragaBezRezultata() throws Exception {
        Dispecer dispecer = new Dispecer();
        dispecer.setIdDispecera(9999);

        kriterijum.setDispecer(dispecer);

        so.templateIzvrsi(kriterijum);
    }
}
