package labs.vex.lumen.firefly;

import labs.vex.lumen.firefly.exceptions.IllegalFootprintException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Reflection daemon used for injection configuration into objects
 *
 * @author vex | Ciobanu Laurentiu
 */
public class Daemon {

    /**
     * Internal cache
     *
     * @author vex | Ciobanu Laurentiu
     */
    private Map<String, ConfigurationManager> cache = new HashMap<>();

    /**
     * Load a configuration manager into the internal cache
     * @param namespace used to specify the domain (Refer to @Configure)
     * @param manager used to specify the manager that is addressed
     * @author vex | Ciobanu Laurentiu
     */
    public void load(String namespace, ConfigurationManager manager) {
        this.cache.put(namespace, manager);
    }

    /**
     * Handle the injection of a class
     *
     * @param clazz the class in discussion
     * @throws IllegalAccessException when reflection fails
     * @throws NoSuchFieldException when reflection fails
     * @throws IllegalFootprintException check Configuration
     * @author vex | Ciobanu Laurentiu
     */
    public void handle(Class clazz) throws IllegalAccessException, NoSuchFieldException, IllegalFootprintException {
        if (!IConfigurable.class.isAssignableFrom(clazz))
            return;

        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isPublic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers()))
                continue;

            if (!field.isAnnotationPresent(Configure.class))
                continue;

            Configure configure = field.getAnnotation(Configure.class);

            ConfigurationManager manager = this.cache.get(configure.namespace());
            if(manager == null)
                continue;

            IConfiguration configuration = manager.get(configure.config());
            if(configuration == null)
                continue;

            Object value;
            if(!configure.access().equals("")) {
                value = configuration.get(configure.access());
            } else {
                if(IConfiguration.class.isAssignableFrom(field.getType())) {
                    value = configuration;
                } else {
                    continue;
                }
            }


            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, value);
            field.setAccessible(accessible);
        }
    }
}
