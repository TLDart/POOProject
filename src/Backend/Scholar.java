package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

abstract public class Scholar extends Person implements Serializable {
    protected int salary;
    private Calendar startDate;
    private Calendar endDate;
    private Project project;

    /**
     * Class Constructor that specifies the name,email,id,tasks to do,start date and end date of scholarship and project of a scholar
     */
    public Scholar(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate, Project project) {
        super(name, email, id, tasks);
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
    }

    /**
     * Class Constructor that specifies the name,email,id,tasks to do,start date and end date of scholarship
     */
    public Scholar(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate) {
        super(name, email, id, tasks);
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = null;
    }

    /**
     * @return Returns the wage of the Scholar
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @return Returns the starting date of the scholarship
     */
    public Calendar getStartDate() {
        return startDate;
    }

    /**
     * @return Returns the end date of the scholarship
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * @return Returns the project the scholar is working in
     * @see Project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the Project the scholar is working in
     *
     * @see Project
     */
    public void setProject(Project project) {
        this.project = project;
    }
}
