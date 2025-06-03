/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import controller.ClientController;
import domen.NalogZaTransportRobe;
import domen.StatusNaloga;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mikir
 */
public class ModelTabelePrikaziNaloge extends AbstractTableModel {

    private ArrayList<NalogZaTransportRobe> lista;
    private String[] kolone = {"Datum kreiranja", "Datum izvršenja", "Adresa utovara", "Adresa istovara", "Status", "Iznos", "Poslovni partner"};
    private String parametar = "";

    public ModelTabelePrikaziNaloge(ArrayList<NalogZaTransportRobe> lista) {
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
        NalogZaTransportRobe nalog = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

        switch (columnIndex) {
            case 0:
                return sdf.format(nalog.getDatumKreiranja());
            case 1:
                return sdf.format(nalog.getDatumIzvrsenja());
            case 2:
                return nalog.getAdresaUtovara();
            case 3:
                return nalog.getAdresaIstovara();
            case 4:
                return nalog.getStatus().toString();
            case 5:
                return nalog.getUkupanIznosPosla() + " RSD";
            case 6:
                return nalog.getPoslovniPartner().toString();
            default:
                return "N/A";
        }
    }

    public NalogZaTransportRobe getSelectedNalog(int row) {
        return lista.get(row);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        //refreshTable();
    }

    //Mettoda refresh
}
