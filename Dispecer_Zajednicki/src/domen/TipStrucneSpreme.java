/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domen;

/**
 *
 * @author mikir
 */
public enum TipStrucneSpreme {
    SREDNJA, VISA_STRUCNA_SPREMA, VISOKA_STRUCNA_SPREMA, MASTER, DOKTORAT;

    @Override
    public String toString() {

        String ime = name().toLowerCase().replace('_', ' ');

        String[] reci = ime.split(" ");
        StringBuilder rezultat = new StringBuilder();

        for (String rec : reci) {
            if (rec.length() > 0) {
                rezultat.append(Character.toUpperCase(rec.charAt(0)))
                        .append(rec.substring(1))
                        .append(" ");
            }
        }

        return rezultat.toString().trim();
    }

}
