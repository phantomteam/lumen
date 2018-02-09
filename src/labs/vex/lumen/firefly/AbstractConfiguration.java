package labs.vex.lumen.firefly;

import labs.vex.lumen.firefly.exceptions.IllegalFootprintException;
import labs.vex.lumen.firefly.utilities.MapWalker;

import java.util.Map;

public class AbstractConfiguration implements IConfiguration {

    protected Map cache = null;

    /**
     * Constructor used only by child classes
     *
     * @param cache can be used to pass an
     *              initial set of data
     *              or defaults
     * @author vex | Ciobanu Laurentiu
     */
    protected AbstractConfiguration(Map cache) {
        if(cache != null) {
            this.cache = cache;
        }
    }

    /**
     * Used to check if the configuration has any required key before
     * trying accessing it
     *
     * @param key the key of the item you
     *            are looking for.
     *            try to be concise.
     *            respect JSON dot notation
     * @return boolean value, true if the key exists, false otherwise
     * @throws IllegalFootprintException if the desired key is not accessible
     * @author vex | Ciobanu Laurentiu
     */
    @Override
    public boolean has(String key) throws IllegalFootprintException {
        Object value = MapWalker.walk(this.cache, key, null);
        return value != null;
    }

    /**
     * Used to add or replace an item in the current configuration
     *
     * @param key   the location of the item
     *              you wish to update.
     *              try to be concise.
     *              respect JSON dot notation
     * @param value the value of the item you
     *              wish to update
     * @return boolean value, true if created, false otherwise
     * @throws IllegalFootprintException if the desired key is not accessible
     * @author vex | Ciobanu Laurentiu
     */
    @Override
    public boolean set(String key, Object value) throws IllegalFootprintException {
        return MapWalker.walk(this.cache, key, value) != null;
    }

    /**
     * Used to obtain the value of an item in the current configuration at
     * any specific key
     *
     * @param key the location of the item
     *            where the value is stored.
     *            try to be concise.
     *            respect JSON dot notation.
     * @return the value of the item at the specific location. might be null, Map or ArrayList
     * @throws IllegalFootprintException if the desired key is not accessible
     * @author vex | Ciobanu Laurentiu
     */
    @Override
    public Object get(String key) throws IllegalFootprintException {
        return MapWalker.walk(this.cache, key, null);
    }
}
