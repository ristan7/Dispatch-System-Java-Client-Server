/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author mikir
 */
public class JedinicaMere implements Serializable {

    private int idJedinice;
    private String nazivJedinice;
    private String oznaka;

    public JedinicaMere() {
    }

    public JedinicaMere(int idJedinice, String nazivJedinice, String oznaka) {
        this.idJedinice = idJedinice;
        this.nazivJedinice = nazivJedinice;
        this.oznaka = oznaka;
    }

    public int getIdJedinice() {
        return idJedinice;
    }

    public void setIdJedinice(int idJedinice) {
        this.idJedinice = idJedinice;
    }

    public String getNazivJedinice() {
        return nazivJedinice;
    }

    public void setNazivJedinice(String nazivJedinice) {
        this.nazivJedinice = nazivJedinice;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    @Override
    public String toString() {
        return nazivJedinice + " (" + oznaka + ")";
    }

}
