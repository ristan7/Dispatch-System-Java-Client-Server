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
import domen.StrucnaSprema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import operacija.dispecer.SistemskaOperacijaVratiDispecere;
import operacija.login.SistemskaOperacijaLogin;
import operacija.mesto.SistemskaOperacijaVratiMesta;
import operacija.nalog.SistemskaOperacijaDodajNalogZaTransportRobe;
import operacija.nalog.SistemskaOperacijaFiltrirajNalogeZaTransportRobe;
import operacija.nalog.SistemskaOperacijaIzmeniNalogZaTransportRobe;
import operacija.nalog.SistemskaOperacijaVratiNalogeZaTransportRobe;
import operacija.partner.SistemskaOperacijaDodajPartnera;
import operacija.partner.SistemskaOperacijaFiltrirajPoslovnePartnere;
import operacija.partner.SistemskaOperacijaIzmeniPoslovnogPartnera;
import operacija.partner.SistemskaOperacijaObrisiPoslovnogPartnera;
import operacija.partner.SistemskaOperacijaVratiPoslovnePartnere;
import operacija.roba.SistemskaOperacijaVratiRobu;
import operacija.sprema.SistemskaOperacijaUbaciStrucnuSpremu;

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

    public Map<Integer, Integer> dodajNalog(NalogZaTransportRobe noviNalog) throws Exception {
        Map<Integer, Integer> mapaDodatih = new HashMap<>();
        SistemskaOperacijaDodajNalogZaTransportRobe so = new SistemskaOperacijaDodajNalogZaTransportRobe();
        so.templateIzvrsi(noviNalog);
        mapaDodatih.put(1, so.getBrojDodatih());
        mapaDodatih.put(2, so.getBrojacStavki());
        return mapaDodatih;
    }

    public ArrayList<NalogZaTransportRobe> vratiNaloge(NalogZaTransportRobe nalog) throws Exception {
        SistemskaOperacijaVratiNalogeZaTransportRobe so = new SistemskaOperacijaVratiNalogeZaTransportRobe();
        so.templateIzvrsi(nalog);
        return so.getLista();
    }

    public ArrayList<NalogZaTransportRobe> filtrirajNaloge(NalogZaTransportRobe n) throws Exception {
        SistemskaOperacijaFiltrirajNalogeZaTransportRobe so = new SistemskaOperacijaFiltrirajNalogeZaTransportRobe();
        so.templateIzvrsi(n);
        return so.getLista();
    }

    public void azurirajNalog(NalogZaTransportRobe nalog) throws Exception {
        SistemskaOperacijaIzmeniNalogZaTransportRobe so = new SistemskaOperacijaIzmeniNalogZaTransportRobe();
        so.templateIzvrsi(nalog);
    }

    public void resetujListe() {
        ulogovaniDispeceri.clear();
    }
}
