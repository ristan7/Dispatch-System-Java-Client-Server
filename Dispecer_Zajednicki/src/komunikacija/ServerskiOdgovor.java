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
    private int operacija;
    private String poruka;
    private VrstaOdgovora vrstaOdgovora;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, int operacija, String poruka, VrstaOdgovora vrstaOdgovora) {
        this.odgovor = odgovor;
        this.operacija = operacija;
        this.poruka = poruka;
        this.vrstaOdgovora = vrstaOdgovora;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int Operacija) {
        this.operacija = Operacija;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public VrstaOdgovora getVrstaOdgovora() {
        return vrstaOdgovora;
    }

    public void setVrstaOdgovora(VrstaOdgovora vrstaOdgovora) {
        this.vrstaOdgovora = vrstaOdgovora;
    }

    @Override
    public String toString() {
        return "ServerskiOdgovor{" + "odgovor=" + odgovor + ", operacija=" + operacija + ", poruka=" + poruka + ", vrstaOdgovora=" + vrstaOdgovora + '}';
    }

}
