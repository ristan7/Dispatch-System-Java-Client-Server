package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikir
 */
public class Konfiguracija {

    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        try (FileInputStream fis = new FileInputStream(
                "C:\\Users\\mikir\\Documents\\NetBeansProjects\\SeminarskiSoftveri\\Dispecer_Server\\config\\config.properties")) {

            konfiguracija = new Properties();
            konfiguracija.load(fis);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName())
                    .log(Level.WARNING, "Greska prilikom ucitavanja konfiguracije...");
        }
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try (FileOutputStream fos = new FileOutputStream(
                "C:\\Users\\mikir\\Documents\\NetBeansProjects\\SeminarskiSoftveri\\Dispecer_Server\\config\\config.properties")) {

            konfiguracija.store(fos, null);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName())
                    .log(Level.WARNING, "Greska prilikom cuvanja konfiguracije...");
        }
    }
}