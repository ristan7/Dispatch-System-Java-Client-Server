/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.ServerController;
import domen.Dispecer;
import domen.Mesto;
import domen.NalogZaTransportRobe;
import domen.PoslovniPartner;
import domen.Roba;
import domen.StrucnaSprema;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentskiZahtev;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.ServerskiOdgovor;
import komunikacija.VrstaOdgovora;

/**
 *
 * @author mikir
 */
public class ObradaKlijentskihZahteva extends Thread {

    private final Socket socket;
    private final Posiljalac posiljalac;
    private final Primalac primalac;

    public ObradaKlijentskihZahteva(Socket socket) throws IOException {
        this.socket = socket;
        this.posiljalac = new Posiljalac(socket);
        this.primalac = new Primalac(socket);

    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                KlijentskiZahtev zahtev = null;
                try {
                    zahtev = (KlijentskiZahtev) primalac.primi();
                } catch (Exception ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (zahtev == null) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                            .log(Level.WARNING, "Primljen je prazan zahtev.");
                    continue;
                }
                ServerskiOdgovor odgovor = obradiZahtev(zahtev);
                posiljalac.posalji(odgovor);
            } catch (IOException e) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                        .log(Level.WARNING, "Greška prilikom primanja ili obrade zahteva: " + e.getMessage(), e);
            }
        }
        zatvoriSoket();

    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev zahtev) {
        try {
            if (zahtev == null) {
                return new ServerskiOdgovor(null, -1, new Exception("Primljen je prazan zahtev."), VrstaOdgovora.NEUSPESNO);
            }

            switch (zahtev.getOperacija()) {

                case Operacija.LOGIN:
                    return obradiLoginOperaciju(zahtev);

                case Operacija.VRATI_NALOGE_PO_DATUMU:
                    return obradiVratiNalogePremaDatumu(zahtev);

                case Operacija.VRATI_MESTA:
                    return obradiVratiMesta(zahtev);

                case Operacija.DODAJ_PARTNERA:
                    return obradiDodajPartnera(zahtev);

                case Operacija.ODJAVI_DISPECERA:
                    return odjaviDispecera(zahtev);

                case Operacija.VRATI_PARTNERE:
                    return obradiVratiPoslovnePartnere(zahtev);

                case Operacija.FILTRIRAJ_PARTNERE:
                    return obradiFiltrirajPoslovnePartnere(zahtev);

                case Operacija.AZURIRAJ_PARTNERA:
                    return obradiAzurirajPartnera(zahtev);

                case Operacija.OBRISI_PARTNERA:
                    return obradiObrisiPartnera(zahtev);

                case Operacija.UBACI_SPREMU:
                    return obradiUbaciSpremu(zahtev);

                case Operacija.VRATI_DISPECERE:
                    return obradiVratiDispecere(zahtev);
                    
                case Operacija.VRATI_ROBU:
                    return obradiVratiRobu(zahtev);

                default:
                    return new ServerskiOdgovor(null, zahtev.getOperacija(), new Exception("Nije poznata operacija!"), VrstaOdgovora.NEUSPESNO);

            }
        } catch (Exception ex) {
            return new ServerskiOdgovor(null, zahtev.getOperacija(), ex, VrstaOdgovora.NEUSPESNO);
        }
    }

    public void zatvoriSoket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.INFO, "Soket je uspešno zatvoren.");
            }
        } catch (IOException e) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                    .log(Level.SEVERE, "Greška pri zatvaranju soketa.", e);
        }
    }

    private ServerskiOdgovor obradiLoginOperaciju(KlijentskiZahtev zahtev) throws Exception {
        Dispecer d = (Dispecer) zahtev.getParametar();
        Dispecer dispecer = ServerController.getInstance().login(d);
        return new ServerskiOdgovor(dispecer, Operacija.LOGIN, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiNalogePremaDatumu(KlijentskiZahtev zahtev) throws Exception {
        NalogZaTransportRobe nalog = (NalogZaTransportRobe) zahtev.getParametar();
        ArrayList<NalogZaTransportRobe> naloziPoDatumu = ServerController.getInstance().naloziPoDatumu(nalog);
        return new ServerskiOdgovor(naloziPoDatumu, Operacija.VRATI_NALOGE_PO_DATUMU, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiMesta(KlijentskiZahtev zahtev) throws Exception {
        Mesto mesto = new Mesto();
        ArrayList<Mesto> mesta = ServerController.getInstance().vratiMesta(mesto);
        return new ServerskiOdgovor(mesta, Operacija.VRATI_MESTA, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiDodajPartnera(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner partner = (PoslovniPartner) zahtev.getParametar();
        int brojDodatih = ServerController.getInstance().dodajPartnera(partner);
        return new ServerskiOdgovor(brojDodatih, Operacija.DODAJ_PARTNERA, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor odjaviDispecera(KlijentskiZahtev zahtev) throws Exception {
        Dispecer ulogovani = (Dispecer) zahtev.getParametar();
        boolean uspesno = ServerController.getInstance().odjaviDispecera(ulogovani);
        return new ServerskiOdgovor(uspesno, Operacija.ODJAVI_DISPECERA, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiPoslovnePartnere(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner pp = new PoslovniPartner();
        ArrayList<PoslovniPartner> partneri = ServerController.getInstance().vratiPartnere(pp);
        return new ServerskiOdgovor(partneri, Operacija.VRATI_PARTNERE, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiFiltrirajPoslovnePartnere(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner pp = (PoslovniPartner) zahtev.getParametar();
        ArrayList<PoslovniPartner> partneri = ServerController.getInstance().filtrirajPartnere(pp);
        return new ServerskiOdgovor(partneri, Operacija.FILTRIRAJ_PARTNERE, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiAzurirajPartnera(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner partner = (PoslovniPartner) zahtev.getParametar();
        ServerController.getInstance().azurirajPartnera(partner);
        return new ServerskiOdgovor(true, Operacija.AZURIRAJ_PARTNERA, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiObrisiPartnera(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner partner = (PoslovniPartner) zahtev.getParametar();
        ServerController.getInstance().obrisiPartnera(partner);
        return new ServerskiOdgovor(true, Operacija.OBRISI_PARTNERA, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiUbaciSpremu(KlijentskiZahtev zahtev) throws Exception {
        StrucnaSprema ss = (StrucnaSprema) zahtev.getParametar();
        int brojDodatih = ServerController.getInstance().dodajStrucnuSpremu(ss);
        return new ServerskiOdgovor(brojDodatih, Operacija.UBACI_SPREMU, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiDispecere(KlijentskiZahtev zahtev) throws Exception {
        Dispecer dispecer = new Dispecer();
        ArrayList<Dispecer> dispeceri = ServerController.getInstance().vratiDispecere(dispecer);
        return new ServerskiOdgovor(dispeceri, Operacija.VRATI_DISPECERE, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiRobu(KlijentskiZahtev zahtev) throws Exception {
        Roba r=new Roba();
        ArrayList<Roba> roba = ServerController.getInstance().vratiRobu(r);
        return new ServerskiOdgovor(roba, Operacija.VRATI_ROBU, null, VrstaOdgovora.USPESNO);
    }
}
