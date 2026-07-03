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
public class SistemskaOperacijaFiltrirajPoslovnePartnereTest {

    private SistemskaOperacijaFiltrirajPoslovnePartnere so;
    private PoslovniPartner kriterijum;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaFiltrirajPoslovnePartnere();
        kriterijum = new PoslovniPartner();
        kriterijum.setMesto(new Mesto());
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

    @Test
    public void testIzvrsiPretragaPoNazivuPartnera() throws Exception {
        kriterijum.setNazivPartnera("Delhaize");
        so.templateIzvrsi(kriterijum);

        assertNotNull(so.getLista());
        assertTrue(so.getLista().size() >= 1);
    }

    @Test(expected = Exception.class)
    public void testIzvrsiPretragaBezRezultata() throws Exception {
        kriterijum.setNazivPartnera("NepostojeciPartnerXYZ999");
        so.templateIzvrsi(kriterijum);
    }
}
