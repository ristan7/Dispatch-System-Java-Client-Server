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
import java.io.IOException;
import java.util.ArrayList;
import komunikacija.KlijentskiZahtev;
import komunikacija.Operacija;
import komunikacija.ServerskiOdgovor;
import komunikacija.VrstaOdgovora;
import sesija.Sesija;

/**
 *
 * @author mikir
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public void posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(operacija, parametar);
        Sesija.getInstance().getOut().writeObject(zahtev);
        Sesija.getInstance().getOut().flush();
    }

    public ServerskiOdgovor primiOdgovor() throws IOException, ClassNotFoundException {
        return (ServerskiOdgovor) Sesija.getInstance().getIn().readObject();
    }

    public Dispecer login(Dispecer dispecer) throws Exception {
        posaljiZahtev(Operacija.LOGIN, dispecer);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (Dispecer) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom logovanja dispecera: " + so.getEx().getMessage());
            throw so.getEx();
        }

    }

    public ArrayList<NalogZaTransportRobe> getNalogeZaDatum(NalogZaTransportRobe nalog) throws Exception {
        posaljiZahtev(Operacija.VRATI_NALOGE_PO_DATUMU, nalog);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (ArrayList<NalogZaTransportRobe>) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom ucitavanja naloga za odredjeni datum: " + so.getEx().getMessage());
            throw so.getEx();
        }

    }

    public ArrayList<Mesto> getMesta() throws Exception {
        posaljiZahtev(Operacija.VRATI_MESTA, null);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (ArrayList<Mesto>) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom ucitavanja svih mesta: " + so.getEx().getMessage());
            throw so.getEx();
        }

    }

    public int dodajPartnera(PoslovniPartner noviPartner) throws Exception {
        posaljiZahtev(Operacija.DODAJ_PARTNERA, noviPartner);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (int) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom dodavanja novog partnera: " + so.getEx().getMessage());
            throw so.getEx();
        }

    }

    public boolean odjaviDispecera(Dispecer ulogovani) throws Exception {
        posaljiZahtev(Operacija.ODJAVI_DISPECERA, ulogovani);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (boolean) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom odjave dispecera: " + so.getEx().getMessage());
            throw so.getEx();
        }

    }

    public ArrayList<PoslovniPartner> getPoslovniPartneri() throws Exception {
        posaljiZahtev(Operacija.VRATI_PARTNERE, null);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (ArrayList<PoslovniPartner>) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom ucitavanja svih partnera: " + so.getEx().getMessage());
            throw so.getEx();
        }

    }

    public ArrayList<PoslovniPartner> filtrirajPartnere(PoslovniPartner pp) throws Exception {
        posaljiZahtev(Operacija.FILTRIRAJ_PARTNERE, pp);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            if (so.getOdgovor() == null) {
                throw new Exception();
            }
            return (ArrayList<PoslovniPartner>) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom filtriranja partnera: " + so.getEx().getMessage());
            throw so.getEx();
        }

    }

    public boolean azurirajPartnera(PoslovniPartner noviPartner) throws Exception {
        posaljiZahtev(Operacija.AZURIRAJ_PARTNERA, noviPartner);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (boolean) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom azuriranja partnera: " + so.getEx().getMessage());
            throw so.getEx();
        }
    }

    public boolean obrisiPartnera(PoslovniPartner pp) throws Exception {
        posaljiZahtev(Operacija.OBRISI_PARTNERA, pp);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (boolean) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom brisanja partnera: " + so.getEx().getMessage());
            throw so.getEx();
        }
    }

    public int dodajStrucnuSpremu(StrucnaSprema novaSprema) throws Exception {
        posaljiZahtev(Operacija.UBACI_SPREMU, novaSprema);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (int) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom ubacivanja strucne spreme: " + so.getEx().getMessage());
            throw so.getEx();
        }
    }

    public ArrayList<Dispecer> getDispeceri() throws Exception {
        posaljiZahtev(Operacija.VRATI_DISPECERE, null);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (ArrayList<Dispecer>) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom ucitavanja svih dispecera: " + so.getEx().getMessage());
            throw so.getEx();
        }
    }

    public ArrayList<Roba> getRoba() throws Exception {
        posaljiZahtev(Operacija.VRATI_ROBU, null);
        ServerskiOdgovor so = primiOdgovor();
        if (so.getVrstaOdgovora().equals(VrstaOdgovora.USPESNO)) {
            return (ArrayList<Roba>) so.getOdgovor();
        } else {
            System.err.println("Greska prilikom ucitavanja sve robe: " + so.getEx().getMessage());
            throw so.getEx();
        }
    }
}
