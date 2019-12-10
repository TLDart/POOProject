package Backend;

import java.io.Serializable;
import java.util.Calendar;

public class Design extends Task implements Serializable {

    public Design(int id, Calendar startDate, Calendar estimatedFinish) {
        super(id, startDate, estimatedFinish);
        this.effortRate = 0.5;
    }

    public Design(int id, Calendar startDate, Calendar estimatedFinish, Person responsible) {
        super(id, startDate, estimatedFinish, responsible);
    }

    @Override
    public String toString() {
        return "Design";
    }
}
