package Project;

import java.util.Calendar;

public class Documentation extends Task {

    public Documentation(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 0.25;
    }
}
