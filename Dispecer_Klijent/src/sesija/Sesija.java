/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesija;

import domen.Dispecer;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author mikir
 */
public class Sesija {

    private static Sesija instance;
    private Socket socket;
    private Dispecer ulogovani;

    private Sesija() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Dispecer getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Dispecer ulogovani) {
        this.ulogovani = ulogovani;
    }

}
