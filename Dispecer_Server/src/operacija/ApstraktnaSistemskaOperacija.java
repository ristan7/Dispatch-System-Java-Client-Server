/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import baza.DBBroker;
import domen.ApstraktniDomenskiObjekat;

/**
 *
 * @author mikir
 */
public abstract class ApstraktnaSistemskaOperacija {

    protected abstract void validiraj(ApstraktniDomenskiObjekat ado) throws Exception;

    protected abstract void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception;

    public void templateIzvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            validiraj(ado);
            izvrsi(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws Exception {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws Exception {
        DBBroker.getInstance().getConnection().rollback();
    }
}
