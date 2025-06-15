/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domen;

/**
 *
 * @author mikir
 */
public enum StatusNaloga {
    U_PRIPREMI, U_TRANSPORTU, ISPORUCENO, OTKAZANO;

    @Override
    public String toString() {
        String ime = name().toLowerCase().replace('_', ' ');

        String[] reci = ime.split(" ");
        StringBuilder rezultat = new StringBuilder();

        for (int i = 0; i < reci.length; i++) {
            String rec = reci[i];
            if (rec.length() > 0) {
                if (i == 0) {

                    rezultat.append(Character.toUpperCase(rec.charAt(0)))
                            .append(rec.substring(1));
                } else {

                    rezultat.append(" ").append(rec);
                }
            }
        }

        return rezultat.toString();
    }

}
