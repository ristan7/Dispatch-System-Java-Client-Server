/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private Rola rola;

    public Dispecer() {
    }

    public Dispecer(int idDispecera, String imeDispecera, String prezimeDispecera, String emailDispecera, String telefonDispecera, String korisnickoIme, String lozinka, Rola rola) {
        this.idDispecera = idDispecera;
        this.imeDispecera = imeDispecera;
        this.prezimeDispecera = prezimeDispecera;
        this.emailDispecera = emailDispecera;
        this.telefonDispecera = telefonDispecera;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.rola = rola;
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

    public Rola getRola() {
        return rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
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
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idDispecera");
            String ime = rs.getString("imeDispecera");
            String prezime = rs.getString("prezimeDispecera");
            String email = rs.getString("emailDispecera");
            String telefon = rs.getString("telefonDispecera");
            String username = rs.getString("korisnickoIme");
            String pass = rs.getString("lozinka");
            Rola rola = Rola.valueOf(rs.getString("nazivRole"));

            Dispecer dispecer = new Dispecer(id, ime, prezime, email, telefon, username, pass, rola);
            lista.add(dispecer);
        }
        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "dispecer";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "idDispecera";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idDispecera = " + idDispecera;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (imeDispecera, prezimeDispecera, emailDispecera, telefonDispecera, korisnickoIme, lozinka, rola) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("'%s', '%s', '%s', '%s', '%s', '%s', %d", imeDispecera, prezimeDispecera, emailDispecera, telefonDispecera, korisnickoIme, lozinka, rola.ordinal());
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return String.format("imeDispecera = '%s', prezimeDispecera = '%s', emailDispecera = '%s', telefonDispecera = '%s', korisnickoIme = '%s', lozinka = '%s', rola = %d", imeDispecera, prezimeDispecera, emailDispecera, telefonDispecera, korisnickoIme, lozinka, rola.ordinal());
    }

    @Override
    public String alijas() {
        return "d";
    }

    @Override
    public String join() {
        return "JOIN rola ro ON d.rola = ro.idRole";
    }

    @Override
    public String uslov() {
        return "korisnickoIme = '" + korisnickoIme + "'";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

}
