/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikir
 */
public class Posiljalac {

    private Socket socket;
    private ObjectOutputStream out;

    public Posiljalac(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void posalji(Object obj) throws IOException {
        out.writeObject(obj);
        out.flush();
    }

    public void close() throws IOException {
        out.close();
    }
}
