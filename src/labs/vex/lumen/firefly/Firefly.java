package labs.vex.lumen.firefly;

import java.util.Map;

/**
 * Firefly is a core package of Lumen Framework.
 *
 * Firefly is designed to handle any type of configuration
 * that might be used by a robot!
 *
 * @author vex | Ciobanu Laurentiu
 */
public class Firefly {

    /**
     * Internal Daemon Instance
     *
     * @author vex | Ciobanu Laurentiu
     */
    private Daemon daemon;

    /**
     * Public constructor of the class
     * Used only for initialization
     *
     * @author vex | Ciobanu Laurentiu
     */
    public Firefly() {
        this.daemon = new Daemon();
    }

    /**
     * Used to load all the configuration by name into
     * a configuration manager
     *
     * @param batch (name, content) pair map
     * @param loaderType the loader of the batch
     * @return loaded ConfigurationManager
     * @throws IllegalAccessException when reflection API fails
     * @throws InstantiationException when reflection API fails
     * @author vex | Ciobanu Laurentiu
     */
    public ConfigurationManager load(Map<String, String> batch, Class<ILoader> loaderType) throws IllegalAccessException, InstantiationException {
        ILoader loader = loaderType.newInstance();
        return loader.load(batch);
    }

    /**
     * Public accessor for the internal Daemon Instance
     *
     * @return the daemon instance
     * @author vex | Ciobanu Laurentiu
     */
    public Daemon daemon() {
        return this.daemon;
    }
}
