/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Dispecer;
import domen.NalogZaTransportRobe;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        return (Dispecer) so.getOdgovor();
    }

    public ArrayList<NalogZaTransportRobe> getNalogeZaDatum(NalogZaTransportRobe nalog) throws Exception {
        posaljiZahtev(Operacija.VRATI_NALOGE_PO_DATUMU, nalog);
        ServerskiOdgovor so = primiOdgovor();
        return (ArrayList<NalogZaTransportRobe>) so.getOdgovor();
    }
}
