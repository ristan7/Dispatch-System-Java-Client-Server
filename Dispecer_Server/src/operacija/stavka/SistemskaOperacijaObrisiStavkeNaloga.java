/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavka;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.StavkaNaloga;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaObrisiStavkeNaloga extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StavkaNaloga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Stavka naloga!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        StavkaNaloga stavka = (StavkaNaloga) ado;
        DBBroker.getInstance().delete(stavka);
    }
    
}
