/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.partner;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.PoslovniPartner;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaVratiPoslovnePartnere extends ApstraktnaSistemskaOperacija {
    ArrayList<PoslovniPartner> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Poslovni partner!!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        PoslovniPartner pp = (PoslovniPartner) ado;
        lista = (ArrayList<PoslovniPartner>) (ArrayList<?>) DBBroker.getInstance().select(pp);

        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public ArrayList<PoslovniPartner> getLista() {
        return lista;
    }
    
    
}
