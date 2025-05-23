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
public class Dispecer implements ApstraktniDomenskiObjekat {

    private int idDispecera;
    private String imeDispecera;
    private String prezimeDispecera;
    private String emailDispecera;
    private String telefonDispecera;
    private String korisnickoIme;
    private String lozinka;

    public Dispecer() {
    }

    public Dispecer(int idDispecera, String imeDispecera, String prezimeDispecera, String emailDispecera, String telefonDispecera, String korisnickoIme, String lozinka) {
        this.idDispecera = idDispecera;
        this.imeDispecera = imeDispecera;
        this.prezimeDispecera = prezimeDispecera;
        this.emailDispecera = emailDispecera;
        this.telefonDispecera = telefonDispecera;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getIdDispecera() {
        return idDispecera;
    }

    public void setIdDispecera(int idDispecera) {
        this.idDispecera = idDispecera;
    }

    public String getImeDispecera() {
        return imeDispecera;
    }

    public void setImeDispecera(String imeDispecera) {
        this.imeDispecera = imeDispecera;
    }

    public String getPrezimeDispecera() {
        return prezimeDispecera;
    }

    public void setPrezimeDispecera(String prezimeDispecera) {
        this.prezimeDispecera = prezimeDispecera;
    }

    public String getEmailDispecera() {
        return emailDispecera;
    }

    public void setEmailDispecera(String emailDispecera) {
        this.emailDispecera = emailDispecera;
    }

    public String getTelefonDispecera() {
        return telefonDispecera;
    }

    public void setTelefonDispecera(String telefonDispecera) {
        this.telefonDispecera = telefonDispecera;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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
        final Dispecer other = (Dispecer) obj;
        if (!Objects.equals(this.imeDispecera, other.imeDispecera)) {
            return false;
        }
        if (!Objects.equals(this.prezimeDispecera, other.prezimeDispecera)) {
            return false;
        }
        return Objects.equals(this.emailDispecera, other.emailDispecera);
    }

    @Override
    public String toString() {
        return imeDispecera + " " + prezimeDispecera;
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
