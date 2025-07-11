/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.NalogZaTransportRobe;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaIzmeniNalog extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof NalogZaTransportRobe)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Nalog za transport robe!");
        }

        NalogZaTransportRobe noviNalog = (NalogZaTransportRobe) ado;

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        sdf.setLenient(false);

        Date danasnjiDatum = null;
        try {
            danasnjiDatum = sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(SistemskaOperacijaDodajNalogZaTransportRobe.class.getName()).log(Level.INFO, "Neuspesno parsiranje danasnjeg datuma...");
            return;
        }

        if (noviNalog.getDatumIzvrsenja() == null) {
            throw new Exception("Datum izvrsenja nije unet!!");
        }
        if (noviNalog.getDatumIzvrsenja().before(noviNalog.getDatumKreiranja())) {
            throw new Exception("Datum izvrsenja mora biti pre datuma kreiranja naloga!!");
        }

        if (noviNalog.getAdresaUtovara() == null || noviNalog.getAdresaUtovara().isBlank()) {
            throw new Exception("Nije uneta adresa utovara!!");
        }

        if (noviNalog.getAdresaIstovara() == null || noviNalog.getAdresaIstovara().isBlank()) {
            throw new Exception("Nije uneta adresa istovara!!");
        }

        if (noviNalog.getPoslovniPartner() == null) {
            throw new Exception("Nije izabran poslovni partner!!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        NalogZaTransportRobe nalog = (NalogZaTransportRobe) ado;
        DBBroker.getInstance().update(nalog);
    }

}
