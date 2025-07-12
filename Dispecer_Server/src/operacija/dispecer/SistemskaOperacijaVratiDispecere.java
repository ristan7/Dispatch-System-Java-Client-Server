/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.dispecer;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Dispecer;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaVratiDispecere extends ApstraktnaSistemskaOperacija {

    ArrayList<Dispecer> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Dispecer)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dispecer!!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        Dispecer d = (Dispecer) ado;
        lista = (ArrayList<Dispecer>) (ArrayList<?>) DBBroker.getInstance().select(d);

        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public ArrayList<Dispecer> getLista() {
        return lista;
    }

}
