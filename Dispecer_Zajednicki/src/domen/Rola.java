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
                karakteri[i] = Character.toLowerCase(karakteri[i]);
            }
        }
        return new String(karakteri);
    }

}