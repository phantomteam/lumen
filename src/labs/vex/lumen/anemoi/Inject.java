package labs.vex.lumen.anemoi;

import java.lang.annotation.*;

/**
 * Inject annotation used inside Anemoi
 *
 * @author vex | Ciobanu Laurentiu
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject { }
