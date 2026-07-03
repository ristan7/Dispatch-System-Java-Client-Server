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
public class SistemskaOperacijaDodajNalogZaTransportRobeTest {

    private SistemskaOperacijaDodajNalogZaTransportRobe so;
    private NalogZaTransportRobe nalog;
    private int idTestNaloga = -1;

    @Before
    public void setUp() {
        so = new SistemskaOperacijaDodajNalogZaTransportRobe();

        PoslovniPartner partner = new PoslovniPartner();
        partner.setIdPoslovnogPartnera(1); // DELHAIZE SERBIA - postojeci partner

        Dispecer dispecer = new Dispecer();
        dispecer.setIdDispecera(1); // Ana Anic - postojeci dispecer

        nalog = new NalogZaTransportRobe();
        nalog.setPoslovniPartner(partner);
        nalog.setDispecer(dispecer);
        nalog.setAdresaUtovara("Bulevar Kralja Aleksandra 73, Beograd");
        nalog.setAdresaIstovara("Bulevar Oslobodjenja 100, Novi Sad");
        nalog.setUkupanIznosPosla(0);
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
    public void testValidirajDatumUtovaraUProslosti() throws Exception {
        nalog.setDatumUtovara(danOd(-5));
        nalog.setDatumIstovara(danOd(5));
        so.validiraj(nalog);
    }

    @Test(expected = Exception.class)
    public void testValidirajDatumIstovaraPreDatumaUtovara() throws Exception {
        nalog.setDatumUtovara(danOd(10));
        nalog.setDatumIstovara(danOd(2));
        so.validiraj(nalog);
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajeAdresaUtovara() throws Exception {
        nalog.setAdresaUtovara("");
        nalog.setDatumUtovara(danOd(3));
        nalog.setDatumIstovara(danOd(7));
        so.validiraj(nalog);
    }

    @Test(expected = Exception.class)
    public void testValidirajNedostajePoslovniPartner() throws Exception {
        nalog.setPoslovniPartner(null);
        nalog.setDatumUtovara(danOd(3));
        nalog.setDatumIstovara(danOd(7));
        so.validiraj(nalog);
    }

    @Test(expected = Exception.class)
    public void testValidirajStavkaBezUneteKolicine() throws Exception {
        nalog.setDatumUtovara(danOd(3));
        nalog.setDatumIstovara(danOd(7));

        Roba roba = new Roba(1, "Mleko 2.8% mm", JedinicaMere.LITAR_L, 125);
        StavkaNaloga stavka = new StavkaNaloga(nalog, 0, roba); // kolicina = 0
        ArrayList<StavkaNaloga> stavke = new ArrayList<>();
        stavke.add(stavka);
        nalog.setStavke(stavke);

        so.validiraj(nalog);
    }

    @Test
    public void testIzvrsiUspesnoKreiranjeNaloga() throws Exception {
        nalog.setDatumUtovara(danOd(3));
        nalog.setDatumIstovara(danOd(7));
        nalog.setStatus(StatusNaloga.U_PRIPREMI);

        Roba roba = new Roba(1, "Mleko 2.8% mm", JedinicaMere.LITAR_L, 125);
        StavkaNaloga stavka = new StavkaNaloga(nalog, 10, roba);
        ArrayList<StavkaNaloga> stavke = new ArrayList<>();
        stavke.add(stavka);
        nalog.setStavke(stavke);
        nalog.izracunajUkupneTroskovePosla();

        so.templateIzvrsi(nalog);

        assertTrue(so.getBrojDodatih() > 0);
        assertTrue(so.getBrojacStavki() > 0);

        idTestNaloga = so.getBrojDodatih();
    }
}
