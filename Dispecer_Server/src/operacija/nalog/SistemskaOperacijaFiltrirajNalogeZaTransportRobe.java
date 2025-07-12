/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.NalogZaTransportRobe;
import domen.StavkaNaloga;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaFiltrirajNalogeZaTransportRobe extends ApstraktnaSistemskaOperacija {

    ArrayList<NalogZaTransportRobe> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof NalogZaTransportRobe)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Nalog za transport robe!!");
        }

        NalogZaTransportRobe n = (NalogZaTransportRobe) ado;

        if (n.getStavke().size() > 0) {
            for (StavkaNaloga stavkaNaloga : n.getStavke()) {
                if (stavkaNaloga.getRoba() == null) {
                    throw new Exception("Roba mora biti uneta da bi se izvrsila pretraga naloga po istoj!");
                }
            }
        } else if (n.getPoslovniPartner() != null) {
            if ((n.getPoslovniPartner().getNazivPartnera() == null || n.getPoslovniPartner().getNazivPartnera().isBlank()) && (n.getPoslovniPartner().getPib() == null || n.getPoslovniPartner().getPib().isBlank()) && (n.getPoslovniPartner().getMesto().getNazivMesta() == null || n.getPoslovniPartner().getMesto().getNazivMesta().isBlank()) && (n.getPoslovniPartner().getMesto().getDrzava() == null || n.getPoslovniPartner().getMesto().getDrzava().isBlank())) {
                throw new Exception("Naziv partnera, PIB, naziv mesta ili drzava su neophodni za pretragu naloga po partnerima!");
            }
        } else {
            if (n.getDatumUtovara()== null && n.getDatumIstovara()== null && n.getStatus() == null && n.getDispecer() == null) {
                throw new Exception("Datum kreiranja naloga, datum izvrsenja naloga, status naloga ili dispecer su neophodni za pretragu naloga!");
            }
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        NalogZaTransportRobe n = (NalogZaTransportRobe) ado;
        lista = (ArrayList<NalogZaTransportRobe>) (ArrayList<?>) DBBroker.getInstance().select(n);

        if (lista.isEmpty()) {
            throw new Exception("Ne postoje nalozi po zadatim kriterijumima");
        }
    }

    public ArrayList<NalogZaTransportRobe> getLista() {
        return lista;
    }

}
