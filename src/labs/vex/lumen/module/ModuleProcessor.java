package labs.vex.lumen.module;

import labs.vex.lumen.Lumen;
import labs.vex.lumen.dexter.EngineException;
import labs.vex.lumen.firefly.ILoader;
import labs.vex.lumen.firefly.exceptions.IllegalFootprintException;
import labs.vex.lumen.dexter.Curiosity;
import labs.vex.lumen.firefly.ConfigurationManager;
import labs.vex.lumen.ion.Ion;
import labs.vex.lumen.ion.IonManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to process a describer
 *
 * @author vex | Ciobanu Laurentiu
 */
public class ModuleProcessor {
    /**
     * Mixed method used to process a descriptor using a loader
     *
     * @param descriptor input descriptor
     * @param loader the loader used for configuration
     * @throws IOException Standard IO Exception
     * @throws ClassNotFoundException Standard Reflection Exception
     * @throws IllegalAccessException Standard Reflection Exception
     * @throws NoSuchFieldException Standard Reflection Exception
     * @throws IllegalFootprintException Standard Reflection Exception
     * @throws InvocationTargetException Standard Reflection Exception
     * @throws InstantiationException Standard Reflection Exception
     * @throws EngineException Standard Engine Exception
     * @author vex | Ciobanu Laurentiu
     */
    public static void process(Descriptor descriptor, ILoader loader) throws IOException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, IllegalFootprintException, InvocationTargetException, InstantiationException, EngineException {
        for(Module module: descriptor.modules) {
            Map<String, String> include = new HashMap<>();
            for(String configurationName: module.configuration){
                String path = module.name + "/" + configurationName + ".json";
                include.put(configurationName, path);
            }

            ConfigurationManager manager = loader.load(include);
            Lumen.firefly().daemon().load(module.pack, manager);
        }

        for(Class c: Curiosity.explore(descriptor.root)) {
            Lumen.firefly().daemon().handle(c);
        }

        for(Module module: descriptor.modules) {
            for(Class c: Curiosity.explore(descriptor.root + "." + module.pack)) {
                Object instance = Lumen.anemoi().handle(c);
                if(instance != null && Ion.class.isAssignableFrom(instance.getClass())) {
                    IonManager.register((Ion) instance);
                }
            }
        }
        IonManager.build();
    }

}
