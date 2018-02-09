package labs.vex.lumen;

import labs.vex.lumen.anemoi.Anemoi;
import labs.vex.lumen.firefly.Firefly;

/**
 * Lumen is a top level framework used for FTC robots!
 * It contains easy to manage and extend code and tons
 * of reflection API usage
 *
 * Me and my team (RO045 Phantom), we love Reflection
 * and service discovery o_O
 *
 * @author vex | Ciobanu Laurentiu
 */
public class Lumen
{
    /**
     * Private firefly instance
     *
     * @author vex | Ciobanu Laurentiu
     */
    private static Firefly firefly = new Firefly();

    /**
     * Private anemoi instance
     *
     * @author vex | Ciobanu Laurentiu
     */
    private static Anemoi anemoi = new Anemoi();

    private static Bootstrap bootstrap;

    /**
     * Public access point used for Firefly API access
     *
     * @return Firefly API accessor
     * @author vex | Ciobanu Laurentiu
     */
    public static Firefly firefly() {
        return firefly;
    }

    /**
     * Pubic access point used for Anemoi API access
     *
     * @return Anemoi API accessor
     * @author vex | Ciobanu Laurentiu
     */
    public static Anemoi anemoi() {
        return anemoi;
    }

    public static Bootstrap getBootstrap() {
        return bootstrap;
    }

    public static void installBootstrap(Bootstrap b) {
        bootstrap = b;
    }

    public static void clean() {
        bootstrap.dispose();
    }
}
