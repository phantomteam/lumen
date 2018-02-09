package labs.vex.lumen.firefly;

import labs.vex.lumen.firefly.exceptions.IllegalFootprintException;

/**
 * Interface must be respected by any Abstract or Concrete implementations
 * of configurations returned and used by any configuration manager
 *
 * @author      vex | Ciobanu Laurentiu
 */
public interface IConfiguration {
    /**
     * Used to check if the configuration has any required key before
     * trying accessing it
     *
     * @param key   the key of the item you
     *              are looking for.
     *              try to be concise.
     *              respect JSON dot notation
     * @return      boolean value, true if the key exists, false otherwise
     * @throws      IllegalFootprintException   if the desired key is not accessible
     * @author      vex | Ciobanu Laurentiu
     */
    boolean has(String key) throws IllegalFootprintException;

    /**
     * Used to add or replace an item in the current configuration
     *
     * @param key       the location of the item
     *                  you wish to update.
     *                  try to be concise.
     *                  respect JSON dot notation
     * @param value     the value of the item you
     *                  wish to update
     * @return          boolean value, true if created, false otherwise
     * @throws          IllegalFootprintException   if the desired key is not accessible
     * @author          vex | Ciobanu Laurentiu
     */
    boolean set(String key, Object value) throws IllegalFootprintException;

    /**
     * Used to obtain the value of an item in the current configuration at
     * any specific key
     *
     * @param key   the location of the item
     *              where the value is stored.
     *              try to be concise.
     *              respect JSON dot notation.
     * @return      the value of the item at the specific location. might be null, Map or ArrayList
     * @throws      IllegalFootprintException   if the desired key is not accessible
     * @author      vex | Ciobanu Laurentiu
     */
    Object get(String key) throws IllegalFootprintException;
}
