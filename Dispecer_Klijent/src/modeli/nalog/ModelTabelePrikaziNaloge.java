/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli.nalog;

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
    private String[] kolone = {"Datum utovara", "Datum istovara", "Adresa utovara", "Adresa istovara", "Status", "Iznos", "Poslovni partner"};

    public ModelTabelePrikaziNaloge(ArrayList<NalogZaTransportRobe> lista) {
        this.lista = lista;
    }

    public ModelTabelePrikaziNaloge() {
        lista = new ArrayList<>();
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
                return sdf.format(nalog.getDatumUtovara());
            case 1:
                return sdf.format(nalog.getDatumIstovara());
            case 2:
                return nalog.getAdresaUtovara();
            case 3:
                return nalog.getAdresaIstovara();
            case 4:
                return srediStatus(nalog);
            case 5:
                return nalog.getUkupanIznosPosla() + " RSD";
            case 6:
                return nalog.getPoslovniPartner().toString();
            default:
                return "N/A";
        }
    }

    public ArrayList<NalogZaTransportRobe> getLista() {
        return lista;
    }

    public void setLista(ArrayList<NalogZaTransportRobe> lista) {
        this.lista = lista;
    }

    public String srediStatus(NalogZaTransportRobe nalog) {
        switch (nalog.getStatus()) {
            case U_PRIPREMI:
                return "U pripremi";
            case U_TRANSPORTU:
                return "U transportu";
            case ISPORUCENO:
                return "Isporuceno";
            case OTKAZANO:
                return "Otkazano";
            default:
                return "N/A";
        }
    }
}
