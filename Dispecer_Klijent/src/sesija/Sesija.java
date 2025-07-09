/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesija;

import domen.Dispecer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikir
 */
public class Sesija {

    private static Sesija instance;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Dispecer ulogovani;

    private Sesija() {
        try {
            socket = new Socket("localhost", 9000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Sesija.class.getName()).log(Level.INFO, "Server nije pokrenut...");
            
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

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public Dispecer getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Dispecer ulogovani) {
        this.ulogovani = ulogovani;
    }
}
