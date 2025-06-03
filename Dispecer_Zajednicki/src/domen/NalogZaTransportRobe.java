/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private ArrayList<StavkaNaloga> stavke = new ArrayList<>();

    public NalogZaTransportRobe() {

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

    public void setStavke(ArrayList<StavkaNaloga> stavke) {
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

    public void izracunajUkupneTroskovePosla() {
        float ukupanIznos = 0;
        for (StavkaNaloga stavkaNaloga : stavke) {
            ukupanIznos += stavkaNaloga.getIznos();
        }
        ukupanIznosPosla = ukupanIznos;
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        //Prva varijanta

//        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
//
//        while (rs.next()) {
//            int idDispecera = rs.getInt("idDispecera");
//            String imeDispecera = rs.getString("imeDispecera");
//            String prezimeDispecera = rs.getString("prezimeDispecera");
//            String emailDispecera = rs.getString("emailDispecera");
//            String telefonDispecera = rs.getString("telefonDispecera");
//            String userName = rs.getString("korisnickoIme");
//            String pass = rs.getString("lozinka");
//
//            Dispecer d = new Dispecer(idDispecera, imeDispecera, prezimeDispecera, emailDispecera, telefonDispecera, userName, pass);
//
//            int idMesta = rs.getShort("lozinka");
//            String nazivMesta = rs.getString("nazivMesta");
//            String drzava = rs.getString("drzava");
//            int postanskiBroj = rs.getInt("postanskiBroj");
//
//            Mesto m = new Mesto(idMesta, nazivMesta, drzava, postanskiBroj);
//
//            int idPoslovnogPartnera = rs.getInt("idPoslovnogPartnera");
//            String nazivPoslovnogPartnera = rs.getString("nazivPartnera");
//            int pib = rs.getInt("pib");
//            String adresaPoslovnogPartnera = rs.getString("adresaPartnera");
//            String emailPoslovnogPartnera = rs.getString("emailPartnera");
//
//            PoslovniPartner pp = new PoslovniPartner(idPoslovnogPartnera, nazivPoslovnogPartnera, pib, adresaPoslovnogPartnera, emailPoslovnogPartnera, m);
//
//            int idNaloga = rs.getInt("idNaloga");
//
//            java.sql.Date datumK = rs.getDate("datumKreiranja");
//            java.util.Date datumKreiranja = new java.util.Date(datumK.getTime());
//
//            java.sql.Date datumI = rs.getDate("datumIzvrsenja");
//            java.util.Date datumIzvrsenja = new java.util.Date(datumI.getTime());
//
//            String adresaUtovara = rs.getString("adresaUtovara");
//            String adresaIstovara = rs.getString("adresaIstovara");
//            StatusNaloga status = StatusNaloga.valueOf("nazivStatusa");
//            Float ukupanIznos = rs.getFloat("ukupanIznosPosla");
//
//            NalogZaTransportRobe nalog = new NalogZaTransportRobe();
//            nalog.setIdNaloga(idNaloga);
//            nalog.setDatumKreiranja(datumKreiranja);
//            nalog.setDatumIzvrsenja(datumIzvrsenja);
//            nalog.setAdresaUtovara(adresaUtovara);
//            nalog.setAdresaIstovara(adresaIstovara);
//            nalog.setStatus(status);
//            nalog.setUkupanIznosPosla(ukupanIznos);
//            nalog.setDispecer(d);
//            nalog.setPoslovniPartner(pp);
//            nalog.setStavke(null);
//
//            lista.add(nalog);
//        }
        //Druga varijanta
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        Map<Integer, NalogZaTransportRobe> nalozi = new HashMap<>();

        while (rs.next()) {
            int idNaloga = rs.getInt("idNaloga");

            NalogZaTransportRobe nalog = nalozi.get(idNaloga);

            if (nalog == null) {

                nalog = new NalogZaTransportRobe();
                nalog.setIdNaloga(idNaloga);

                java.sql.Date datumK = rs.getDate("datumKreiranja");
                java.util.Date datumKreiranja = new java.util.Date(datumK.getTime());
                nalog.setDatumKreiranja(datumKreiranja);

                java.sql.Date datumI = rs.getDate("datumIzvrsenja");
                java.util.Date datumIzvrsenja = new java.util.Date(datumI.getTime());
                nalog.setDatumIzvrsenja(datumIzvrsenja);

                nalog.setAdresaUtovara(rs.getString("adresaUtovara"));
                nalog.setAdresaIstovara(rs.getString("adresaIstovara"));
                nalog.setStatus(StatusNaloga.valueOf(rs.getString("nazivStatusa")));
                nalog.setUkupanIznosPosla(rs.getFloat("ukupanIznosPosla"));

                int idDispecera = rs.getInt("idDispecera");
                String imeDispecera = rs.getString("imeDispecera");
                String prezimeDispecera = rs.getString("prezimeDispecera");
                String emailDispecera = rs.getString("emailDispecera");
                String telefonDispecera = rs.getString("telefonDispecera");
                String userName = rs.getString("korisnickoIme");
                String pass = rs.getString("lozinka");

                Dispecer d = new Dispecer(idDispecera, imeDispecera, prezimeDispecera, emailDispecera, telefonDispecera, userName, pass);

                nalog.setDispecer(d);

                int idMesta = rs.getInt("idMesta");
                String nazivMesta = rs.getString("nazivMesta");
                String drzava = rs.getString("drzava");
                int postanskiBroj = rs.getInt("postanskiBroj");

                Mesto m = new Mesto(idMesta, nazivMesta, drzava, postanskiBroj);

                int idPoslovnogPartnera = rs.getInt("idPoslovnogPartnera");
                String nazivPoslovnogPartnera = rs.getString("nazivPartnera");
                int pib = rs.getInt("pib");
                String adresaPoslovnogPartnera = rs.getString("adresaPartnera");
                String emailPoslovnogPartnera = rs.getString("emailPartnera");

                PoslovniPartner pp = new PoslovniPartner(idPoslovnogPartnera, nazivPoslovnogPartnera, pib, adresaPoslovnogPartnera, emailPoslovnogPartnera, m);

                nalog.setPoslovniPartner(pp);

                nalozi.put(idNaloga, nalog);
            }

            StavkaNaloga stavka = new StavkaNaloga();

            int rb = rs.getInt("rb");
            Float kolicina = rs.getFloat("kolicina");
            Float cenaPoJedinici = rs.getFloat("cenaPoJedinici");
            Float iznos = rs.getFloat("iznos");

            int idRobe = rs.getInt("idRobe");
            String nazivRobe = rs.getString("nazivRobe");

            int idJedinice = rs.getInt("idJedinice");
            String nazivJedinice = rs.getString("nazivJedinice");
            String oznaka = rs.getString("oznaka");
            JedinicaMere j = new JedinicaMere(idJedinice, nazivJedinice, oznaka);

            Float cena = rs.getFloat("cena");
            Roba r = new Roba(idRobe, nazivRobe, j, cena);

            stavka.setRb(rb);
            stavka.setKolicina(kolicina);
            stavka.setCenaPoJedinici(cenaPoJedinici);
            stavka.setIznos(iznos);
            stavka.setRoba(r);
            stavka.setNalog(nalog);

            nalog.getStavke().add(stavka);
        }

        lista.addAll(nalozi.values());

        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "nalog_za_transport_robe";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "idNaloga";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idNaloga = " + idNaloga;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (datumKreiranja, datumIzvrsenja, adresaUtovara, adresaIstovara, status, ukupanIznosPosla, dispecer, poslovni_partner) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format(
                "'%s', '%s', '%s', '%s', %d, %.2f, %d, %d",
                new SimpleDateFormat("yyyy-MM-dd").format(datumKreiranja),
                new SimpleDateFormat("yyyy-MM-dd").format(datumIzvrsenja),
                adresaUtovara,
                adresaIstovara,
                status.ordinal(),
                ukupanIznosPosla,
                dispecer.getIdDispecera(),
                poslovniPartner.getIdPoslovnogPartnera()
        );

    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return String.format(
                "datumKreiranja = '%s', datumIzvrsenja = '%s', adresaUtovara = '%s', adresaIstovara = '%s', status = %d, ukupanIznosPosla = %.2f, dispecer = %d, poslovniPartner = %d",
                new SimpleDateFormat("yyyy-MM-dd").format(datumKreiranja),
                new SimpleDateFormat("yyyy-MM-dd").format(datumIzvrsenja),
                adresaUtovara,
                adresaIstovara,
                status.ordinal(),
                ukupanIznosPosla,
                dispecer.getIdDispecera(),
                poslovniPartner.getIdPoslovnogPartnera()
        );
    }

    @Override
    public String alijas() {
        return "nr";
    }

    @Override
    public String join() {
        return "JOIN Dispecer d ON nr.dispecer = d.idDispecera "
                + "JOIN status_naloga st ON nr.status = st.idStatusaNaloga "
                + "JOIN poslovni_partner pp ON nr.poslovni_partner = pp.idPoslovnogPartnera "
                + "JOIN mesto m ON pp.mesto = m.idMesta "
                + "JOIN stavka_naloga sn ON nr.idNaloga = sn.nalog "
                + "JOIN roba r ON sn.roba = r.idRobe "
                + "JOIN jedinica_mere jm ON r.jedinicaMere = jm.idJedinice";
    }

    @Override
    public String uslov() {
        return "nr.dispecer = " + dispecer.getIdDispecera();
    }

    @Override
    public String uslovZaSelect() {
        StringBuilder uslov = new StringBuilder();

        if (dispecer != null && dispecer.getIdDispecera() > 0) {
            uslov.append("nr.dispecer = ").append(dispecer.getIdDispecera());
        }

        if (status != null) {
            if (uslov.length() > 0) {
                uslov.append(" AND ");
            }
            //Da li brisati st
            uslov.append("st.nazivStatusa = '").append(status.name()).append("'");
        }

        if (datumKreiranja != null) {
            if (uslov.length() > 0) {
                uslov.append(" AND ");
            }
            uslov.append("nr.datumKreiranja = '").append(datumKreiranja).append("'");
        }

        if (datumIzvrsenja != null) {
            if (uslov.length() > 0) {
                uslov.append(" AND ");
            }
            uslov.append("nr.datumIzvrsenja = '").append(datumIzvrsenja).append("'");
        }

        if (poslovniPartner != null) {

            if (poslovniPartner.getNazivPartnera() != null) {
                if (uslov.length() > 0) {
                    uslov.append(" AND ");
                }
                uslov.append("pp.nazivPartnera LIKE '%").append(poslovniPartner.getNazivPartnera()).append("%'");
            }
            if (poslovniPartner.getPib() > 0) {
                if (uslov.length() > 0) {
                    uslov.append(" AND ");
                }
                uslov.append("pp.pib = ").append(poslovniPartner.getPib());
            }
            if (poslovniPartner.getMesto() != null) {

                if (poslovniPartner.getMesto().getNazivMesta() != null) {
                    if (uslov.length() > 0) {
                        uslov.append(" AND ");
                    }
                    uslov.append("m.nazivMesta LIKE '%").append(poslovniPartner.getMesto().getNazivMesta()).append("%'");
                }
                if (poslovniPartner.getMesto().getDrzava() != null) {
                    if (uslov.length() > 0) {
                        uslov.append(" AND ");
                    }
                    uslov.append("m.drzava LIKE '%").append(poslovniPartner.getMesto().getDrzava()).append('%');
                }

            }

        }

        return uslov.toString();
    }

}
