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
public class SistemskaOperacijaIzmeniPoslovnogPartnera extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pacijent!");
        }

        PoslovniPartner noviPartner = (PoslovniPartner) ado;

        if (noviPartner.getNazivPartnera() == null || noviPartner.getNazivPartnera().isBlank()) {
            throw new Exception("Naziv partnera nije unet!!");
        }

        if (noviPartner.getPib() != null) {
            if (!noviPartner.getPib().isBlank()) {
                if (noviPartner.getPib().length() != 9) {
                    throw new Exception("PIB partnera mora da ima tacno devet cifara!!");
                }
                for (char c : noviPartner.getPib().toCharArray()) {
                    if (!Character.isDigit(c)) {
                        throw new Exception("PIB partnera mora da sadrzi samo cifre");
                    }
                }
            }
        } else {
            throw new Exception("PIB partnera nije unet!!");
        }

        if (noviPartner.getAdresaPartnera() == null || noviPartner.getAdresaPartnera().isBlank()) {
            throw new Exception("Adresa partnera nije uneta!!");
        }

        if (noviPartner.getEmailPartnera() == null || noviPartner.getEmailPartnera().isBlank()) {
            throw new Exception("Email partnera nije unet!!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        PoslovniPartner p = (PoslovniPartner) ado;
        DBBroker.getInstance().update(p);
    }

}
