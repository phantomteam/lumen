package labs.vex.lumen.hardware;

/**
 * Implement this to help conversion between hardware components and actual devices
 *
 * @author vex | Ciobanu Laurentiu
 */
public interface IHardwareHandler {
    /**
     * Method called from the hardware processor
     *
     * @param h the hardware component to be converted
     * @return instance of the device
     * @author vex | Ciobanu Laurentiu
     */
    Object handle(Hardware h);
}
