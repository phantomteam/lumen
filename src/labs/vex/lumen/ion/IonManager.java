package labs.vex.lumen.ion;

import java.util.*;

/**
 * Class used for flow control inside the C
 *
 * @author vex | Ciobanu Laurentiu
 */
public class IonManager {
    /**
     * Internal ion cache
     */
    public static Map<String, List<Ion>> cache = new HashMap<>();

    /**
     * Method used to register an Ion in the internal cache
     *
     * @param ion the ion that needs to be registered
     * @author vex | Ciobanu Laurentiu
     */
    public static void register(Ion ion) {
        List<Ion> set = cache.get(ion.accessor);
        if(set == null) {
            set = new ArrayList<>();
        }
        set.add(ion);
        cache.put(ion.accessor, set);
    }

    /**
     * Used to rebuild the cache
     *
     * @author vex | Ciobanu Laurentiu
     */
    public static void build() {
        for(Map.Entry entry: cache.entrySet()) {
            List<Ion> set = (List<Ion>) entry.getValue();
            Collections.sort(set);
            cache.put((String) entry.getKey(), set);
        }
    }

    /**
     * Retrieve Ion for accessor
     * @param accessor name of the ion
     * @return instance of the ion
     * @throws IonException when something is wrong with the ion
     * @author vex | Ciobanu Laurentiu
     */
    public static  List<Ion> getForAccessor(String accessor) throws IonException {
        List<Ion> set = cache.get(accessor);
        if(set == null)
            throw new IonException(accessor);
        return set;
    }

    /**
     * Init/boot/tick/kill an ion for a specific accessor
     * @param accessor name of the ion
     * @throws IonException when something is wrong with the ion
     * @author vex | Ciobanu Laurentiu
     */
    public static void initAccessor(String accessor) throws IonException {
        List<Ion> set = getForAccessor(accessor);
        for(Ion ion: set) {
            ion.init();
        }
    }

    /**
     * Init/boot/tick/kill an ion for a specific accessor
     * @param accessor name of the ion
     * @throws IonException when something is wrong with the ion
     * @author vex | Ciobanu Laurentiu
     */
    public static void bootAccessor(String accessor) throws IonException {
        List<Ion> set = getForAccessor(accessor);
        for(Ion ion: set) {
            ion.boot();
        }
    }

    /**
     * Init/boot/tick/kill an ion for a specific accessor
     * @param accessor name of the ion
     * @throws IonException when something is wrong with the ion
     * @author vex | Ciobanu Laurentiu
     */
    public static void killAccessor(String accessor) throws IonException {
        List<Ion> set = getForAccessor(accessor);
        for(Ion ion: set) {
            ion.kill();
        }
    }

    /**
     * Init/boot/tick/kill an ion for a specific accessor
     * @param accessor name of the ion
     * @throws IonException when something is wrong with the ion
     * @author vex | Ciobanu Laurentiu
     */
    public static void tickAccessor(String accessor) throws IonException {
        List<Ion> set = getForAccessor(accessor);
        for(Ion ion: set) {
            ion.tick();
        }
    }
}
