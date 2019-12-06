package Project;

import java.util.Calendar;

public class Design extends Task {

    public Design(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 0.5;
    }
}
