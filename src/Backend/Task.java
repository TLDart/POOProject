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

    public Task(int id, Calendar startDate, Calendar estimatedFinish) {
        this.id = id;
        this.startDate = startDate;
        this.estimatedFinish = estimatedFinish;
    }

    public Task(int id, Calendar startDate, Calendar estimatedFinish, Person responsible, int status) {
        this.id = id;
        this.startDate = startDate;
        this.estimatedFinish = estimatedFinish;
        this.responsible = responsible;
        this.status = status;
    }

    public Task(int id, int status, Calendar startDate, Calendar estimatedFinish, Calendar endTime, Person responsible) {
        this.id = id;
        this.status = status;
        this.startDate = startDate;
        this.estimatedFinish = estimatedFinish;
        this.endTime = endTime;
        this.responsible = responsible;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getEffortRate() {
        return effortRate;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public Person getResponsible() {
        return responsible;
    }

    public Calendar getEstimatedFinish() {
        return estimatedFinish;
    }

    public Calendar getStartDate() {
        return startDate;
    }

}
