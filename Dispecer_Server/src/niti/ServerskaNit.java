/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.ServerController;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                    .log(Level.WARNING, "Greška prilikom kreiranja serverske niti na portu 9000: " + ex.getMessage(), ex);
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
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                        .log(Level.WARNING, "Greška prilikom primanja klijenata: " + ex.getMessage(), ex);;
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
            listaNitiKlijenta.clear();
            ServerController.getInstance().resetujListe();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                    .log(Level.WARNING, "Greška prilikom zaustavljanja servera: " + ex.getMessage(), ex);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
