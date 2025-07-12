/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.NalogZaTransportRobe;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaVratiNalogeZaTransportRobe extends ApstraktnaSistemskaOperacija {

    private ArrayList<NalogZaTransportRobe> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof NalogZaTransportRobe)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Nalog za transport robe!!");
        }

        NalogZaTransportRobe n = (NalogZaTransportRobe) ado;

        if (n.getIdNaloga() == -1 && n.getDispecer() == null) {
             throw new Exception("Nalog, kao ni dispecer koji ga je napravio nisu postojeci!!");
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        NalogZaTransportRobe n = (NalogZaTransportRobe) ado;
        
        lista = (ArrayList<NalogZaTransportRobe>) (ArrayList<?>) DBBroker.getInstance().select(n);

        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public ArrayList<NalogZaTransportRobe> getLista() {
        return lista;
    }

}
