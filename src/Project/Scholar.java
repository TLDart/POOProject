package Project;

import java.util.ArrayList;
import java.util.Calendar;

abstract class Scholar extends Person{
    protected int salary;
    protected Calendar startDate;
    protected Calendar endDate;
    private Project project;

    public Scholar(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, Project project) {
        super(name, email, id, tasks);
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
    }

    public int getSalary() {
        return salary;
    }
}
