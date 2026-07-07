package util;

import java.time.Clock;
import java.util.Date;

/**
 * Centralizovani izvor "trenutnog vremena" za celu aplikaciju.
 *
 * Produkcioni kod NIKAD ne poziva new Date() ili Calendar.getInstance()
 * direktno kad mu treba "danas" - poziva VremenskiIzvor.sada().
 *
 * U testovima se sat moze fiksirati pozivom postaviClock(...), cime
 * testovi postaju deterministicki i vise ne zavise od sistemskog sata
 * u trenutku pokretanja (SonarQube rule java:S8692).
 *
 * @author mikir
 */
public final class VremenskiIzvor {

    private static Clock clock = Clock.systemDefaultZone();

    private VremenskiIzvor() {
    }

    public static Date sada() {
        return Date.from(clock.instant());
    }

    public static void postaviClock(Clock noviClock) {
        clock = noviClock;
    }

    public static void resetuj() {
        clock = Clock.systemDefaultZone();
    }
}