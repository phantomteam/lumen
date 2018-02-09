package labs.vex.lumen.anemoi;

import labs.vex.lumen.firefly.exceptions.IllegalFootprintException;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Anemoi is a very smart dependency injection following the DRY SOLID specs
 *
 * @author vex | Ciobanu Laurentiu
 */
public class Anemoi {
    /**
     * public method exposed to handle any class that extends the Injection Container (Moi)
     * @param clazz the class that is going to be analyzed
     * @return an object, the instance of the class
     * @throws IllegalAccessException standard reflection Exception
     * @throws NoSuchFieldException standard reflection Exception
     * @throws IllegalFootprintException standard reflection Exception
     * @throws InstantiationException standard reflection Exception
     * @throws InvocationTargetException standard reflection Exception
     */
    public Object handle(Class clazz) throws IllegalAccessException, NoSuchFieldException, IllegalFootprintException, InstantiationException, InvocationTargetException {
        if(!Moi.class.isAssignableFrom(clazz)){
            return null;
        }

        Constructor[] constructors = clazz.getDeclaredConstructors();
        List<Object> args = new ArrayList<>();
        Constructor constructor = constructors[0];
        for(Parameter p: constructor.getParameters()) {
            Class type = p.getType();
            if(!type.isAnnotationPresent(Dose.class)) {
                args.add(null);
                continue;
            }
            args.add(type.newInstance());
        }
        Object obj = constructor.newInstance(args.toArray());

        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) || !Modifier.isPublic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()))
                continue;

            if (!field.isAnnotationPresent(Inject.class))
                continue;

            Class dose = field.getType();
            if(!dose.isAnnotationPresent(Dose.class))
                continue;

            Object value = dose.newInstance();
            field.setAccessible(true);
            field.set(obj, value);
        }

        return obj;
    }
}
