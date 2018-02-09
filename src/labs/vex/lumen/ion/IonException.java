package labs.vex.lumen.ion;

/**
 * Exception thrown from within the Ions
 *
 * @author vex | Ciobanu Laurentiu
 */
public class IonException extends Exception {
    public IonException(String accessor) {
        super("IonException triggered at: <" + accessor + ">!");
    }
}
