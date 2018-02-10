package labs.vex.lumen;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import labs.vex.lumen.dexter.EngineException;
import labs.vex.lumen.firefly.ILoader;
import labs.vex.lumen.firefly.exceptions.IllegalFootprintException;
import labs.vex.lumen.hardware.Hardware;
import labs.vex.lumen.hardware.HardwareProcessor;
import labs.vex.lumen.hardware.IHardwareHandler;
import labs.vex.lumen.module.Descriptor;
import labs.vex.lumen.module.IDescriber;
import labs.vex.lumen.module.ModuleProcessor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Core class used to bootstrap the engine
 *
 * @author vex | Ciobanu Laurentiu
 */
public class Bootstrap {
    public IDescriber describer;
    public IHardwareHandler hardwareHandler;
    public ILoader loader;

    public Descriptor currentDescriptor;
    public Hardware[] currentHardwareList;

    public Bootstrap(IDescriber describer, IHardwareHandler hardwareHandler, ILoader loader) {
        this.describer = describer;
        this.hardwareHandler = hardwareHandler;
        this.loader = loader;
    }

    public void bootstrap(String path) throws IllegalAccessException, IOException, NoSuchFieldException, InstantiationException, IllegalFootprintException, InvocationTargetException, ClassNotFoundException, EngineException {
        Descriptor descriptor = describer.describe(path);
        Hardware[] hardwareList = describer.analyze(descriptor);

        this.currentDescriptor = descriptor;
        this.currentHardwareList = hardwareList;

        HardwareProcessor.process(hardwareList, this.hardwareHandler);
        ModuleProcessor.process(descriptor, this.loader);
    }
}
