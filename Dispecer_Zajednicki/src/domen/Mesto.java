/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mikir
 */
public class Mesto implements ApstraktniDomenskiObjekat {

    private int idMesta;
    private String nazivMesta;
    private String drzava;
    private int postanskiBroj;

    public Mesto() {
    }

    public Mesto(int idMesta, String nazivMesta, String drzava, int postanskiBroj) {
        this.idMesta = idMesta;
        this.nazivMesta = nazivMesta;
        this.drzava = drzava;
        this.postanskiBroj = postanskiBroj;
    }

    public int getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(int idMesta) {
        this.idMesta = idMesta;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
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
        final Mesto other = (Mesto) obj;
        return this.postanskiBroj == other.postanskiBroj;
    }

    @Override
    public String toString() {
        return nazivMesta;
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idMesta");
            String naziv = rs.getString("nazivMesta");
            String drzava = rs.getString("drzava");
            int postanskiBroj = rs.getInt("postanskiBroj");

            Mesto mesto = new Mesto(id, naziv, drzava, postanskiBroj);
            lista.add(mesto);
        }
        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "idMesta";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idMesta = " + idMesta;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (nazivMesta, drzava, postanskiBroj) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "?, ?, ?";
    }

    @Override
    public ArrayList<Object> parametriZaInsert() {
        ArrayList<Object> parametri = new ArrayList<>();
        parametri.add(nazivMesta);
        parametri.add(drzava);
        parametri.add(postanskiBroj);
        return parametri;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "nazivMesta = ?, drzava = ?, postanskiBroj = ?";
    }

    @Override
    public ArrayList<Object> parametriZaUpdate() {
        return parametriZaInsert();
    }

    @Override
    public String alijas() {
        return "m";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String uslovZaSelect() {

        return "";
    }
}
