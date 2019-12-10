package Backend;

import java.io.Serializable;
import java.util.Calendar;

public class Documentation extends Task implements Serializable {

    public Documentation(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 0.25;
    }

    public Documentation(int id, Calendar startDate, Calendar estimatedFinish, Person responsible) {
        super(id, startDate, estimatedFinish, responsible);
    }

    @Override
    public String toString() {
        return "Documentation";
    }
}
