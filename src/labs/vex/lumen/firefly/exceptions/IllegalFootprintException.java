package labs.vex.lumen.firefly.exceptions;

/**
 * Exception thrown by firefly if the footprint is not a valid path!
 *
 * @author vex | Ciobanu Laurentiu
 */
public class IllegalFootprintException extends Exception {
    /**
     * Builds the thrown exception from the original key and the
     * active key footprint
     *
     * @param footPrint the actual footprint
     * @param original  the desired key footprint
     * @author vex | Ciobanu Laurentiu
     */
    public IllegalFootprintException(String footPrint, String original) {
        super("Invalid footprint detected! Trying to get to key <" + original + "> but got stuck at <" + footPrint + ">!" +
                "Please check stacktrace for more information!");
    }
}
