/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Dispecer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private Object posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(operacija, parametar);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(zahtev);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor odgovor = (ServerskiOdgovor) in.readObject();

        if (odgovor.getVrstaOdgovora().equals(VrstaOdgovora.NEUSPESNO)) {
            throw new Exception(odgovor.getEx());
        } else {
            return odgovor.getOdgovor();
        }

    }

    public Dispecer login(Dispecer dispecer) throws Exception {
        return (Dispecer) posaljiZahtev(Operacija.LOGIN, dispecer);
    }
}
