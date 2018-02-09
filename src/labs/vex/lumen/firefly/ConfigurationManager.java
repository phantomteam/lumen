package labs.vex.lumen.firefly;

import java.util.HashMap;
import java.util.Map;

/**
 * The biggest Firefly building block!
 *
 * @author vex | Ciobanu Laurentiu
 */
public class ConfigurationManager {
    /**
     * Internal cache
     *
     * @author vex | Ciobanu Laurentiu
     */
    private Map<String, IConfiguration> cache = new HashMap<>();

    /**
     * The load method is the constructor itself
     *
     * @author vex | Ciobanu Laurentiu
     */
    public ConfigurationManager(Map include) {
        this.cache.putAll(include);
    }

    /**
     * Used to obtain a Configuration from the bundle!
     *
     * @param name
     * @return
     * @author vex | Ciobanu Laurentiu
     */
    public IConfiguration get(String name) {
        return this.cache.get(name);
    }
}
