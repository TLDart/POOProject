package Project;

import java.util.ArrayList;
import java.util.Calendar;

public class PhD extends Scholar {
    int salary = 1200;
    public PhD(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, Project project) {
        super(name, email, id, tasks, startDate, endDate, project);
    }
}
