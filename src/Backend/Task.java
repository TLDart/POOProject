package Backend;

import java.io.Serializable;
import java.util.Calendar;

abstract public class Task implements Serializable {
    private int id;
    protected int status;
    private Calendar startDate;
    private Calendar estimatedFinish;
    protected Calendar endTime;
    protected double effortRate;
    protected Person responsible;

    /**
     * Class Constructor with the id,start date and estimated finish date of a Task
     */
    public Task(int id, Calendar startDate, Calendar estimatedFinish) {// New task
        this.id = id;
        this.status = 0;
        this.startDate = startDate;
        this.estimatedFinish = estimatedFinish;
        this.endTime = null;
        this.responsible = null;
    }

    /**
     * Class Constructor with the id,start date,estimated finish date,responsible and status of a Task
     */
    public Task(int id, Calendar startDate, Calendar estimatedFinish, Person responsible, int status) {//Unfinished Task
        this.id = id;
        this.status = status;
        this.startDate = startDate;
        this.estimatedFinish = estimatedFinish;
        this.endTime = null;
        this.responsible = responsible;
    }

    /**
     * Class Constructor with the id,start date,estimated finish date,finishing date,responsible and status of a Task
     */
    public Task(int id, Calendar startDate, Calendar estimatedFinish, Calendar endTime, Person responsible, int status) {
        this.id = id;
        this.status = status;
        this.startDate = startDate;
        this.estimatedFinish = estimatedFinish;
        this.endTime = endTime;
        this.responsible = responsible;
    }

    /**
     * @return Returns the effor rate of a task
     */
    public double getEffortRate() {
        return effortRate;
    }

    /**
     * @return Returns the id of the task
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status % of the task completed
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return Id of the task
     */
    public int getId() {
        return id;
    }

    /**
     * @return Id of the task
     */
    public Calendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time for a task
     */
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    /**
     * @return Responsible for a task
     */
    public Person getResponsible() {
        return responsible;
    }

    /**
     * Sets the responsible for a task
     */
    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    /**
     * @return Returns the finishing date of the task
     */
    public Calendar getEstimatedFinish() {
        return estimatedFinish;
    }

    /**
     * @return StartDate
     */
    public Calendar getStartDate() {
        return startDate;
    }
}
