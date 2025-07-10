/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavka;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.StavkaNaloga;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaDodajStavkeNaloga extends ApstraktnaSistemskaOperacija {

    private int brojDodatih;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StavkaNaloga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Stavka naloga!");
        }

        StavkaNaloga novaStavka = (StavkaNaloga) ado;

        if (novaStavka.getNalog() == null) {
            throw new Exception("Stavka nije povezana ni sa jednim nalogom");
        }

        if (novaStavka.getRoba() == null) {
            throw new Exception("Roba mora biti uneta");
        }

        if (novaStavka.getKolicina() == 0) {
            throw new Exception("Kolicina mora biti uneta!!");
        }

        ArrayList<StavkaNaloga> stavke = (ArrayList<StavkaNaloga>) (ArrayList<?>) DBBroker.getInstance().vratiSve(ado);

        for (StavkaNaloga stavka : stavke) {
            if (stavka.equals(novaStavka)) {
                throw new Exception("Stavka naloga vec postoji!!");
            }
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        StavkaNaloga novaStavka = (StavkaNaloga) ado;
        brojDodatih = DBBroker.getInstance().insert(novaStavka);
    }

    public int getBrojDodatih() {
        return brojDodatih;
    }

}
