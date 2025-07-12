/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.NalogZaTransportRobe;
import domen.StavkaNaloga;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaDodajNalogZaTransportRobe extends ApstraktnaSistemskaOperacija {

    private int brojDodatih;
    private int brojacStavki = 0;

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

        if (noviNalog.getDatumUtovara() == null) {
            throw new Exception("Datum utovara nije unet!!");
        }

        if (danasnjiDatum.after(noviNalog.getDatumUtovara())) {
            throw new Exception("Datum utovara mora biti u buducnosti!!");
        }

        if (noviNalog.getDatumIstovara() == null) {
            throw new Exception("Datum istovara nije unet!!");
        }
        if (noviNalog.getDatumUtovara().after(noviNalog.getDatumIstovara())) {
            throw new Exception("Datum istovara mora biti posle datuma utovara ili isti dan!!");
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

        ArrayList<NalogZaTransportRobe> nalozi = (ArrayList<NalogZaTransportRobe>) (ArrayList<?>) DBBroker.getInstance().select(noviNalog);

        for (NalogZaTransportRobe nalog : nalozi) {
            if (nalog.equals(noviNalog)) {
                throw new Exception("Nalog za transport robe vec postoji!!");
            }
        }

        if (noviNalog.getStavke().size() != 0) {
            for (StavkaNaloga stavkaNaloga : noviNalog.getStavke()) {
                if (stavkaNaloga.getNalog() == null) {
                    throw new Exception("Stavka nije povezana ni sa jednim nalogom");
                }

                if (stavkaNaloga.getRoba() == null) {
                    throw new Exception("Roba mora biti uneta");
                }

                if (stavkaNaloga.getKolicina() == 0) {
                    throw new Exception("Kolicina mora biti uneta!!");
                }

                ArrayList<StavkaNaloga> stavke = (ArrayList<StavkaNaloga>) (ArrayList<?>) DBBroker.getInstance().select(stavkaNaloga);

                for (StavkaNaloga stavka : stavke) {
                    if (stavka.equals(stavkaNaloga)) {
                        throw new Exception("Stavka naloga vec postoji!!");
                    }
                }
            }
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        NalogZaTransportRobe noviNalog = (NalogZaTransportRobe) ado;
        brojDodatih = DBBroker.getInstance().insert(noviNalog);

        noviNalog.setIdNaloga(brojDodatih);
        if (noviNalog.getStavke().size() != 0) {
            for (int i = 0; i < noviNalog.getStavke().size(); i++) {
                noviNalog.getStavke().get(i).setNalog(noviNalog);
                int brojStavke = DBBroker.getInstance().insert(noviNalog.getStavke().get(i));
                brojacStavki += brojStavke;
            }

        }
    }

    public int getBrojDodatih() {
        return brojDodatih;
    }

    public int getBrojacStavki() {
        return brojacStavki;
    }

    public void setBrojacStavki(int brojacStavki) {
        this.brojacStavki = brojacStavki;
    }

}
