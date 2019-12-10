package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

abstract class Student extends Scholar implements Serializable {
    protected ArrayList<Teacher> coordinator;

    public Student(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, ArrayList<Teacher> coordinator) {
        super(name, email, id, tasks, startDate, endDate);
        this.coordinator = coordinator;
    }
}
