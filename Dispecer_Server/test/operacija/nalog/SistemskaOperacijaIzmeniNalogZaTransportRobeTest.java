/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import baza.DBBroker;
import domen.Dispecer;
import domen.JedinicaMere;
import domen.NalogZaTransportRobe;
import domen.PoslovniPartner;
import domen.Roba;
import domen.StatusNaloga;
import domen.StavkaNaloga;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author mikir
 */
public class SistemskaOperacijaIzmeniNalogZaTransportRobeTest {

    private SistemskaOperacijaIzmeniNalogZaTransportRobe so;
    private NalogZaTransportRobe nalog;
    private int idTestNaloga = -1;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaIzmeniNalogZaTransportRobe();

        PoslovniPartner partner = new PoslovniPartner();
        partner.setIdPoslovnogPartnera(1);

        Dispecer dispecer = new Dispecer();
        dispecer.setIdDispecera(1);

        nalog = new NalogZaTransportRobe();
        nalog.setPoslovniPartner(partner);
        nalog.setDispecer(dispecer);
        nalog.setAdresaUtovara("Test adresa utovara 1");
        nalog.setAdresaIstovara("Test adresa istovara 1");
        nalog.setDatumUtovara(danOd(3));
        nalog.setDatumIstovara(danOd(7));
    }

    @After
    public void tearDown() throws Exception {
        if (idTestNaloga != -1) {
            Statement st = DBBroker.getInstance().getConnection().createStatement();
            st.executeUpdate("DELETE FROM stavka_naloga WHERE nalog = " + idTestNaloga);
            st.executeUpdate("DELETE FROM nalog_za_transport_robe WHERE idNaloga = " + idTestNaloga);
            DBBroker.getInstance().getConnection().commit();
            idTestNaloga = -1;
        }
        so = null;
        nalog = null;
    }

    private Date danOd(int brojDana) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, brojDana);
        return c.getTime();
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajeDatumUtovara() throws Exception {
        nalog.setDatumUtovara(null);
        so.validiraj(nalog);
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajeAdresaIstovara() throws Exception {
        nalog.setAdresaIstovara("");
        so.validiraj(nalog);
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajePoslovniPartner() throws Exception {
        nalog.setPoslovniPartner(null);
        so.validiraj(nalog);
    }

    @Test
    public void testValidirajDatumUtovaraMozeBitiUProslosti() throws Exception {
        nalog.setDatumUtovara(danOd(-10));
        so.validiraj(nalog);
        assertTrue(true);
    }

    @Test
    public void testIzvrsiUspesnaIzmenaNaloga() throws Exception {
        SistemskaOperacijaDodajNalogZaTransportRobe dodaj = new SistemskaOperacijaDodajNalogZaTransportRobe();

        NalogZaTransportRobe noviNalog = new NalogZaTransportRobe();
        noviNalog.setPoslovniPartner(nalog.getPoslovniPartner());
        noviNalog.setDispecer(nalog.getDispecer());
        noviNalog.setAdresaUtovara("Adresa pre izmene");
        noviNalog.setAdresaIstovara("Adresa istovara pre izmene");
        noviNalog.setDatumUtovara(danOd(3));
        noviNalog.setDatumIstovara(danOd(7));
        noviNalog.setStatus(StatusNaloga.U_PRIPREMI);

        Roba roba = new Roba(1, "Mleko 2.8% mm", JedinicaMere.LITAR_L, 125);
        StavkaNaloga stavka = new StavkaNaloga(noviNalog, 5, roba);
        ArrayList<StavkaNaloga> stavke = new ArrayList<>();
        stavke.add(stavka);
        noviNalog.setStavke(stavke);
        noviNalog.izracunajUkupneTroskovePosla();

        dodaj.templateIzvrsi(noviNalog);
        idTestNaloga = dodaj.getBrojDodatih();

        nalog.setIdNaloga(idTestNaloga);
        nalog.setAdresaUtovara("Nova adresa posle izmene");
        nalog.setStavke(new ArrayList<>());
        nalog.setStatus(StatusNaloga.U_TRANSPORTU);

        so.templateIzvrsi(nalog);

        assertTrue(so.isUspesno());
    }
}
