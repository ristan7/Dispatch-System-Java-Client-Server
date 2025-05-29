/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mikir
 */
public class PoslovniPartner implements ApstraktniDomenskiObjekat {

    private int idPoslovnogPartnera;
    private String nazivPartnera;
    private int pib;
    private String adresaPartnera;
    private String emailPartnera;
    private Mesto mesto;

    public PoslovniPartner() {
    }

    public PoslovniPartner(int idPoslovnogPartnera, String nazivPartnera, int pib, String adresaPartnera, String emailPartnera, Mesto mesto) {
        this.idPoslovnogPartnera = idPoslovnogPartnera;
        this.nazivPartnera = nazivPartnera;
        this.pib = pib;
        this.adresaPartnera = adresaPartnera;
        this.emailPartnera = emailPartnera;
        this.mesto = mesto;
    }

    public int getIdPoslovnogPartnera() {
        return idPoslovnogPartnera;
    }

    public void setIdPoslovnogPartnera(int idPoslovnogPartnera) {
        this.idPoslovnogPartnera = idPoslovnogPartnera;
    }

    public String getNazivPartnera() {
        return nazivPartnera;
    }

    public void setNazivPartnera(String nazivPartnera) {
        this.nazivPartnera = nazivPartnera;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public String getAdresaPartnera() {
        return adresaPartnera;
    }

    public void setAdresaPartnera(String adresaPartnera) {
        this.adresaPartnera = adresaPartnera;
    }

    public String getEmailPartnera() {
        return emailPartnera;
    }

    public void setEmailPartnera(String emailPartnera) {
        this.emailPartnera = emailPartnera;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
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
        final PoslovniPartner other = (PoslovniPartner) obj;
        return this.pib == other.pib;
    }

    @Override
    public String toString() {
        return nazivPartnera;
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idPartnera = rs.getInt("idPoslovnogPartnera");
            String nazivPartnera = rs.getString("nazivPartnera");
            int pib = rs.getInt("pib");
            String adresaPartnera = rs.getString("adresaPartnera");
            String emailPartnera = rs.getString("emailPartnera");

            int idMesta = rs.getInt("idMesta");
            String nazivMesta = rs.getString("nazivMesta");
            String drzava = rs.getString("drzava");
            int postanskiBroj = rs.getInt("postanskiBroj");

            Mesto mesto = new Mesto(idMesta, nazivMesta, drzava, postanskiBroj);

            PoslovniPartner ps = new PoslovniPartner(idPartnera, nazivPartnera, pib, adresaPartnera, emailPartnera, mesto);
            lista.add(ps);
        }

        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "poslovni_partner";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "idPoslovnogPartnera";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idPoslovnogPartnera = " + idPoslovnogPartnera;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (nazivPartnera, pib, adresaPartnera, emailPartnera, mesto) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("'%s', %d, '%s', '%s', %d",
                nazivPartnera,
                pib,
                adresaPartnera,
                emailPartnera,
                mesto.getIdMesta()
        );

    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return String.format(
                "nazivPartnera = '%s', pib = %d, adresaPartnera = '%s', emailPartnera = '%s', mesto = %d",
                nazivPartnera,
                pib,
                adresaPartnera,
                emailPartnera,
                mesto.getIdMesta()
        );
    }

    @Override
    public String alijas() {
        return "pp";
    }

    @Override
    public String join() {
        return "JOIN mesto m ON pp.mesto = m.idMesta";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        StringBuilder uslov = new StringBuilder();

        if (nazivPartnera != null && !nazivPartnera.isEmpty()) {
            uslov.append("pp.nazivPartnera LIKE '%").append(nazivPartnera).append("%'");
        }
        if (pib > 0) {
            if (uslov.length() > 0) {
                uslov.append(" AND ");
            }
            uslov.append("pp.pib = ").append(pib);
        }
        if (mesto != null && mesto.getNazivMesta() != null && !mesto.getNazivMesta().isEmpty()) {
            if (uslov.length() > 0) {
                uslov.append(" AND ");
            }
            uslov.append("m.nazivMesta LIKE '%").append(mesto.getNazivMesta()).append("%'");
        }
        return uslov.toString();

    }

}
