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
public class SistemskaOperacijaVratiSveNaloge extends ApstraktnaSistemskaOperacija {

    private ArrayList<NalogZaTransportRobe> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof NalogZaTransportRobe)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Nalog za transport robe!!");
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        lista = (ArrayList<NalogZaTransportRobe>) (ArrayList<?>) DBBroker.getInstance().vratiSve(ado);

        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public ArrayList<NalogZaTransportRobe> getLista() {
        return lista;
    }

}
