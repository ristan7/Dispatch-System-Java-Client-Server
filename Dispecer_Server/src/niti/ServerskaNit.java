/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mikir
 */
public class ServerskaNit extends Thread {

    ServerSocket serverSocket;
    List<ObradaKlijentskihZahteva> listaNitiKlijenta;

    public ServerskaNit() {
        try {
            serverSocket = new ServerSocket(9000);
            listaNitiKlijenta = new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Pokrenut je SERVER!!!");
        while (serverSocket != null && !serverSocket.isClosed()) {
            try {

                System.out.println("Cekanje KLIJENTA...");
                Socket socket = serverSocket.accept();
                ObradaKlijentskihZahteva nitKlijenta = new ObradaKlijentskihZahteva(socket);
                listaNitiKlijenta.add(nitKlijenta);
                System.out.println("KLIJENT se uspesno povezao. Ovo je KLIJENT broj : " + (listaNitiKlijenta.size()));
                nitKlijenta.start();

            } catch (SocketException e) {
                System.out.println("Serverski soket je zatvoren!!");
                break;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void zaustaviServer() {
        try {
            for (ObradaKlijentskihZahteva nitKlijenta : listaNitiKlijenta) {
                nitKlijenta.zatvoriSoket();
            }

            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
