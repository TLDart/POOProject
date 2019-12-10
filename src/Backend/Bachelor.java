package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Bachelor extends Student implements Serializable {

    public Bachelor(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, ArrayList<Teacher> coordinator) {
        super(name, email, id, tasks, startDate, endDate, coordinator);
        this.salary = 1000;
    }
}
