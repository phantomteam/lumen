package labs.vex.lumen.ion.service;

public interface IService {
    void setup(ServiceHandler handler);
    void kill();
    void inject(ServicePacket packet);
}
