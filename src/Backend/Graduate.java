package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Graduate extends Student implements Serializable {

    public Graduate(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, ArrayList<Teacher> coordinator) {
        super(name, email, id, tasks, startDate, endDate, coordinator);
        this.salary = 800;
    }

}
