/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mikir
 */
public class Roba implements ApstraktniDomenskiObjekat {

    private int idRobe;
    private String nazivRobe;
    private JedinicaMere jedinicaMere;
    private float cena;

    public Roba() {
    }

    public Roba(int idRobe, String nazivRonbe, JedinicaMere jedinicaMere, float cena) {
        this.idRobe = idRobe;
        this.nazivRobe = nazivRonbe;
        this.jedinicaMere = jedinicaMere;
        this.cena = cena;
    }

    public int getIdRobe() {
        return idRobe;
    }

    public void setIdRobe(int idRobe) {
        this.idRobe = idRobe;
    }

    public String getNazivRonbe() {
        return nazivRobe;
    }

    public void setNazivRonbe(String nazivRonbe) {
        this.nazivRobe = nazivRonbe;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Roba other = (Roba) obj;
        if (!Objects.equals(this.nazivRobe, other.nazivRobe)) {
            return false;
        }
        return Objects.equals(this.jedinicaMere, other.jedinicaMere);
    }

    @Override
    public String toString() {
        String jedinica[] = jedinicaMere.name().split("_");
        return nazivRobe + " (" + jedinica[1].toLowerCase() + ")";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idRobe = rs.getInt("idRobe");
            String nazivRobe = rs.getString("nazivRobe");
            float cena = rs.getFloat("cena");

            JedinicaMere jedinicaMere = JedinicaMere.valueOf(rs.getString("nazivJedinice_oznaka"));

            Roba roba = new Roba(idRobe, nazivRobe, jedinicaMere, cena);
            lista.add(roba);
        }
        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "roba";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "idRobe";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idRobe = " + idRobe;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (nazivRobe, jedinicaMere, cena) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("'%s', %d, %.2f",
                nazivRobe,
                jedinicaMere.ordinal(),
                cena
        );

    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return String.format(
                "nazivRobe = '%s', jedinicaMere = %d, cena = %.2f",
                nazivRobe,
                jedinicaMere.ordinal(),
                cena
        );
    }

    @Override
    public String alijas() {
        return "r";
    }

    @Override
    public String join() {
        return "JOIN jedinica_mere jm ON r.jedinicaMere = jm.idJedinice";
    }

    @Override
    public String uslov() {
        return "idRobe = " + idRobe;
    }

    @Override
    public String uslovZaSelect() {
        List<String> uslovi = new ArrayList<>();

        if (nazivRobe != null) {
            uslovi.add("nazivRobe = '" + nazivRobe + "'");
        }

        if (uslovi.isEmpty()) {
            System.out.println("Upozorenje: uslovZaPretragu() - Nema filtera, vraća se prazan string!");
            return "";
        }

        return String.join(" AND ", uslovi);
    }

}
