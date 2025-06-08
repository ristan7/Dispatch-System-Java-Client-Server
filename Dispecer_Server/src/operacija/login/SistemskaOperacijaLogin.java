/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import baza.DBBroker;
import controller.ServerController;
import domen.ApstraktniDomenskiObjekat;
import domen.Dispecer;
import java.util.ArrayList;
import operacija.ApstraktnaSistemskaOperacija;

/**
 *
 * @author mikir
 */
public class SistemskaOperacijaLogin extends ApstraktnaSistemskaOperacija {

    Dispecer ulogovani;

    @Override
    protected void validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Dispecer)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dispecer!!");
        }

        Dispecer d = (Dispecer) ado;

        if (d.getKorisnickoIme().isEmpty() || d.getLozinka().isEmpty()) {
            throw new Exception("Korisnicko ime i lozinka moraju biti uneti!!");
        }

        for (Dispecer dispecer : ServerController.getInstance().getUlogovaniDispeceri()) {
            if (dispecer.getKorisnickoIme().equals(d.getKorisnickoIme())) {
                throw new Exception("Ovaj dispecer je vec ulogovan na sistem!!");
            }
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {

        Dispecer d = (Dispecer) ado;

        //Unchecked cast
        ArrayList<Dispecer> dispeceri = (ArrayList<Dispecer>) (ArrayList<?>) DBBroker.getInstance().vratiSve(ado);

        for (Dispecer dispecer : dispeceri) {
            if (dispecer.getKorisnickoIme().equals(d.getKorisnickoIme()) && dispecer.getLozinka().equals(d.getLozinka())) {
                ulogovani = dispecer;
                ServerController.getInstance().getUlogovaniDispeceri().add(dispecer);
                return;
            }
        }

        throw new Exception("Ne postoji dispecer sa tim login podacima!!");

    }

    public Dispecer getUlogovani() {
        return ulogovani;
    }

}
