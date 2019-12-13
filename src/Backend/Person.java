package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

abstract public class Person implements Serializable {
    private final String name;
    private final String email;
    private int id;
    protected ArrayList<Task> tasks;

    /**
     * Class Constructor with the name,email,id and tasks of a Person
     */
    public Person(String name, String email, int id, ArrayList<Task> tasks) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.tasks = tasks;
    }

    /**
     * Verifies if said person has a workload of 1 or more
     *
     * @param date Date used to verify if the Person is overloaded
     * @return true if the person is overloaded, false if it is not
     */
    public boolean overloaded(Calendar date) {
        return calcWorkload(date) >= 1;
    }

    /**
     * Overrides toString to the person name
     *
     * @return person's name
     */
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
            if ((date.after(t.getStartDate()) || date.equals(t.getStartDate())) && date.before(t.getEstimatedFinish())) {
                workload += t.getEffortRate();
            }
        }
        return workload;
    }

    /**
     * Adds a task to the task array
     *
     * @param t Task to add
     * @see Task
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes the task from the task array
     *
     * @param t Task to remove
     * @see Task
     */
    public void remTask(Task t) {
        this.tasks.remove(t);
    }

    /**
     * @return Returns the person's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the person's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Returns the person's id
     */
    public int getId() {
        return id;
    }

    /**
     * @return Returns an array list with the person's tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
