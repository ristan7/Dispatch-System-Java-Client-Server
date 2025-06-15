/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli.partner;

import domen.PoslovniPartner;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mikir
 */
public class ModelTabelePrikaziPoslovnePartnere extends AbstractTableModel {

    private ArrayList<PoslovniPartner> lista;
    private String[] kolone = {"Naziv partnera", "PIB", "Adresa partnera", "Email partnera", "Mesto"};

    public ModelTabelePrikaziPoslovnePartnere(ArrayList<PoslovniPartner> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PoslovniPartner pp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pp.getNazivPartnera();
            case 1:
                return pp.getPib();
            case 2:
                return pp.getAdresaPartnera();
            case 3:
                return pp.getEmailPartnera();
            case 4:
                return pp.getMesto().getNazivMesta() + " (" + pp.getMesto().getDrzava() + ")";
            default:
                return "N/A";
        }
    }

    public ArrayList<PoslovniPartner> getLista() {
        return lista;
    }

    public void setLista(ArrayList<PoslovniPartner> lista) {
        this.lista = lista;
    }

}
