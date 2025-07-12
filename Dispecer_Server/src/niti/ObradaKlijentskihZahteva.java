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
import domen.StavkaNaloga;
import domen.StrucnaSprema;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Map;
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
                    //Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.INFO, "Soket je zatvoren, nije moguce primiti zahtev..");
                    return;
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
                return new ServerskiOdgovor(null, new Exception("Primljen je prazan zahtev."), VrstaOdgovora.NEUSPESNO);
            }

            switch (zahtev.getOperacija()) {

                case Operacija.LOGIN:
                    return obradiLoginOperaciju(zahtev);

//                case Operacija.VRATI_NALOGE_PO_DATUMU:
//                    return obradiVratiNalogePremaDatumu(zahtev);

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

                case Operacija.DODAJ_NALOG:
                    return obradiDodajNalog(zahtev);

                case Operacija.VRATI_SVE_NALOGE:
                    return obradiVratiSveNaloge(zahtev);

//                case Operacija.VRATI_NALOGE_ZA_ULOGOVANOG:
//                    return obradiVratiNalogeZaUlogovanog(zahtev);

                case Operacija.FILTRIRAJ_NALOGE:
                    return obradiFiltrirajNaloge(zahtev);

                case Operacija.AZURIRAJ_NALOG:
                    return obradiAzurirajNalog(zahtev);

                case Operacija.VRATI_NALOGE:
                    return obradiVratiNaloge(zahtev);

                default:
                    return new ServerskiOdgovor(null, new Exception("Nije poznata operacija!"), VrstaOdgovora.NEUSPESNO);

            }
        } catch (Exception ex) {
            return new ServerskiOdgovor(null, ex, VrstaOdgovora.NEUSPESNO);
        }
    }

    public void zatvoriSoket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.INFO, "Soket je uspesno zatvoren.");
            }
        } catch (IOException e) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                    .log(Level.SEVERE, "Greška pri zatvaranju soketa.", e);
        }
    }

    private ServerskiOdgovor obradiLoginOperaciju(KlijentskiZahtev zahtev) throws Exception {
        Dispecer d = (Dispecer) zahtev.getParametar();
        Dispecer dispecer = ServerController.getInstance().login(d);
        return new ServerskiOdgovor(dispecer, null, VrstaOdgovora.USPESNO);
    }

