package Backend;

import java.io.Serializable;
import java.util.Calendar;

public class Documentation extends Task implements Serializable {
    /**
     * Class Constructor with id, start date and estimated end of a Design Task
     */
    public Documentation(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 0.25;
    }

    /**
     * Class Constructor with id,start date, estimated end date, responsible and status of a Design Task
     */
    public Documentation(int id, Calendar startDate, Calendar estimatedFinish, Person responsible, int status) {
        super(id, startDate, estimatedFinish, responsible, status);
        this.effortRate = 0.25;
    }

    /**
     * Class Constructor with id, start date, estimated end date, responsible, status and finishing date of a Documentation Task
     */
    public Documentation(int id, Calendar startDate, Calendar estimatedFinish, Calendar endTime, Person responsible, int status) {
        super(id, startDate, estimatedFinish, endTime, responsible, status);
        this.effortRate = 0.25;
    }

    /**
     * Overrides toString method to get desired output of the object instead of an internal toString call
     *
     * @return Returns the desired output
     */
    @Override
    public String toString() {
        return "Documentation";
    }
}
