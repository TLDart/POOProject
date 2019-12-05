package Project;

import java.util.ArrayList;
import java.util.Calendar;

abstract class Student extends Scholar {
    protected ArrayList<Teacher> coordinator;

    public Student(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, Project project, ArrayList<Teacher> coordinator) {
        super(name, email, id, tasks, startDate, endDate, project);
        this.coordinator = coordinator;
    }
}
