package Project;

import java.util.Calendar;

public class Development extends Task {
    int workload = 1;

    public Development(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
    }
}
