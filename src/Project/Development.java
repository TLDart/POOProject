package Project;

import java.util.Calendar;

public class Development extends Task {

    public Development(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 1;
    }
}
