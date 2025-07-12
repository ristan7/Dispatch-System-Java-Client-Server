/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.NalogZaTransportRobe;
import domen.Roba;
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
public class SistemskaOperacijaIzmeniNalogZaTransportRobe extends ApstraktnaSistemskaOperacija {

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

        if (noviNalog.getDatumIstovara() == null) {
            throw new Exception("Datum istovara nije unet!!");
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
            }
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        NalogZaTransportRobe nalog = (NalogZaTransportRobe) ado;
        DBBroker.getInstance().update(nalog);

        System.out.println("Sada cu raditi stavke");

        StavkaNaloga stavka = new StavkaNaloga(nalog, 0, new Roba(-1, null, null, 0));

        ArrayList<StavkaNaloga> stavkeUBazi = (ArrayList<StavkaNaloga>) (ArrayList<?>) DBBroker.getInstance().select(stavka);
        System.out.println(stavkeUBazi);

        ArrayList<StavkaNaloga> stavkeKojeSeMenjaju = new ArrayList<>();
        ArrayList<StavkaNaloga> stavkeKojeSeBrisu = new ArrayList<>();
        ArrayList<StavkaNaloga> stavkeKojeSuNove = new ArrayList<>();

        if (nalog.getStavke().size() != 0) {
            for (StavkaNaloga stavkaUBazi : stavkeUBazi) {
                if (!nalog.getStavke().contains(stavkaUBazi)) {
                    stavkeKojeSeBrisu.add(stavkaUBazi);
                    System.out.println("Brisace se stavka: " + stavkaUBazi);
                }
            }

            for (StavkaNaloga stavkaUNalogu : nalog.getStavke()) {
                if (!stavkeUBazi.contains(stavkaUNalogu)) {
                    stavkeKojeSuNove.add(stavkaUNalogu);
                    System.out.println("Stavka koja je nova:" + stavkaUNalogu);
                } else {
                    stavkeKojeSeMenjaju.add(stavkaUNalogu);
                    System.out.println("Menja se stavka: " + stavkaUNalogu);
                }
            }

            for (StavkaNaloga novaStavka : stavkeKojeSuNove) {
                int brojUbacene = DBBroker.getInstance().insert(novaStavka);
                System.out.println(brojUbacene);
            }

            for (StavkaNaloga stavkaZaBrisanje : stavkeKojeSeBrisu) {
                DBBroker.getInstance().delete(stavkaZaBrisanje);
                System.out.println("Obrisana");
            }

            for (StavkaNaloga stavkaZaMenjanje : stavkeKojeSeMenjaju) {
                DBBroker.getInstance().update(stavkaZaMenjanje);
                System.out.println("Promenjeno");
            }

        } else {
            System.out.println("Nema stavki");
            DBBroker.getInstance().delete(stavka);

        }

    }

}
