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
public class StavkaNaloga implements ApstraktniDomenskiObjekat {

    private NalogZaTransportRobe nalog;
    private int rb;
    private float kolicina;
    private float cenaPoJedinici;
    private float iznos;
    private Roba roba;

    public StavkaNaloga() {
    }

    public StavkaNaloga(NalogZaTransportRobe nalog, float kolicina, Roba roba) {
        this.nalog = nalog;
        this.kolicina = kolicina;
        this.roba = roba;
        postaviCenuPoJedinici();
        izracunajIznosStavke();
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
        if (Float.floatToIntBits(this.iznos) != Float.floatToIntBits(other.iznos)) {
            return false;
        }
        if (!Objects.equals(this.nalog, other.nalog)) {
            return false;
        }
        return Objects.equals(this.roba, other.roba);
    }

    @Override
    public String toString() {
        return "Kolicina: " + kolicina + " CenaPoJedinici: " + cenaPoJedinici + " Iznos: " + iznos;
    }

    private void postaviCenuPoJedinici() {
        cenaPoJedinici = roba.getCena();
    }

    private void izracunajIznosStavke() {
        iznos = kolicina * cenaPoJedinici;
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idDispecera = rs.getInt("idDispecera");
            String imeDispecera = rs.getString("imeDispecera");
            String prezimeDispecera = rs.getString("prezimeDispecera");
            String emailDispecera = rs.getString("emailDispecera");
            String telefonDispecera = rs.getString("telefonDispecera");
            String userName = rs.getString("korisnickoIme");
            String pass = rs.getString("lozinka");
            Rola rola = Rola.valueOf(rs.getString("nazivRole"));

            Dispecer d = new Dispecer(idDispecera, imeDispecera, prezimeDispecera, emailDispecera, telefonDispecera, userName, pass, rola);

            int idMesta = rs.getInt("idMesta");
            String nazivMesta = rs.getString("nazivMesta");
            String drzava = rs.getString("drzava");
            int postanskiBroj = rs.getInt("postanskiBroj");

            Mesto m = new Mesto(idMesta, nazivMesta, drzava, postanskiBroj);

            int idPoslovnogPartnera = rs.getInt("idPoslovnogPartnera");
            String nazivPoslovnogPartnera = rs.getString("nazivPartnera");
            String pib = rs.getString("pib");
            String adresaPoslovnogPartnera = rs.getString("adresaPartnera");
            String emailPoslovnogPartnera = rs.getString("emailPartnera");

            PoslovniPartner pp = new PoslovniPartner(idPoslovnogPartnera, nazivPoslovnogPartnera, pib, adresaPoslovnogPartnera, emailPoslovnogPartnera, m);

            int idNaloga = rs.getInt("idNaloga");

            java.sql.Date datumK = rs.getDate("datumKreiranja");
            java.util.Date datumKreiranja = new java.util.Date(datumK.getTime());

            java.sql.Date datumI = rs.getDate("datumIzvrsenja");
            java.util.Date datumIzvrsenja = new java.util.Date(datumI.getTime());

            String adresaUtovara = rs.getString("adresaUtovara");
            String adresaIstovara = rs.getString("adresaIstovara");
            StatusNaloga status = StatusNaloga.valueOf(rs.getString("nazivStatusa"));
            Float ukupanIznos = rs.getFloat("ukupanIznosPosla");

            NalogZaTransportRobe nalog = new NalogZaTransportRobe();
            nalog.setIdNaloga(idNaloga);
            nalog.setDatumKreiranja(datumKreiranja);
            nalog.setDatumIzvrsenja(datumIzvrsenja);
            nalog.setAdresaUtovara(adresaUtovara);
            nalog.setAdresaIstovara(adresaIstovara);
            nalog.setStatus(status);
            nalog.setUkupanIznosPosla(ukupanIznos);
            nalog.setDispecer(d);
            nalog.setPoslovniPartner(pp);
            nalog.setStavke(null);

            StavkaNaloga stavka = new StavkaNaloga();

            int rb = rs.getInt("rb");
            Float kolicina = rs.getFloat("kolicina");
            Float cenaPoJedinici = rs.getFloat("cenaPoJedinici");
            Float iznos = rs.getFloat("iznos");

            int idRobe = rs.getInt("idRobe");
            String nazivRobe = rs.getString("nazivRobe");

            JedinicaMere jedinicaMere = JedinicaMere.valueOf(rs.getString("nazivJedinice_oznaka"));

            Float cena = rs.getFloat("cena");
            Roba r = new Roba(idRobe, nazivRobe, jedinicaMere, cena);

            stavka.setRb(rb);
            stavka.setKolicina(kolicina);
            stavka.setCenaPoJedinici(cenaPoJedinici);
            stavka.setIznos(iznos);
            stavka.setRoba(r);
            stavka.setNalog(nalog);

            lista.add(stavka);
        }

        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_naloga";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "nalog, rb";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nalog = " + nalog.getIdNaloga() + " AND rb = " + rb;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (nalog, kolicina, cenaPoJedinici, iznos, roba) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("%d, %.2f, %.2f, %.2f, %d",
                nalog.getIdNaloga(),
                kolicina,
                cenaPoJedinici,
                iznos,
                roba.getIdRobe()
        );
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return String.format(
                "nalog = %d, kolicina = %.2f, cenaPoJedinici = %.2f, iznos = %.2f, roba = %d",
                nalog.getIdNaloga(),
                kolicina,
                cenaPoJedinici,
                iznos,
                roba.getIdRobe()
        );
    }

    @Override
    public String alijas() {
        return "sn";
    }

    @Override
    public String join() {
        return "JOIN nalog_za_transport_robe nr ON sn.nalog = nr.idNaloga "
                + "JOIN roba r ON sn.roba = r.idRobe "
                + "JOIN jedinica_mere jm ON r.jedinicaMere = jm.idJedinice "
                + "JOIN Dispecer d ON nr.dispecer = d.idDispecera "
                + "JOIN rola ro ON d.rola = ro.idRole "
                + "JOIN status_naloga st ON nr.status = st.idStatusaNaloga "
                + "JOIN poslovni_partner pp ON nr.poslovni_partner = pp.idPoslovnogPartnera "
                + "JOIN mesto m ON pp.mesto = m.idMesta";
    }

    @Override
    public String uslov() {
        return "sn.rb = " + rb;
    }

    @Override
    public String uslovZaSelect() {
        return "nr.idNaloga = " + nalog.getIdNaloga();
    }

}
