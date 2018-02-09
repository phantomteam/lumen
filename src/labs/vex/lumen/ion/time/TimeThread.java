package labs.vex.lumen.ion.time;

import java.util.*;

/**
 * TimeThread used to sync moments
 *
 * @author vex | Ciobanu Laurentiu
 */
public class TimeThread extends TimerTask {
    public int time = 0;
    public String name;

    public Map<Integer, List<ICallback>> callbacks = new HashMap<>();
    public List<ICallback> periods = new ArrayList<>();

    /**
     * Used to describe a timer and link it's name
     *
     * @param name timer name
     * @author vex | Ciobanu Laurentiu
     */
    public TimeThread(String name) {
        this.name = name;
    }

    /**
     * Used to register a moment
     *
     * @param t time frame
     * @param callback moment callback
     * @author vex | Ciobanu Laurentiu
     */
    public void at(Integer t, ICallback callback) {
        List<ICallback> callbacks = this.callbacks.get(t);
        if(callbacks == null)
            callbacks = new ArrayList<>();
        callbacks.add(callback);
        this.callbacks.put(t, callbacks);
    }

    /**
     * used to register a period
     *
     * @param callback period callback
     * @author vex | Ciobanu Laurentiu
     */
    public void period(ICallback callback) {
        this.periods.add(callback);
    }

    /**
     * Use to execute the current time frame
     *
     * @param t current time frame
     * @author vex | Ciobanu Laurentiu
     */
    public void execute(Integer t) {
        List<ICallback> callbacks = this.callbacks.get(t);
        if(callbacks == null)
            callbacks = new ArrayList<>();

        for(ICallback callback: callbacks) {
            callback.call();
        }
    }

    /**
     * Used to tick all periods
     *
     * @author vex | Ciobanu Laurentiu
     */
    public void tick() {
        for(ICallback period: periods) {
            period.call();
        }
    }

    @Override
    public void run() {
        this.tick();
        this.execute(time);
        time++;
    }

}