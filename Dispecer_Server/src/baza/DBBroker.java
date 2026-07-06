/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import domen.ApstraktniDomenskiObjekat;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author mikir
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;

    private DBBroker() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\mikir\\Documents\\NetBeansProjects\\SeminarskiSoftveri\\Dispecer_Server\\config\\config.properties"));
            String url = properties.getProperty("url");
            String pass = properties.getProperty("password");
            String userName = properties.getProperty("username");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, userName, pass);
                connection.setAutoCommit(false);
                System.out.println("Otvorena konekcija!");
            }
        } catch (Exception ex) {
            System.out.println("Greska u connection manager DBBrokera: " + ex.getMessage());
        }

    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<ApstraktniDomenskiObjekat> select(ApstraktniDomenskiObjekat ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.vratiNazivTabele() + " " + ado.alijas() + " " + ado.join() + ado.uslovZaSelect();
        System.out.println(upit);

        PreparedStatement ps = connection.prepareStatement(upit);
        ArrayList<Object> parametri = ado.parametriZaSelect();
        for (int i = 0; i < parametri.size(); i++) {
            ps.setObject(i + 1, parametri.get(i));
        }

        ResultSet rs = ps.executeQuery();
        return ado.vratiListu(rs);
    }

    public int insert(ApstraktniDomenskiObjekat ado) throws SQLException {
        String naredba = "INSERT INTO " + ado.vratiNazivTabele() + " " + ado.vratiKoloneZaInsert() + " VALUES(" + ado.vratiVrednostiZaInsert() + ")";
        System.out.println(naredba);
        PreparedStatement ps = connection.prepareStatement(naredba, Statement.RETURN_GENERATED_KEYS);

        ArrayList<Object> parametri = ado.parametriZaInsert();
        for (int i = 0; i < parametri.size(); i++) {
            ps.setObject(i + 1, parametri.get(i));
        }

        int brojDodatihRedova = ps.executeUpdate();
        System.out.println("Broj dodatih redova: " + brojDodatihRedova);

        if (brojDodatihRedova == 0) {
            throw new SQLException("INSERT nije uspešan, nijedan red nije dodat.");
        }

        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
            System.out.println("Generisan ID: " + id);
        } else {
            System.out.println("Nema generisanog ID-a!");
        }

        return id;
    }

    public void update(ApstraktniDomenskiObjekat ado) throws SQLException {
        String naredba = "UPDATE " + ado.vratiNazivTabele() + " SET " + ado.vratiVrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println(naredba);
        PreparedStatement ps = connection.prepareStatement(naredba);

        ArrayList<Object> parametri = new ArrayList<>();
        parametri.addAll(ado.parametriZaUpdate());
        parametri.addAll(ado.parametriZaUslov());

        for (int i = 0; i < parametri.size(); i++) {
            ps.setObject(i + 1, parametri.get(i));
        }

        ps.executeUpdate();
    }

    public void delete(ApstraktniDomenskiObjekat ado) throws SQLException {
        String naredba = "DELETE FROM " + ado.vratiNazivTabele() + " WHERE " + ado.uslov();
        System.out.println(naredba);
        PreparedStatement ps = connection.prepareStatement(naredba);

        ArrayList<Object> parametri = ado.parametriZaUslov();
        for (int i = 0; i < parametri.size(); i++) {
            ps.setObject(i + 1, parametri.get(i));
        }

        ps.executeUpdate();
    }
}
