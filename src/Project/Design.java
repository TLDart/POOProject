package Project;

import java.util.Calendar;

public class Design extends Task {
    double workload = 0.5;

    public Design(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
    }
}
