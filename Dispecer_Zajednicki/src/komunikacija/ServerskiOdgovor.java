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
    private Exception ex;
    private VrstaOdgovora vrstaOdgovora;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, Exception ex, VrstaOdgovora vrstaOdgovora) {
        this.odgovor = odgovor;
        this.ex = ex;
        this.vrstaOdgovora = vrstaOdgovora;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }

    public VrstaOdgovora getVrstaOdgovora() {
        return vrstaOdgovora;
    }

    public void setVrstaOdgovora(VrstaOdgovora vrstaOdgovora) {
        this.vrstaOdgovora = vrstaOdgovora;
    }

    @Override
    public String toString() {
        return "ServerskiOdgovor{" + "odgovor=" + odgovor + ", ex=" + ex + ", vrstaOdgovora=" + vrstaOdgovora + '}';
    }

}
