/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Dispecer;
import domen.Mesto;
import domen.NalogZaTransportRobe;
import domen.PoslovniPartner;
import java.util.ArrayList;
import operacija.login.SistemskaOperacijaLogin;
import operacija.mesto.SistemskaOperacijaVratiMesta;
import operacija.nalog.SistemskaOperacijaVratiNalogePoDatumu;
import operacija.partner.SistemskaOperacijaDodajPartnera;
import operacija.partner.SistemskaOperacijaFiltrirajPoslovnePartnere;
import operacija.partner.SistemskaOperacijaIzmeniPoslovnogPartnera;
import operacija.partner.SistemskaOperacijaObrisiPoslovnogPartnera;
import operacija.partner.SistemskaOperacijaVratiPoslovnePartnere;

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

}
