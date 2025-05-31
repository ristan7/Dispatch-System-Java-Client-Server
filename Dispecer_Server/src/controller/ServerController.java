/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Dispecer;
import java.util.ArrayList;

/**
 *
 * @author mikir
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Dispecer> ulogovaniDispeceri = new ArrayList<>();

    private ServerController() {

    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Dispecer> getUlogovaniDispeceri() {
        return ulogovaniDispeceri;
    }

    public void setUlogovaniDispeceri(ArrayList<Dispecer> ulogovaniDispeceri) {
        this.ulogovaniDispeceri = ulogovaniDispeceri;
    }
    
    

}
