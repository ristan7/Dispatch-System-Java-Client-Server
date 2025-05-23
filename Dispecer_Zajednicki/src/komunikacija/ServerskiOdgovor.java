/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author mikir
 */
public class ServerskiOdgovor implements Serializable {

    private Object odgovor;
    private int Operacija;
    private VrstaOdgovora vrstaOdgovora;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, int Operacija, VrstaOdgovora vrstaOdgovora) {
        this.odgovor = odgovor;
        this.Operacija = Operacija;
        this.vrstaOdgovora = vrstaOdgovora;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public int getOperacija() {
        return Operacija;
    }

    public void setOperacija(int Operacija) {
        this.Operacija = Operacija;
    }

    public VrstaOdgovora getVrstaOdgovora() {
        return vrstaOdgovora;
    }

    public void setVrstaOdgovora(VrstaOdgovora vrstaOdgovora) {
        this.vrstaOdgovora = vrstaOdgovora;
    }

    @Override
    public String toString() {
        return "ServerskiOdgovor{" + "odgovor=" + odgovor + ", Operacija=" + Operacija + ", vrstaOdgovora=" + vrstaOdgovora + '}';
    }

}
