/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Mesto;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaVratiMesta extends ApstraktnaSistemskaOperacija {

    ArrayList<Mesto> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Mesto)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mesto!!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        Mesto m = (Mesto) ado;
        
        lista = (ArrayList<Mesto>) (ArrayList<?>) DBBroker.getInstance().select(m);

        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public ArrayList<Mesto> getLista() {
        return lista;
    }

}
