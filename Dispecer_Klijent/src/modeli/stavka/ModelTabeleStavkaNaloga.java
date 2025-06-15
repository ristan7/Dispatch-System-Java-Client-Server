/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli.stavka;

import domen.StavkaNaloga;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mikir
 */
public class ModelTabeleStavkaNaloga extends AbstractTableModel {

    private ArrayList<StavkaNaloga> lista;
    private String[] kolone = {"Naziv robe", "Kolicina", "Cena", "Ukupno"};

    public ModelTabeleStavkaNaloga(ArrayList<StavkaNaloga> lista) {
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
        StavkaNaloga stavka = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stavka.getRoba().getNazivRonbe();
            case 1:
                return stavka.getKolicina();
            case 2:
                return stavka.getRoba().getCena();
            case 3:
                return stavka.getIznos();
            default:
                return "N/A";
        }
    }

    public ArrayList<StavkaNaloga> getLista() {
        return lista;
    }

    public void setLista(ArrayList<StavkaNaloga> lista) {
        this.lista = lista;
    }

}
