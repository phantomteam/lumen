package labs.vex.lumen.hardware;

/**
 * Hardware component that has to be parsed for the hardware processor
 *
 * @author vex | Ciobanu Laurentiu
 */
public class Hardware {
    /**
     * Name of the hardware component
     */
    public String name;
    /**
     * The type of the hardware component
     */
    public String type;
    /**
     * The accessor to resolve in your hardware map
     */
    public String accessor;
    /**
     * Only used by composite hardware
     */
    public Hardware[] contains = new Hardware[] {};
    /**
     * Filled by the hardware handler
     */
    public Object actual;
}
