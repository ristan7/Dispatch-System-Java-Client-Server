/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mikir
 */
public class NalogZaTransportRobe implements ApstraktniDomenskiObjekat {

    private int idNaloga;
    private Date datumKreiranja;
    private Date datumIzvrsenja;
    private String adresaUtovara;
    private String adresaIstovara;
    private StatusNaloga status;
    private float ukupanIznosPosla;
    private Dispecer dispecer;
    private PoslovniPartner poslovniPartner;
    private List<StavkaNaloga> stavke;

    public NalogZaTransportRobe() {
        stavke = new LinkedList<>();
    }

    public NalogZaTransportRobe(int idNaloga, Date datumKreiranja, Date datumIzvrsenja, String adresaUtovara, String adresaIstovara, StatusNaloga status, float ukupanIznosPosla, Dispecer dispecer, PoslovniPartner poslovniPartner, List<StavkaNaloga> stavke) {
        stavke = new LinkedList<>();
        this.idNaloga = idNaloga;
        this.datumKreiranja = datumKreiranja;
        this.datumIzvrsenja = datumIzvrsenja;
        this.adresaUtovara = adresaUtovara;
        this.adresaIstovara = adresaIstovara;
        this.status = status;
        this.ukupanIznosPosla = ukupanIznosPosla;
        this.dispecer = dispecer;
        this.poslovniPartner = poslovniPartner;
        this.stavke = stavke;
    }

    public int getIdNaloga() {
        return idNaloga;
    }

    public void setIdNaloga(int idNaloga) {
        this.idNaloga = idNaloga;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Date getDatumIzvrsenja() {
        return datumIzvrsenja;
    }

    public void setDatumIzvrsenja(Date datumIzvrsenja) {
        this.datumIzvrsenja = datumIzvrsenja;
    }

    public String getAdresaUtovara() {
        return adresaUtovara;
    }

    public void setAdresaUtovara(String adresaUtovara) {
        this.adresaUtovara = adresaUtovara;
    }

    public String getAdresaIstovara() {
        return adresaIstovara;
    }

    public void setAdresaIstovara(String adresaIstovara) {
        this.adresaIstovara = adresaIstovara;
    }

    public StatusNaloga getStatus() {
        return status;
    }

    public void setStatus(StatusNaloga status) {
        this.status = status;
    }

    public float getUkupanIznosPosla() {
        return ukupanIznosPosla;
    }

    public void setUkupanIznosPosla(float ukupanIznosPosla) {
        this.ukupanIznosPosla = ukupanIznosPosla;
    }

    public Dispecer getDispecer() {
        return dispecer;
    }

    public void setDispecer(Dispecer dispecer) {
        this.dispecer = dispecer;
    }

    public PoslovniPartner getPoslovniPartner() {
        return poslovniPartner;
    }

    public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
        this.poslovniPartner = poslovniPartner;
    }

    public List<StavkaNaloga> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaNaloga> stavke) {
        this.stavke = stavke;
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
        final NalogZaTransportRobe other = (NalogZaTransportRobe) obj;
        if (Float.floatToIntBits(this.ukupanIznosPosla) != Float.floatToIntBits(other.ukupanIznosPosla)) {
            return false;
        }
        if (!Objects.equals(this.datumKreiranja, other.datumKreiranja)) {
            return false;
        }
        if (!Objects.equals(this.datumIzvrsenja, other.datumIzvrsenja)) {
            return false;
        }
        if (!Objects.equals(this.dispecer, other.dispecer)) {
            return false;
        }
        return Objects.equals(this.poslovniPartner, other.poslovniPartner);
    }

    @Override
    public String toString() {
        return "Nalog izmedju " + dispecer + " i " + poslovniPartner + ", zakljucen dana " + datumKreiranja + ", planiran da se izvrsi dana " + datumIzvrsenja;
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
