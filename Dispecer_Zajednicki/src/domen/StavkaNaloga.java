/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mikir
 */
public class StavkaNaloga implements ApstraktniDomenskiObjekat{

    private NalogZaTransportRobe nalog;
    private int rb;
    private float kolicina;
    private float cenaPoJedinici;
    private float iznos;
    private Roba roba;

    public StavkaNaloga() {
    }

    public StavkaNaloga(NalogZaTransportRobe nalog, int rb, float kolicina, float cenaPoJedinici, float iznos, Roba roba) {
        this.nalog = nalog;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cenaPoJedinici = cenaPoJedinici;
        this.iznos = iznos;
        this.roba = roba;
    }

    public NalogZaTransportRobe getNalog() {
        return nalog;
    }

    public void setNalog(NalogZaTransportRobe nalog) {
        this.nalog = nalog;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public float getKolicina() {
        return kolicina;
    }

    public void setKolicina(float kolicina) {
        this.kolicina = kolicina;
    }

    public float getCenaPoJedinici() {
        return cenaPoJedinici;
    }

    public void setCenaPoJedinici(float cenaPoJedinici) {
        this.cenaPoJedinici = cenaPoJedinici;
    }

    public float getIznos() {
        return iznos;
    }

    public void setIznos(float iznos) {
        this.iznos = iznos;
    }

    public Roba getRoba() {
        return roba;
    }

    public void setRoba(Roba roba) {
        this.roba = roba;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaNaloga other = (StavkaNaloga) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (Float.floatToIntBits(this.kolicina) != Float.floatToIntBits(other.kolicina)) {
            return false;
        }
        if (Float.floatToIntBits(this.cenaPoJedinici) != Float.floatToIntBits(other.cenaPoJedinici)) {
            return false;
        }
        if (Float.floatToIntBits(this.iznos) != Float.floatToIntBits(other.iznos)) {
            return false;
        }
        return Objects.equals(this.roba, other.roba);
    }

    @Override
    public String toString() {
        return "Kolicina: " + kolicina + " CenaPoJedinici: " + cenaPoJedinici + " Iznos: " + iznos;
    }

    @Override
    public String vratiNazivTabele() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
