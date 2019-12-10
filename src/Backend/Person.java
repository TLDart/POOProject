package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

abstract public class Person implements Serializable {
    private final String name;
    private final String email;
    private int id;
    protected ArrayList<Task> tasks;

    public Person(String name, String email, int id, ArrayList<Task> tasks) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.tasks = tasks;
    }

    public boolean overloaded(Calendar date) {
        return calcWorkload(date) == 1;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Calculates the workload on a given date
     *
     * <p>
     * Iterates through every task verifying if the task "overlaps" the current {@code date}, and, if it does, adds the {@code effortRate}
     * </p>
     *
     * @param date Current date that it used has basis for the calculation
     * @return Float containing the current workload on a given date
     */
    public float calcWorkload(Calendar date) {
        float workload = 0;
        for (Task t : this.tasks) {
            if (date.after(t.getStartDate()) && date.before(t.getEstimatedFinish())) {
                workload += t.getEffortRate();
            }
        }
        return workload;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void remTask(Task t) {
        this.tasks.remove(t);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
