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
import java.util.Optional;

/**
 *
 * @author mikir
 */
public class NalogZaTransportRobe implements ApstraktniDomenskiObjekat {

    private static final String FORMAT_DATUMA = "yyyy-MM-dd";

    private int idNaloga;
    private Date datumUtovara;
    private Date datumIstovara;
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

    public Date getDatumUtovara() {
        return datumUtovara;
    }

    public void setDatumUtovara(Date datumUtovara) {
        this.datumUtovara = datumUtovara;
    }

    public Date getDatumIstovara() {
        return datumIstovara;
    }

    public void setDatumIstovara(Date datumIstovara) {
        this.datumIstovara = datumIstovara;
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

    public ArrayList<StavkaNaloga> getStavke() {
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
        if (!Objects.equals(this.adresaUtovara, other.adresaUtovara)) {
            return false;
        }
        if (!Objects.equals(this.adresaIstovara, other.adresaIstovara)) {
            return false;
        }
        if (!Objects.equals(this.datumUtovara, other.datumUtovara)) {
            return false;
        }
        if (!Objects.equals(this.datumIstovara, other.datumIstovara)) {
            return false;
        }
        if (!Objects.equals(this.dispecer, other.dispecer)) {
            return false;
        }
        if (!Objects.equals(this.poslovniPartner, other.poslovniPartner)) {
            return false;
        }
        return Objects.equals(this.stavke, other.stavke);
    }

    @Override
    public String toString() {
        return "Nalog izmedju " + dispecer + " i " + poslovniPartner + ", utovar zakazan za  " + datumUtovara + ", planiran da se izvrsi dana " + datumIstovara;
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

        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        Map<Integer, NalogZaTransportRobe> nalozi = new HashMap<>();

        while (rs.next()) {
            int idNaloga = rs.getInt("idNaloga");

            NalogZaTransportRobe nalog = nalozi.get(idNaloga);

            if (nalog == null) {

                nalog = new NalogZaTransportRobe();
                nalog.setIdNaloga(idNaloga);

                java.sql.Date datumU = rs.getDate("datumUtovara");
                java.util.Date datumUtovara = new java.util.Date(datumU.getTime());
                nalog.setDatumUtovara(datumUtovara);

                java.sql.Date datumI = rs.getDate("datumIstovara");
                java.util.Date datumIstovara = new java.util.Date(datumI.getTime());
                nalog.setDatumIstovara(datumIstovara);

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
                Rola rola = Rola.valueOf(rs.getString("nazivRole"));

                Dispecer d = new Dispecer(idDispecera, imeDispecera, prezimeDispecera, emailDispecera, telefonDispecera, userName, pass, rola);

                nalog.setDispecer(d);

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

                nalog.setPoslovniPartner(pp);

                nalozi.put(idNaloga, nalog);
            }

            int rb = rs.getInt("rb");
            if (!rs.wasNull()) {
                StavkaNaloga stavka = new StavkaNaloga();

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

                nalog.getStavke().add(stavka);
            }

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
        return " (datumUtovara, datumIstovara, adresaUtovara, adresaIstovara, status, ukupanIznosPosla, dispecer, poslovni_partner) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    public ArrayList<Object> parametriZaInsert() {
        ArrayList<Object> parametri = new ArrayList<>();
        parametri.add(new SimpleDateFormat(FORMAT_DATUMA).format(datumUtovara));
        parametri.add(new SimpleDateFormat(FORMAT_DATUMA).format(datumIstovara));
        parametri.add(adresaUtovara);
        parametri.add(adresaIstovara);
        parametri.add(status.ordinal() + 1);
        parametri.add(ukupanIznosPosla);
        parametri.add(dispecer.getIdDispecera());
        parametri.add(poslovniPartner.getIdPoslovnogPartnera());
        return parametri;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "datumUtovara = ?, datumIstovara = ?, adresaUtovara = ?, adresaIstovara = ?, status = ?, ukupanIznosPosla = ?, dispecer = ?, poslovni_partner = ?";
    }

    @Override
    public ArrayList<Object> parametriZaUpdate() {
        return parametriZaInsert();
    }

    @Override
    public String alijas() {
        return "nr";
    }

    @Override
    public String join() {

        return "JOIN Dispecer d ON nr.dispecer = d.idDispecera "
                + "JOIN rola ro ON d.rola = ro.idRole "
                + "JOIN status_naloga st ON nr.status = st.idStatusaNaloga "
                + "JOIN poslovni_partner pp ON nr.poslovni_partner = pp.idPoslovnogPartnera "
                + "JOIN mesto m ON pp.mesto = m.idMesta "
                + "LEFT JOIN stavka_naloga sn ON nr.idNaloga = sn.nalog "
                + "LEFT JOIN roba r ON sn.roba = r.idRobe "
                + "LEFT JOIN jedinica_mere jm ON r.jedinicaMere = jm.idJedinice";
    }

    @Override
    public String uslov() {
        return "idNaloga = ?";
    }

    @Override
    public ArrayList<Object> parametriZaUslov() {
        ArrayList<Object> parametri = new ArrayList<>();
        parametri.add(idNaloga);
        return parametri;
    }

    @Override
    public String uslovZaSelect() {
        String posebanSlucaj = uslovZaPosebanSlucaj();
        if (posebanSlucaj != null) {
            return posebanSlucaj;
        }

        StringBuilder uslov = new StringBuilder();
        dodajUslovDispecera(uslov);
        dodajUslovStatusa(uslov);
        dodajUslovDatuma(uslov);
        dodajUslovPartnera(uslov);
        dodajUslovStavki(uslov);

        if (uslov.length() > 0) {
            return " WHERE " + uslov;
        }
        return "";
    }

    private String uslovZaPosebanSlucaj() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATUMA);
        sdf.setLenient(false);
        String danasnjiDatum = sdf.format(new Date());

        if (datumUtovara != null && datumIstovara != null) {
            String datumUtovaraFormatiran = sdf.format(datumUtovara);
            String datumIstovaraFormatiran = sdf.format(datumIstovara);

            if (datumUtovaraFormatiran.equals(danasnjiDatum) && datumIstovaraFormatiran.equals(danasnjiDatum)) {
                return " WHERE nr.dispecer = ? AND (nr.datumUtovara = ? OR nr.datumIstovara = ?)";
            }
        }

        if (dispecer != null) {
            if (dispecer.getRola() == Rola.KORISNIK && idNaloga == -1) {
                return " WHERE nr.dispecer = ?";
            }
            if (dispecer.getRola() == Rola.ADMINISTRATOR && idNaloga == -1) {
                return "";
            }
        }

        return null;
    }

