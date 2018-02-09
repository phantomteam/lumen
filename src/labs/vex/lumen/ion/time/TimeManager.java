package labs.vex.lumen.ion.time;

import java.util.Timer;

/**
 * Utility class for managing TimeThreads
 *
 * @author vex | Ciobanu Laurentiu
 */
public class TimeManager {
    /**
     * Used to start a TimeThread
     *
     * @param time the TimeThread
     * @param period repeat period
     * @return Timer object used to stop
     * @author vex | Ciobanu Laurentiu
     */
    public static Timer start(TimeThread time, int period) {
        Timer timer = new Timer(time.name);
        timer.scheduleAtFixedRate(time, 0, period);
        return timer;
    }
}
