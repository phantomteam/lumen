package labs.vex.lumen.hardware;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class used to convert from Hardware components to actual devices
 *
 * @author vex | Ciobanu Laurentiu
 */
public class HardwareProcessor {
    /**
     * Internal hardware map
     */
    public static Map<String, Hardware> hardwareMap = new HashMap<>();

    /**
     * Convert batch of hardware components
     *
     * @param hardwareList the batch of components
     * @param handler the hardware handler used in process
     * @author vex | Ciobanu Laurentiu
     */
    public static void process(Hardware[] hardwareList, IHardwareHandler handler) {
        for(Hardware hardware: hardwareList) {
            if(hardware.accessor == null && hardware.contains.length == 0){
                continue;
            }

            if(!hardware.type.equals("component")) {
                HardwareProcessor.process(hardware, handler, null);
            } else {
                for(Hardware sub: hardware.contains) {
                    HardwareProcessor.process(sub, handler, hardware);
                }
            }
        }
    }

    /**
     * Convert the specific hardware component to its actual device and store it in the internal hardware map for later usage
     *
     * @param hardware the component
     * @param handler the handler that will be used
     * @param parent the parent of the component (use null if it's a root)
     * @author vex | Ciobanu Laurentiu
     */
    public static void process(Hardware hardware, IHardwareHandler handler, Hardware parent) {
        hardware.actual = handler.handle(hardware);
        String above = (parent == null ? "" : (parent.name + "."));
        hardwareMap.put(above + hardware.name, hardware);
    }

    /**
     * Retrieve the actual device from the internal hardware map
     *
     * @param name the name of the device
     * @param <T> type of the device
     * @return Device of type T
     */
    public static<T> T get(String name) {
        Hardware h = hardwareMap.get(name);
        if(h == null)
            return null;
        if(h.actual == null)
            return null;

        return (T) h.actual;
    }

    /**
     * Return the hardware component used before conversion
     *
     * @param name name of the device
     * @return Hardware component before conversion
     * @author vex | Ciobanu Laurentiu
     */
    public static Hardware getAbsolute(String name) {
        return hardwareMap.get(name);
    }
}
