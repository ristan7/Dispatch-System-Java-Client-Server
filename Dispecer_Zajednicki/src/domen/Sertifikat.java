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
import java.util.Objects;

/**
 *
 * @author mikir
 */
public class Sertifikat implements ApstraktniDomenskiObjekat {

    private int idSertifikata;
    private Dispecer dispecer;
    private StrucnaSprema strucnaSprema;
    private String nazivSertifikata;
    private Date datumIzdavanja;

    public Sertifikat() {
    }

    public Sertifikat(int idSertifikata, Dispecer dispecer, StrucnaSprema strucnaSprema, String nazivSertifikata, Date datumIzdavanja) {
        this.idSertifikata = idSertifikata;
        this.dispecer = dispecer;
        this.strucnaSprema = strucnaSprema;
        this.nazivSertifikata = nazivSertifikata;
        this.datumIzdavanja = datumIzdavanja;
    }

    public int getIdSertifikata() {
        return idSertifikata;
    }

    public void setIdSertifikata(int idSertifikata) {
        this.idSertifikata = idSertifikata;
    }

    public Dispecer getDispecer() {
        return dispecer;
    }

    public void setDispecer(Dispecer dispecer) {
        this.dispecer = dispecer;
    }

    public StrucnaSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getNazivSertifikata() {
        return nazivSertifikata;
    }

    public void setNazivSertifikata(String nazivSertifikata) {
        this.nazivSertifikata = nazivSertifikata;
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
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.nazivSertifikata, other.nazivSertifikata)) {
            return false;
        }
        return Objects.equals(this.strucnaSprema, other.strucnaSprema);
    }

    @Override
    public String toString() {
        return dispecer + " dana " + datumIzdavanja + " je stekao sertifikat: " + nazivSertifikata;
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idSertifikata = rs.getInt("idSertifikata");
            String nazivSertifikata = rs.getString("nazivSertifikata");
            java.util.Date datumIzdavanja = new java.util.Date(rs.getDate("datumIzdavanja").getTime());

            int idDispecera = rs.getInt("idDispecera");
            String ime = rs.getString("imeDispecera");
            String prezime = rs.getString("prezimeDispecera");
            String email = rs.getString("emailDispecera");
            String telefon = rs.getString("telefonDispecera");
            String username = rs.getString("korisnickoIme");
            String pass = rs.getString("lozinka");
            Rola rola = Rola.valueOf(rs.getString("nazivRole"));

            Dispecer dispecer = new Dispecer(idDispecera, ime, prezime, email, telefon, username, pass, rola);

            int idSpreme = rs.getInt("idStrucneSpreme");
            String naziv = rs.getString("nazivStrucneSpreme");
            TipStrucneSpreme tip = TipStrucneSpreme.valueOf(rs.getString("nazivTipaSpreme"));

            StrucnaSprema sprema = new StrucnaSprema(idSpreme, naziv, tip);

            Sertifikat sertifikat = new Sertifikat(idSertifikata, dispecer, sprema, nazivSertifikata, datumIzdavanja);
            lista.add(sertifikat);

        }

        rs.close();
        return lista;
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "idSertifikata , dispecer, strucnaSprema";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idSertifikata = " + idSertifikata + " AND dispecer = " + dispecer + " AND strucnaSprema = " + strucnaSprema;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return " (dispecer, strucnaSprema, nazivSertifikata, datumIzdavanja) ";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format(
                "%d, %d, '%s', '%s'",
                dispecer.getIdDispecera(),
                strucnaSprema.getIdStrucneSpreme(),
                nazivSertifikata,
                new SimpleDateFormat("yyyy-MM-dd").format(datumIzdavanja)
        );
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return String.format(
                "dispecer = %d, strucnaSprema = %d, nazivSertifikata = '%s', datumIzdavanja = '%s'",
                dispecer.getIdDispecera(),
                strucnaSprema.getIdStrucneSpreme(),
                nazivSertifikata,
                new SimpleDateFormat("yyyy-MM-dd").format(datumIzdavanja)
        );
    }

    @Override
    public String alijas() {
        return "s";
    }

    @Override
    public String join() {
        return "JOIN dispecer d ON s.dispecer = d.idDispecera "
                + "JOIN rola ro ON d.rola = ro.idRole "
                + "JOIN strucna_sprema ss ON s.strucnaSprema = ss.idStrucneSpreme";
    }

    @Override
    public String uslov() {
        return "idSertifikata = " + idSertifikata;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

}
