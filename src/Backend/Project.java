package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Project implements Serializable {
    private String name;
    private String nick;
    private Calendar startDate;
    private Calendar estimatedEnd;
    private Calendar endDate;
    private Teacher mainTeacher;
    private Boolean finished;
    private ArrayList<Teacher> teachers;
    private ArrayList<Scholar> scholars;
    private ArrayList<Task> tasks;

    /**
     * Class constructor specifying the name,nickname,startDate,deadline,main teacher and other people in the project
     */
    public Project(String name, String nick, Calendar startDate, int duration, Teacher mainTeacher, ArrayList<Teacher> teachers, ArrayList<Scholar> scholars, ArrayList<Task> tasks) {
        Calendar endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.MONTH, duration);
        this.name = name;
        this.nick = nick;
        this.startDate = startDate;
        this.estimatedEnd = endDate;
        this.mainTeacher = mainTeacher;
        this.teachers = teachers;
        this.scholars = scholars;
        this.tasks = tasks;
        this.finished = false;
        this.endDate = null;
    }

    /**
     * Class constructor specifying the name, nickname, startDate,duration, finishedDate, main Teacher, teachers, scholars and Tasks currently in the project
     */
    public Project(String name, String nick, Calendar startDate, int duration, Calendar finishedDate, Teacher mainTeacher, ArrayList<Teacher> teachers, ArrayList<Scholar> scholars, ArrayList<Task> tasks) {
        Calendar endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.MONTH, duration);
        this.name = name;
        this.nick = nick;
        this.startDate = startDate;
        this.estimatedEnd = endDate;
        this.mainTeacher = mainTeacher;
        this.teachers = teachers;
        this.scholars = scholars;
        this.tasks = tasks;
        this.finished = true;
        this.endDate = finishedDate;
    }

    /**
     * Class constructor specifying the name,nickname,startDate,duration and main teacher of the project
     */
    public Project(String name, String nick, Calendar startDate, int duration, Teacher mainTeacher) {
        Calendar endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.MONTH, duration);
        this.name = name;
        this.nick = nick;
        this.startDate = startDate;
        this.estimatedEnd = endDate;
        this.mainTeacher = mainTeacher;
        this.endDate = null;
        this.teachers = new ArrayList<>();
        this.scholars = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.finished = false;
    }

    public ArrayList<Task> listTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getStatus() == 100) {
                tasks.add(t);
                System.out.println(t.getId());
            }
        }
        return tasks;
    }

    /**
     * Deletes a task from the project
     *
     * @param t Task to delete
     * @return Returns true if removed
     * @see Task
     */
    public boolean deleteTask(Task t) {
        return this.tasks.remove(t);
    }

    /**
     * Lists all the tasks which status is 0
     *
     * @return Returns an array list of all the finished tasks
     * @see Task
     */
    public ArrayList<Task> listNotStarted() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getStatus() == 0) {
                tasks.add(t);
            }
        }
        return tasks;
    }

    /**
     * Lists all the tasks which status is 100
     *
     * @return Returns an array list of all the finished tasks
     * @see Task
     */
    public ArrayList<Task> listConcluded() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getStatus() == 100) {
                tasks.add(t);
            }
        }
        return tasks;
    }

    /**
     * Calculates the total cost of the project
     *
     * @return Returns the cost of the project
     * @see Scholar
     */
    public int getTotalPrice() {
        int total = 0;
        for (Scholar s : this.scholars) {
            Calendar start = (Calendar) s.getStartDate().clone();
            while (start.before(s.getEndDate())) {
                if (start.after(this.startDate) && start.before(this.estimatedEnd)) { //Only lists scholar within the the time frame the project is active
                    total += s.getSalary();
                }
                start.add(Calendar.MONTH, 1);
            }
        }
        return total;
    }

    /**
     * @return mainTeacher in the project
     */
    public Teacher getMainTeacher() {
        return mainTeacher;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * @return Scholars associated with the project
     */
    public ArrayList<Scholar> getScholars() {
        return scholars;
    }

    /**
     * @return name of the Project
     */
    public String getName() {
        return name;
    }

    /**
     * @return Task associated with the project
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @return Estimated end of the project
     */
    public Calendar getEstimatedEnd() {
        return estimatedEnd;
    }

    /**
     * @return Start date of the project
     */
    public Calendar getStartDate() {
        return startDate;
    }

    /**
     * @return Returns if project is finished
     */
    public Boolean getFinished() {
        return finished;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the project as finished
     *
     * @param finished
     */
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /**
     * @param endDate end date of the Project
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    /**
     * Overrides to string to return the name of the project, very useful when adding projects to combo lists, making the string displayed on the combo list the project name
     *
     * @return String with the name of the project
     */
    @Override
    public String toString() {
        return this.name;
    }
}
