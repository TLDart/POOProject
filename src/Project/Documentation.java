package Project;

import java.util.Calendar;

public class Documentation extends Task {
    double effortRate = 0.25;

    public Documentation(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
    }
}
