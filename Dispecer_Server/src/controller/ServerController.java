/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Dispecer;
import domen.NalogZaTransportRobe;
import java.util.ArrayList;
import operacija.login.SistemskaOperacijaLogin;
import operacija.nalog.SistemskaOperacijaVratiNalogePoDatumu;

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

}
