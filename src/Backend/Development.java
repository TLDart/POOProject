package Backend;

import java.io.Serializable;
import java.util.Calendar;

public class Development extends Task implements Serializable {
    public Development(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 1;
    }

    public Development(int id, Calendar startDate, Calendar estimatedFinish, Person responsible, int status) {
        super(id, startDate, estimatedFinish, responsible, status);
        this.effortRate = 1;
    }

    public Development(int id, Calendar startDate, Calendar estimatedFinish, Calendar endTime, Person responsible, int status) {
        super(id, startDate, estimatedFinish, endTime, responsible, status);
        this.effortRate = 1;
    }

    /**
     * Overrides toString method to get desired output of the object instead of an internal toString call
     *
     * @return Returns the desired output
     */
    @Override
    public String toString() {
        return "Development";
    }
}
