/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nalog;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.NalogZaTransportRobe;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaVratiNalogePoDatumu extends ApstraktnaSistemskaOperacija {

    private ArrayList<NalogZaTransportRobe> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof NalogZaTransportRobe)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Nalog za transport robe!!");
        }

        NalogZaTransportRobe n = (NalogZaTransportRobe) ado;

        if (n == null || n.getDatumKreiranja() == null || n.getDatumIzvrsenja() == null) {
            throw new Exception("Nalog, kao ni njegovi datumi nisu postojeci!!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        NalogZaTransportRobe n = (NalogZaTransportRobe) ado;

        //Formatiranje datuma
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(n.getDatumKreiranja());

        String uslov = String.format(
                "nr.dispecer = %d AND (nr.datumKreiranja = '%s' OR nr.datumIzvrsenja = '%s')",
                n.getDispecer().getIdDispecera(), d, d
        );

        lista = (ArrayList<NalogZaTransportRobe>) (ArrayList<?>) DBBroker.getInstance().selectZaUslov(ado, uslov);

        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public ArrayList<NalogZaTransportRobe> getLista() {
        return lista;
    }

}
