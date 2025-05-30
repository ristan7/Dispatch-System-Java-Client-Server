/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentskiZahtev;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.ServerskiOdgovor;
import komunikacija.VrstaOdgovora;

/**
 *
 * @author mikir
 */
public class ObradaKlijentskihZahteva extends Thread {

    private final Socket socket;
    private final Posiljalac posiljalac;
    private final Primalac primalac;

    public ObradaKlijentskihZahteva(Socket socket) throws IOException {
        this.socket = socket;
        this.posiljalac = new Posiljalac(socket);
        this.primalac = new Primalac(socket);

    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                KlijentskiZahtev zahtev = null;
                try {
                    zahtev = (KlijentskiZahtev) primalac.primi();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (zahtev == null) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.WARNING, "Primljen je prazan zahtev");
                    continue;
                }
                ServerskiOdgovor odgovor = obradiZahtev(zahtev);
                posiljalac.posalji(odgovor);
            } catch (IOException e) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                        .log(Level.WARNING, "Greška prilikom primanja ili obrade zahteva: " + e.getMessage(), e);
            }
        }
        zatvoriSoket();

    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev zahtev) {
        try {
            if (zahtev == null) {
                return new ServerskiOdgovor(null, -1, "Primljen je prazan zahtev.", VrstaOdgovora.NEUSPESNO);
            }

            switch (zahtev.getOperacija()) {
                default:
                    return new ServerskiOdgovor(null, zahtev.getOperacija(), "Nije poznata operacija!", VrstaOdgovora.NEUSPESNO);
            }
        } catch (Exception ex) {
            return new ServerskiOdgovor(null, zahtev.getOperacija(), ex.getMessage(), VrstaOdgovora.NEUSPESNO);
        }
    }

    public void zatvoriSoket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.INFO, "Soket je uspešno zatvoren.");
            }
        } catch (IOException e) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                    .log(Level.SEVERE, "Greška pri zatvaranju soketa.", e);
        }
    }
}
