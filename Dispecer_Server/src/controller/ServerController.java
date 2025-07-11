/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Dispecer;
import domen.Mesto;
import domen.NalogZaTransportRobe;
import domen.PoslovniPartner;
import domen.Roba;
import domen.StavkaNaloga;
import domen.StrucnaSprema;
import java.util.ArrayList;
import operacija.dispecer.SistemskaOperacijaVratiDispecere;
import operacija.login.SistemskaOperacijaLogin;
import operacija.mesto.SistemskaOperacijaVratiMesta;
import operacija.nalog.SistemskaOperacijaDodajNalogZaTransportRobe;
import operacija.nalog.SistemskaOperacijaFiltrirajNaloge;
import operacija.nalog.SistemskaOperacijaIzmeniNalog;
import operacija.nalog.SistemskaOperacijaVratiNalogePoDatumu;
import operacija.nalog.SistemskaOperacijaVratiNalogeZaUlogovanog;
import operacija.nalog.SistemskaOperacijaVratiSveNaloge;
import operacija.partner.SistemskaOperacijaDodajPartnera;
import operacija.partner.SistemskaOperacijaFiltrirajPoslovnePartnere;
import operacija.partner.SistemskaOperacijaIzmeniPoslovnogPartnera;
import operacija.partner.SistemskaOperacijaObrisiPoslovnogPartnera;
import operacija.partner.SistemskaOperacijaVratiPoslovnePartnere;
import operacija.roba.SistemskaOperacijaVratiRobu;
import operacija.sprema.SistemskaOperacijaUbaciStrucnuSpremu;
import operacija.stavka.SistemskaOperacijaDodajStavkeNaloga;
import operacija.stavka.SistemskaOperacijaObrisiStavkeNaloga;

