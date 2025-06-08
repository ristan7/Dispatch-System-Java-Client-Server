/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.partner;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.PoslovniPartner;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaFiltrirajPoslovnePartnere extends ApstraktnaSistemskaOperacija {

    ArrayList<PoslovniPartner> lista;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Poslovni partner!!");
        }

        PoslovniPartner pp = (PoslovniPartner) ado;

        if (pp.getMesto() == null) {
            if ((pp.getNazivPartnera() == null || pp.getNazivPartnera().isBlank()) && (pp.getPib() == null || pp.getPib().isBlank())) {
                throw new Exception("Naziv partnera ili PIB su neophodni za pretragu partnera!");
            }
        } else {
            if ((pp.getMesto().getNazivMesta() == null || pp.getMesto().getNazivMesta().isBlank()) && (pp.getMesto().getDrzava() == null || pp.getMesto().getDrzava().isBlank())) {
                throw new Exception("Naziv mesta ili drzava su neophodni za pretragu partnera!");
            }
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        PoslovniPartner pp = (PoslovniPartner) ado;
        lista = (ArrayList<PoslovniPartner>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        if (lista.isEmpty()) {
            throw new Exception("Ne postoje partneri po zadatim kriterijumima");
        }
    }

    public ArrayList<PoslovniPartner> getLista() {
        return lista;
    }
}
