package labs.vex.lumen.ion;

/**
 * Class used for flow control inside the app
 *
 * @author vex | Ciobanu Laurentiu
 */
public abstract class Ion implements Comparable<Ion> {
    public String accessor;
    public int priority;

    public Ion(String accessor, int priority) {
        this.accessor = accessor;
        this.priority = priority;
    }

    @Override
    public int compareTo(Ion ion) {
        return Integer.compare(this.priority, ion.priority);
    }

    public void init() {}
    public void boot() {}
    public void tick() {}
    public void kill() {}
}