/**
 *
 * @author mikir
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Dispecer> ulogovaniDispeceri = new ArrayList<>();

    private ServerController() {

    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Dispecer> getUlogovaniDispeceri() {
        return ulogovaniDispeceri;
    }

    public void setUlogovaniDispeceri(ArrayList<Dispecer> ulogovaniDispeceri) {
        this.ulogovaniDispeceri = ulogovaniDispeceri;
    }

    public Dispecer login(Dispecer dispecer) throws Exception {
        SistemskaOperacijaLogin so = new SistemskaOperacijaLogin();
        so.templateIzvrsi(dispecer);
        return so.getUlogovani();
    }

    public ArrayList<NalogZaTransportRobe> naloziPoDatumu(NalogZaTransportRobe nalog) throws Exception {
        SistemskaOperacijaVratiNalogePoDatumu so = new SistemskaOperacijaVratiNalogePoDatumu();
        so.templateIzvrsi(nalog);
        return so.getLista();
    }

    public ArrayList<Mesto> vratiMesta(Mesto mesto) throws Exception {
        SistemskaOperacijaVratiMesta so = new SistemskaOperacijaVratiMesta();
        so.templateIzvrsi(mesto);
        return so.getLista();
    }

    public int dodajPartnera(PoslovniPartner partner) throws Exception {
        SistemskaOperacijaDodajPartnera so = new SistemskaOperacijaDodajPartnera();
        so.templateIzvrsi(partner);
        return so.getBrojDodatih();
    }

    public boolean odjaviDispecera(Dispecer ulogovani) throws Exception {

        try {
            for (int i = 0; i < ulogovaniDispeceri.size(); i++) {
                if (ulogovaniDispeceri.get(i).equals(ulogovani)) {
                    ulogovaniDispeceri.remove(i);
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            throw new Exception("Nije moguce odjaviti dispecera!!");
        }
    }

    public ArrayList<PoslovniPartner> vratiPartnere(PoslovniPartner pp) throws Exception {
        SistemskaOperacijaVratiPoslovnePartnere so = new SistemskaOperacijaVratiPoslovnePartnere();
        so.templateIzvrsi(pp);
        return so.getLista();

    }

    public ArrayList<PoslovniPartner> filtrirajPartnere(PoslovniPartner pp) throws Exception {
        SistemskaOperacijaFiltrirajPoslovnePartnere so = new SistemskaOperacijaFiltrirajPoslovnePartnere();
        so.templateIzvrsi(pp);
        return so.getLista();
    }

    public void azurirajPartnera(PoslovniPartner partner) throws Exception {
        SistemskaOperacijaIzmeniPoslovnogPartnera so = new SistemskaOperacijaIzmeniPoslovnogPartnera();
        so.templateIzvrsi(partner);
    }

    public void obrisiPartnera(PoslovniPartner partner) throws Exception {
        SistemskaOperacijaObrisiPoslovnogPartnera so = new SistemskaOperacijaObrisiPoslovnogPartnera();
        so.templateIzvrsi(partner);
    }

    public int dodajStrucnuSpremu(StrucnaSprema ss) throws Exception {
        SistemskaOperacijaUbaciStrucnuSpremu so = new SistemskaOperacijaUbaciStrucnuSpremu();
        so.templateIzvrsi(ss);
        return so.getBrojDodatih();
    }

    public ArrayList<Dispecer> vratiDispecere(Dispecer dispecer) throws Exception {
        SistemskaOperacijaVratiDispecere so = new SistemskaOperacijaVratiDispecere();
        so.templateIzvrsi(dispecer);
        return so.getLista();
    }

    public ArrayList<Roba> vratiRobu(Roba r) throws Exception {
        SistemskaOperacijaVratiRobu so = new SistemskaOperacijaVratiRobu();
        so.templateIzvrsi(r);
        return so.getLista();
    }

    public int dodajNalog(NalogZaTransportRobe noviNalog) throws Exception {
        SistemskaOperacijaDodajNalogZaTransportRobe so = new SistemskaOperacijaDodajNalogZaTransportRobe();
        so.templateIzvrsi(noviNalog);
        return so.getBrojDodatih();
    }

    public int dodajStavke(ArrayList<StavkaNaloga> stavke) throws Exception {
        int brojac = 0;

        for (StavkaNaloga stavkaNaloga : stavke) {
            SistemskaOperacijaDodajStavkeNaloga so = new SistemskaOperacijaDodajStavkeNaloga();
            so.templateIzvrsi(stavkaNaloga);
            brojac += so.getBrojDodatih();
        }
        return brojac;
    }

    public ArrayList<NalogZaTransportRobe> vratiNaloge(NalogZaTransportRobe nalog) throws Exception {
        SistemskaOperacijaVratiSveNaloge so = new SistemskaOperacijaVratiSveNaloge();
        so.templateIzvrsi(nalog);
        return so.getLista();
    }

    public ArrayList<NalogZaTransportRobe> vratiNalogeZaUlogovanog(NalogZaTransportRobe nalog) throws Exception {
        SistemskaOperacijaVratiNalogeZaUlogovanog so = new SistemskaOperacijaVratiNalogeZaUlogovanog();
        so.templateIzvrsi(nalog);
        return so.getLista();
    }

    public void resetujListe() {
        ulogovaniDispeceri.clear();
    }

    public ArrayList<NalogZaTransportRobe> filtrirajNaloge(NalogZaTransportRobe n) throws Exception {
        SistemskaOperacijaFiltrirajNaloge so = new SistemskaOperacijaFiltrirajNaloge();
        so.templateIzvrsi(n);
        return so.getLista();
    }

    public void azurirajNalog(NalogZaTransportRobe nalog) throws Exception {
        SistemskaOperacijaIzmeniNalog so = new SistemskaOperacijaIzmeniNalog();
        so.templateIzvrsi(nalog);
    }

    public void obrisiStavkeNaloga(StavkaNaloga stavkaZaBrisanje) throws Exception {
        System.out.println("Usao u brisanje!!");
        SistemskaOperacijaObrisiStavkeNaloga so = new SistemskaOperacijaObrisiStavkeNaloga();
        so.templateIzvrsi(stavkaZaBrisanje);
    }
}