    private void dodajUslovDispecera(StringBuilder uslov) {
        if (dispecer != null && dispecer.getIdDispecera() > 0) {
            uslov.append("nr.dispecer = ?");
        }
    }

    private void dodajUslovStatusa(StringBuilder uslov) {
        if (status != null) {
            dodajAndAkoTreba(uslov);
            uslov.append("st.nazivStatusa = ?");
        }
    }

    private void dodajUslovDatuma(StringBuilder uslov) {
        if (datumUtovara != null) {
            dodajAndAkoTreba(uslov);
            uslov.append("nr.datumUtovara = ?");
        }
        if (datumIstovara != null) {
            dodajAndAkoTreba(uslov);
            uslov.append("nr.datumIstovara = ?");
        }
    }

    private void dodajUslovPartnera(StringBuilder uslov) {
        if (poslovniPartner == null) {
            return;
        }

        if (poslovniPartner.getNazivPartnera() != null && !poslovniPartner.getNazivPartnera().isBlank()) {
            dodajAndAkoTreba(uslov);
            uslov.append("pp.nazivPartnera LIKE ?");
        }
        if (poslovniPartner.getPib() != null && !poslovniPartner.getPib().isEmpty()) {
            dodajAndAkoTreba(uslov);
            uslov.append("pp.pib = ?");
        }

        Mesto mesto = poslovniPartner.getMesto();
        if (mesto == null) {
            return;
        }

        if (mesto.getNazivMesta() != null && !mesto.getNazivMesta().isBlank()) {
            dodajAndAkoTreba(uslov);
            uslov.append("m.nazivMesta LIKE ?");
        }
        if (mesto.getDrzava() != null && !mesto.getDrzava().isBlank()) {
            dodajAndAkoTreba(uslov);
            uslov.append("m.drzava LIKE ?");
        }
    }

