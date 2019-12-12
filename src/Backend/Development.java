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

    public Development(int id, Calendar startDate, Calendar estimatedFinish, Person responsible, int status, Calendar endTime) {
        super(id, status, startDate, estimatedFinish, endTime, responsible);
        this.effortRate = 1;
    }

    @Override
    public String toString() {
        return "Development";
    }
}
