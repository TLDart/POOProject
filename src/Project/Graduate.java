package Project;

import java.util.ArrayList;
import java.util.Calendar;

public class Graduate extends Student {

    public Graduate(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, Project project, ArrayList<Teacher> coordinator, int salary) {
        super(name, email, id, tasks, startDate, endDate, project, coordinator);
        this.salary = 800;
    }

}
