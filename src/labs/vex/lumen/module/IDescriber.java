package labs.vex.lumen.module;

import labs.vex.lumen.hardware.Hardware;

public interface IDescriber {
    Descriptor describe(String path);
    Hardware[] analyze(Descriptor descriptor);
}
