import java.util.TimerTask;
import java.util.Timer;

/**
 *
 * @author Fatema Shabbir 19201960
 */
public class Scheduler{
    
    /**
     * This method schedules all the plugins to run at a scheduled rate
     * Code adapted from: https://stackoverflow.com/questions/438312/how-to-schedule-a-callable-to-run-on-a-specific-time
     * Accessed 24/10/7
     * @param time
     * @param timerTask
     * @param delay
     * @return 
     * @throws java.lang.InterruptedException
     */
    public Timer schedule(long time, TimerTask timerTask, long delay) throws InterruptedException {    
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, delay, time);
        
        return timer;
        
    }
}
