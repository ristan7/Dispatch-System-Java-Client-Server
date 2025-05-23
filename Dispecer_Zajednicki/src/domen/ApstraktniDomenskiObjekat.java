/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mikir
 */
public interface ApstraktniDomenskiObjekat extends Serializable {

    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException;

    public String vratiNazivTabele();

    public String vratiNazivPrimarnogKljuca();

    public String vratiPrimarniKljuc();

    public String vratiKoloneZaInsert();

    public String vratiVrednostiZaInsert();

    public String vratiVrednostiZaUpdate();

    public String alijas();

    public String join();

    public String uslov();

    public String uslovZaSelect();

}
