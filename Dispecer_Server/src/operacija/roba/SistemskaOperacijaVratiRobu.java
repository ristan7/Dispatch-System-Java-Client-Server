/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.roba;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Roba;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaVratiRobu extends ApstraktnaSistemskaOperacija {

    ArrayList<Roba> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Roba)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Roba!!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        lista = (ArrayList<Roba>) (ArrayList<?>) DBBroker.getInstance().vratiSve(ado);

        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public ArrayList<Roba> getLista() {
        return lista;
    }

}
