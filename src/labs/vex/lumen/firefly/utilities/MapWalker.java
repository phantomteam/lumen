package labs.vex.lumen.firefly.utilities;

import labs.vex.lumen.firefly.exceptions.IllegalFootprintException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class used for map walking
 *
 * @author vex | Ciobanu Laurentiu
 */
public class MapWalker {

    /*
     * @todo: implement setting in MapWalker
     */
    /**
     * Used for walking a map, for getting or setting a value
     * at a specific location (key)
     *
     * @param map map in discussion
     * @param key location of the item
     * @param set value of the item
     * @return the value at the key
     * @throws IllegalFootprintException if the desired key is not accessible
     * @author vex | Ciobanu Laurentiu
     */
    public static Object walk(Map map, String key, Object set) throws IllegalFootprintException {
        String[] subKeys = key.split("\\.");
        if(subKeys.length == 0)
            throw new IllegalFootprintException("", key);

        Object current = map;
        List<String> footPrint = new ArrayList<>();
        for(String subKey: subKeys) {
            footPrint.add(subKey);
            if(current instanceof Map) {
                current = ((Map) current).get(subKey);

                if(current == null) {
                    StringBuilder fp = new StringBuilder();
                    for (String p: footPrint) fp.append(p + "");
                    throw new IllegalFootprintException(fp.substring(0, fp.length() - 1), key);
                }
            } else if(current instanceof ArrayList) {
                int index = Integer.parseInt(subKey);
                current = ((ArrayList) current).get(0);

                if(current == null) {
                    StringBuilder fp = new StringBuilder();
                    for (String p: footPrint) fp.append(p + "");
                    throw new IllegalFootprintException(fp.substring(0, fp.length() - 1), key);
                }
            } else  {
                StringBuilder fp = new StringBuilder();
                for (String p: footPrint) fp.append(p + "");
                throw new IllegalFootprintException(fp.substring(0, fp.length() - 1), key);
            }
        }

        return current;
    }
}
