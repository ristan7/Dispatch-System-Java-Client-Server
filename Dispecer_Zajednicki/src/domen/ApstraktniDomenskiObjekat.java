/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mikir
 */
public interface ApstraktniDomenskiObjekat extends Serializable {

    public abstract ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException;

    public abstract String vratiNazivTabele();

    public abstract String vratiNazivPrimarnogKljuca();

    public abstract String vratiPrimarniKljuc();

    public abstract String vratiKoloneZaInsert();

    public abstract String vratiVrednostiZaInsert();

    public abstract String vratiVrednostiZaUpdate();

    public abstract String alijas();

    public abstract String join();

    public abstract String uslov();

    public abstract String uslovZaSelect();

}
