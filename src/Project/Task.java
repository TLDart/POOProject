package Project;

import java.util.Calendar;

abstract class Task {
    private int id;
    protected int status;
    protected Calendar startDate;
    protected Calendar estimatedFinish;
    protected Calendar endTime;
    protected double effortRate;
    protected Person resposible;
    public Task(int id, Calendar startDate, Calendar estimatedFinish) {
        this.id = id;
        this.startDate = startDate;
        this.estimatedFinish = estimatedFinish;
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


}
