package labs.vex.lumen.firefly;

import java.util.Map;

/**
 * Used as a basic contract to define what a loader is
 *
 * @author vex | Ciobanu Laurentiu
 */
public interface ILoader {
    /**
     * Used to load all the configuration by name into
     * a configuration manager
     *
     * @param batch (name, content) pair map
     * @return loaded ConfigurationManager
     * @author vex | Ciobanu Laurentiu
     */
    ConfigurationManager load(Map<String, String> batch);
}
