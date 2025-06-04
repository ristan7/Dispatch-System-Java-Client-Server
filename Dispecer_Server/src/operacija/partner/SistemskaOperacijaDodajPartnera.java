/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.partner;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Mesto;
import domen.PoslovniPartner;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaDodajPartnera extends ApstraktnaSistemskaOperacija {

    private int brojDodatih;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pacijent!");
        }

        PoslovniPartner noviPartner = (PoslovniPartner) ado;

        ArrayList<PoslovniPartner> partneri = (ArrayList<PoslovniPartner>) (ArrayList<?>) DBBroker.getInstance().selectSve(ado);

        for (PoslovniPartner poslovniPartner : partneri) {
            if (poslovniPartner.equals(noviPartner)) {
                throw new Exception("Poslovni partner vec postoji!!");
            }
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        PoslovniPartner p = (PoslovniPartner) ado;
        brojDodatih = DBBroker.getInstance().insert(p);
    }

    public int getBrojDodatih() {
        return brojDodatih;
    }

}