    private void dodajUslovStavki(StringBuilder uslov) {
        if (!stavke.isEmpty()) {
            dodajAndAkoTreba(uslov);
            for (StavkaNaloga stavkaNaloga : stavke) {
                uslov.append("nr.idNaloga IN (\n"
                        + "    SELECT sn2.nalog\n"
                        + "    FROM stavka_naloga sn2\n"
                        + "    WHERE sn2.roba = ?)");
            }
        }
    }

    private void dodajAndAkoTreba(StringBuilder uslov) {
        if (uslov.length() > 0) {
            uslov.append(" AND ");
        }
    }

    @Override
    public ArrayList<Object> parametriZaSelect() {
        Optional<List<Object>> posebanSlucaj = parametriZaPosebanSlucaj();
        if (posebanSlucaj.isPresent()) {
            return new ArrayList<>(posebanSlucaj.get());
        }

        ArrayList<Object> parametri = new ArrayList<>();
        dodajParametarDispecera(parametri);
        dodajParametarStatusa(parametri);
        dodajParametreDatuma(parametri);
        dodajParametrePartnera(parametri);
        dodajParametreStavki(parametri);
        return parametri;
    }

    private Optional<List<Object>> parametriZaPosebanSlucaj() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATUMA);
        sdf.setLenient(false);
        String danasnjiDatum = sdf.format(new Date());

        if (datumUtovara != null && datumIstovara != null) {
            String datumUtovaraFormatiran = sdf.format(datumUtovara);
            String datumIstovaraFormatiran = sdf.format(datumIstovara);

            if (datumUtovaraFormatiran.equals(danasnjiDatum) && datumIstovaraFormatiran.equals(danasnjiDatum)) {
                List<Object> parametri = new ArrayList<>();
                parametri.add(dispecer.getIdDispecera());
                parametri.add(danasnjiDatum);
                parametri.add(danasnjiDatum);
                return Optional.of(parametri);
            }
        }

        if (dispecer != null) {
            if (dispecer.getRola() == Rola.KORISNIK && idNaloga == -1) {
                List<Object> parametri = new ArrayList<>();
                parametri.add(dispecer.getIdDispecera());
                return Optional.of(parametri);
            }
            if (dispecer.getRola() == Rola.ADMINISTRATOR && idNaloga == -1) {
                return Optional.of(new ArrayList<>());
            }
        }

        return Optional.empty();
    }

    private void dodajParametarDispecera(List<Object> parametri) {
        if (dispecer != null && dispecer.getIdDispecera() > 0) {
            parametri.add(dispecer.getIdDispecera());
        }
    }

    private void dodajParametarStatusa(List<Object> parametri) {
        if (status != null) {
            parametri.add(status.name());
        }
    }

    private void dodajParametreDatuma(List<Object> parametri) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATUMA);
        sdf.setLenient(false);

        if (datumUtovara != null) {
            parametri.add(sdf.format(datumUtovara));
        }
        if (datumIstovara != null) {
            parametri.add(sdf.format(datumIstovara));
        }
    }

    private void dodajParametrePartnera(List<Object> parametri) {
        if (poslovniPartner == null) {
            return;
        }

        if (poslovniPartner.getNazivPartnera() != null && !poslovniPartner.getNazivPartnera().isBlank()) {
            parametri.add("%" + poslovniPartner.getNazivPartnera() + "%");
        }
        if (poslovniPartner.getPib() != null && !poslovniPartner.getPib().isEmpty()) {
            parametri.add(poslovniPartner.getPib());
        }

        Mesto mesto = poslovniPartner.getMesto();
        if (mesto == null) {
            return;
        }

        if (mesto.getNazivMesta() != null && !mesto.getNazivMesta().isBlank()) {
            parametri.add("%" + mesto.getNazivMesta() + "%");
        }
        if (mesto.getDrzava() != null && !mesto.getDrzava().isBlank()) {
            parametri.add("%" + mesto.getDrzava() + "%");
        }
    }

    private void dodajParametreStavki(List<Object> parametri) {
        for (StavkaNaloga stavkaNaloga : stavke) {
            parametri.add(stavkaNaloga.getRoba().getIdRobe());
        }
    }

}