//    private ServerskiOdgovor obradiVratiNalogePremaDatumu(KlijentskiZahtev zahtev) throws Exception {
//        NalogZaTransportRobe nalog = (NalogZaTransportRobe) zahtev.getParametar();
//        ArrayList<NalogZaTransportRobe> naloziPoDatumu = ServerController.getInstance().naloziPoDatumu(nalog);
//        return new ServerskiOdgovor(naloziPoDatumu, null, VrstaOdgovora.USPESNO);
//    }

    private ServerskiOdgovor obradiVratiMesta(KlijentskiZahtev zahtev) throws Exception {
        Mesto mesto = new Mesto();
        ArrayList<Mesto> mesta = ServerController.getInstance().vratiMesta(mesto);
        return new ServerskiOdgovor(mesta, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiDodajPartnera(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner partner = (PoslovniPartner) zahtev.getParametar();
        int brojDodatih = ServerController.getInstance().dodajPartnera(partner);
        return new ServerskiOdgovor(brojDodatih, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor odjaviDispecera(KlijentskiZahtev zahtev) throws Exception {
        Dispecer ulogovani = (Dispecer) zahtev.getParametar();
        boolean uspesno = ServerController.getInstance().odjaviDispecera(ulogovani);
        return new ServerskiOdgovor(uspesno, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiPoslovnePartnere(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner pp = new PoslovniPartner();
        pp.setIdPoslovnogPartnera(-1);
        ArrayList<PoslovniPartner> partneri = ServerController.getInstance().vratiPartnere(pp);
        return new ServerskiOdgovor(partneri, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiFiltrirajPoslovnePartnere(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner pp = (PoslovniPartner) zahtev.getParametar();
        ArrayList<PoslovniPartner> partneri = ServerController.getInstance().filtrirajPartnere(pp);
        return new ServerskiOdgovor(partneri, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiAzurirajPartnera(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner partner = (PoslovniPartner) zahtev.getParametar();
        ServerController.getInstance().azurirajPartnera(partner);
        return new ServerskiOdgovor(true, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiObrisiPartnera(KlijentskiZahtev zahtev) throws Exception {
        PoslovniPartner partner = (PoslovniPartner) zahtev.getParametar();
        ServerController.getInstance().obrisiPartnera(partner);
        return new ServerskiOdgovor(true, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiUbaciSpremu(KlijentskiZahtev zahtev) throws Exception {
        StrucnaSprema ss = (StrucnaSprema) zahtev.getParametar();
        int brojDodatih = ServerController.getInstance().dodajStrucnuSpremu(ss);
        return new ServerskiOdgovor(brojDodatih, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiDispecere(KlijentskiZahtev zahtev) throws Exception {
        Dispecer dispecer = new Dispecer();
        ArrayList<Dispecer> dispeceri = ServerController.getInstance().vratiDispecere(dispecer);
        return new ServerskiOdgovor(dispeceri, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiRobu(KlijentskiZahtev zahtev) throws Exception {
        Roba r = new Roba();
        ArrayList<Roba> roba = ServerController.getInstance().vratiRobu(r);
        return new ServerskiOdgovor(roba, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiDodajNalog(KlijentskiZahtev zahtev) throws Exception {
        NalogZaTransportRobe noviNalog = (NalogZaTransportRobe) zahtev.getParametar();
//        ArrayList<Integer> brojeviDodatih = new ArrayList<>();

        Map<Integer, Integer> mapaDodatih = ServerController.getInstance().dodajNalog(noviNalog);
//        brojeviDodatih.add(brojDodatih);

//        noviNalog.setIdNaloga(brojDodatih);
//        if (noviNalog.getStavke().size() != 0) {
//            for (int i = 0; i < noviNalog.getStavke().size(); i++) {
//                noviNalog.getStavke().get(i).setNalog(noviNalog);
//            }
//            int brojDodatihStavki = ServerController.getInstance().dodajStavke(noviNalog.getStavke());
//
//            brojeviDodatih.add(brojDodatihStavki);
//        }
        return new ServerskiOdgovor(mapaDodatih, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiSveNaloge(KlijentskiZahtev zahtev) throws Exception {
        NalogZaTransportRobe nalog = new NalogZaTransportRobe();
        ArrayList<NalogZaTransportRobe> nalozi = ServerController.getInstance().vratiNaloge(nalog);
        return new ServerskiOdgovor(nalozi, null, VrstaOdgovora.USPESNO);
    }

//    private ServerskiOdgovor obradiVratiNalogeZaUlogovanog(KlijentskiZahtev zahtev) throws Exception {
//        NalogZaTransportRobe nalog = (NalogZaTransportRobe) zahtev.getParametar();
//        ArrayList<NalogZaTransportRobe> nalozi = ServerController.getInstance().vratiNalogeZaUlogovanog(nalog);
//        return new ServerskiOdgovor(nalozi, null, VrstaOdgovora.USPESNO);
//    }

    private ServerskiOdgovor obradiFiltrirajNaloge(KlijentskiZahtev zahtev) throws Exception {
        NalogZaTransportRobe n = (NalogZaTransportRobe) zahtev.getParametar();
        ArrayList<NalogZaTransportRobe> nalozi = ServerController.getInstance().filtrirajNaloge(n);
        return new ServerskiOdgovor(nalozi, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiAzurirajNalog(KlijentskiZahtev zahtev) throws Exception {
        NalogZaTransportRobe nalog = (NalogZaTransportRobe) zahtev.getParametar();
        ServerController.getInstance().azurirajNalog(nalog);

//        StavkaNaloga stavkaZaBrisanje = new StavkaNaloga(nalog, 0, new Roba());
//
//        System.out.println("Kreirao stavku");

//        ServerController.getInstance().obrisiStavkeNaloga(stavkaZaBrisanje);

//        if (nalog.getStavke().size() != 0) {
//
//            int brojDodatihStavki = ServerController.getInstance().dodajStavke(nalog.getStavke());
//        }
        return new ServerskiOdgovor(true, null, VrstaOdgovora.USPESNO);
    }

    private ServerskiOdgovor obradiVratiNaloge(KlijentskiZahtev zahtev) throws Exception {
        NalogZaTransportRobe nalog = (NalogZaTransportRobe) zahtev.getParametar();
        ArrayList<NalogZaTransportRobe> nalozi = ServerController.getInstance().vratiNaloge(nalog);
        return new ServerskiOdgovor(nalozi, null, VrstaOdgovora.USPESNO);
    }

}
