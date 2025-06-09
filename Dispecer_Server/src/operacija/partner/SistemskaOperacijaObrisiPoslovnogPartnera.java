/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.partner;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.PoslovniPartner;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaObrisiPoslovnogPartnera extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Poslovni partner!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        PoslovniPartner p = (PoslovniPartner) ado;
        DBBroker.getInstance().delete(p);
    }

}
