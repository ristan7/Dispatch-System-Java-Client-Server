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
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idStrucneSpreme");
            String naziv = rs.getString("nazivStrucneSpreme");
            TipStrucneSpreme tip = TipStrucneSpreme.valueOf(rs.getString("nazivTipaSpreme"));

            StrucnaSprema sprema = new StrucnaSprema(id, naziv, tip);
            lista.add(sprema);
        }

        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "strucna_sprema";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "idStrucneSpreme";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idStrucneSpreme = " + idStrucneSpreme;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (nazivStrucneSpreme, tipStrucneSpreme) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("'%s', %d",
                nazivStrucneSpreme,
                tip.ordinal()
        );
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return String.format(
                "nazivStrucneSpreme = '%s', tipStrucneSpreme = %d",
                nazivStrucneSpreme,
                tip.ordinal()
        );
    }

    @Override
    public String alijas() {
        return "ss";
    }

    @Override
    public String join() {
        return "JOIN tip_strucne_spreme ts ON ss.tipStrucneSpreme = ts.idTipaSpreme";
    }

    @Override
    public String uslov() {
        return "idStrucneSpreme = " + idStrucneSpreme;
    }

    @Override
    public String uslovZaSelect() {
        return "nazivTipaSpreme LIKE '%" + tip.name() + "%'";
    }

}
