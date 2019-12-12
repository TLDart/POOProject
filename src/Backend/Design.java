package Backend;

import java.io.Serializable;
import java.util.Calendar;

public class Design extends Task implements Serializable {

    public Design(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 0.5;
    }

    public Design(int id, Calendar startDate, Calendar estimatedFinish, Person responsible, int status) {
        super(id, startDate, estimatedFinish, responsible, status);
        this.effortRate = 0.5;
    }

    public Design(int id, Calendar startDate, Calendar estimatedFinish, Person responsible, int status, Calendar endTime) {
        super(id, status, startDate, estimatedFinish, endTime, responsible);
        this.effortRate = 0.5;
    }

    @Override
    public String toString() {
        return "Design";
    }
}
