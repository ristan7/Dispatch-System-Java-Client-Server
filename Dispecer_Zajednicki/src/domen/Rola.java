/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domen;

/**
 *
 * @author mikir
 */
public enum Rola {
    ADMINISTRATOR, KORISNIK;

    @Override
    public String toString() {
        String ime = name();
        char[] karakteri = ime.toCharArray();
        for (int i = 0; i < karakteri.length; i++) {

            if (i != 0) {
                Character.toLowerCase(karakteri[i]);
            }
        }
        return karakteri.toString();
    }

}
