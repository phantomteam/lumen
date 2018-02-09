package labs.vex.lumen.dexter;

/**
 * Contract should be implemented by any custom service discovery engine.
 * @author vex | Ciobanu Laurentiu
 */
public interface IEngine {
    /**
     * This method will be called by Curiosity when he needs your help!
     *
     * @param pack package that will be explored
     * @return array of class objects that the engine resolved
     * @throws EngineException when the engine fails loading resources
     */
    Class[] explore(String pack) throws EngineException;
}
