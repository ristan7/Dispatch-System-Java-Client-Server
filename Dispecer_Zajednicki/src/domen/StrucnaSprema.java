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
public class StrucnaSprema implements ApstraktniDomenskiObjekat{
    private int idStrucneSpreme;
    private String nazivStrucneSpreme;
    private TipStrucneSpreme tip;

    public StrucnaSprema() {
    }

    public StrucnaSprema(int idStrucneSpreme, String nazivStrucneSpreme, TipStrucneSpreme tip) {
        this.idStrucneSpreme = idStrucneSpreme;
        this.nazivStrucneSpreme = nazivStrucneSpreme;
        this.tip = tip;
    }

    public int getIdStrucneSpreme() {
        return idStrucneSpreme;
    }

    public void setIdStrucneSpreme(int idStrucneSpreme) {
        this.idStrucneSpreme = idStrucneSpreme;
    }

    public String getNazivStrucneSpreme() {
        return nazivStrucneSpreme;
    }

    public void setNazivStrucneSpreme(String nazivStrucneSpreme) {
        this.nazivStrucneSpreme = nazivStrucneSpreme;
    }

    public TipStrucneSpreme getTip() {
        return tip;
    }

    public void setTip(TipStrucneSpreme tip) {
        this.tip = tip;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final StrucnaSprema other = (StrucnaSprema) obj;
        if (!Objects.equals(this.nazivStrucneSpreme, other.nazivStrucneSpreme)) {
            return false;
        }
        return this.tip == other.tip;
    }

    @Override
    public String toString() {
        return nazivStrucneSpreme;
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
