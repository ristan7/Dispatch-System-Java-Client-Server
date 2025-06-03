/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author mikir
 */
public class Primalac {

    private Socket socket;
    private ObjectInputStream in;

    public Primalac(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public Object primi() throws Exception {
        return in.readObject();
    }

    public void close() throws IOException {
        in.close();
    }
}
