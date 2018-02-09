package labs.vex.lumen.firefly;

import java.lang.annotation.*;

/**
 * Annotation used for the configuration daemon
 *
 * @author vex | Ciobanu Laurentiu
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configure {
    /**
     * Namespace of the ConfigurationManager
     *
     * @author vex | Ciobanu Laurentiu
     */
    String namespace();

    /**
     * ConfigurationManager's accessor key
     *
     * @author vex | Ciobanu Laurentiu
     */
    String config();

    /**
     * Configuration's accessor key
     *
     * @author vex | Ciobanu Laurentiu
     */
    String access() default "";
}
