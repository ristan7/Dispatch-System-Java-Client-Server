/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mikir
 */
public class StrucnaSprema implements ApstraktniDomenskiObjekat {

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
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiNazivTabele() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String alijas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String join() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String uslov() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String uslovZaSelect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
