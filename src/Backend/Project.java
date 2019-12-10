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

    public Project(String name, String nick, Calendar startDate, Calendar estimatedEnd, Teacher mainTeacher, ArrayList<Teacher> teachers, ArrayList<Scholar> scholars, ArrayList<Task> tasks) {
        this.name = name;
        this.nick = nick;
        this.startDate = startDate;
        this.estimatedEnd = estimatedEnd;
        this.mainTeacher = mainTeacher;
        this.teachers = teachers;
        this.scholars = scholars;
        this.tasks = tasks;
        this.finished = false;
        this.endDate = null;
    }

    public Project(String name, String nick, Calendar startDate, Calendar estimatedEnd, Teacher mainTeacher) {
        this.name = name;
        this.nick = nick;
        this.startDate = startDate;
        this.estimatedEnd = estimatedEnd;
        this.mainTeacher = mainTeacher;
        this.endDate = null;
        this.teachers = new ArrayList<>();
        this.scholars = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public void createTask(int type, int id, Calendar startDate, Calendar estimatedFinish, Calendar endTime) {
        if (type == 1)
            this.tasks.add(new Documentation (id, startDate, estimatedFinish));
        if(type == 2)
            this.tasks.add(new Development (id, startDate, estimatedFinish));
        if(type == 3)
            this.tasks.add(new Design (id, startDate, estimatedFinish));

    }
    public ArrayList<Task> listTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task t: this.tasks){
            if(t.getStatus() == 100){
                tasks.add(t);
                System.out.println(t.getId());
            }
        }
        return tasks;
    }
    public boolean deleteTask(Task t){
        return this.tasks.remove(t);
    }
    public Task getTaskById(int id){
        for(Task t: this.tasks){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    //public boolean verifyValidDate(){

    //}
    public void updateTaskStatus(Task t, int value) {
        t.setStatus(value);
    }

    public void giveTask(Person p, Task t) {//TODO: giving tasks to Scholars verify date
        if (!p.overloaded(t.getStartDate()) || p != this.mainTeacher) {
            p.addTask(t);
            t.responsible = p;
        }
    }

    public ArrayList<Task> listNotStarted() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getStatus() == 0) {
                tasks.add(t);
            }
        }
        return tasks;
    }
    public ArrayList<Task> listConcluded(){
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task t : this.tasks){
            if(t.getStatus() == 100){
                tasks.add(t);
            }
        }
        return tasks;
    }
    public ArrayList<Task> listNotConcludedOnDate(Calendar c){
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task t : this.tasks){
            if(t.endTime != null) {
                if (t.endTime.after(t.getEstimatedFinish())) {
                    tasks.add(t);
                }
            }
        }
        return tasks;
    }
    public int getTotalPrice() {
        int total = 0;
        for (Scholar s : this.scholars) {
            int monthSpan = (s.getStartDate().get(Calendar.MONTH) - s.getEndDate().get(Calendar.MONTH)) * 12 + (s.getStartDate().get(Calendar.MONTH) - s.getEndDate().get(Calendar.MONTH)) + 1;
            total += monthSpan * s.getSalary();
        }
        return total;
    }
    public void closeProject(){
        this.setFinished(true);
    }

    public Teacher getMainTeacher() {
        return mainTeacher;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Scholar> getScholars() {
        return scholars;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Calendar getEstimatedEnd() {
        return estimatedEnd;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
