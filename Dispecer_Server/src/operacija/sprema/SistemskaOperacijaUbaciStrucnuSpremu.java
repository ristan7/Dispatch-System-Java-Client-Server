/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.sprema;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.StrucnaSprema;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaUbaciStrucnuSpremu extends ApstraktnaSistemskaOperacija {

    private int brojDodatih;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StrucnaSprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Strucna sprema!");
        }

        StrucnaSprema ss = (StrucnaSprema) ado;

        if (ss.getNazivStrucneSpreme() == null || ss.getNazivStrucneSpreme().isBlank()) {
            throw new Exception("Naziv strucne spreme mora biti unet!!");
        }

        ArrayList<StrucnaSprema> spreme = (ArrayList<StrucnaSprema>) (ArrayList<?>) DBBroker.getInstance().select(ss);

        for (StrucnaSprema sprema : spreme) {
            if (sprema.equals(ss)) {
                throw new Exception("Strucna sprema vec postoji!!");
            }
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        StrucnaSprema ss = (StrucnaSprema) ado;
        brojDodatih = DBBroker.getInstance().insert(ss);
    }

    public int getBrojDodatih() {
        return brojDodatih;
    }

}
