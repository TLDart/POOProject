package Backend;

import java.io.Serializable;
import java.util.Calendar;

public class Development extends Task implements Serializable {

    public Development(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 1;
    }

    public Development(int id, Calendar startDate, Calendar estimatedFinish, Person resposible) {
        super(id, startDate, estimatedFinish, resposible);
    }

    @Override
    public String toString() {
        return "Development";
    }
}
