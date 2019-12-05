package Project;

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
    private String name;
    private String nick;
    private Calendar startDate;
    private Calendar endDate;
    private Teacher mainTeacher;
    private Boolean finished;
    private ArrayList<Teacher> teachers;
    private ArrayList<Scholar> scholars;
    private ArrayList<Task> tasks;

    public Project(String name, String nick, Calendar startDate, Calendar endDate, Teacher mainTeacher) {
        this.name = name;
        this.nick = nick;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mainTeacher = mainTeacher;
    }

    public void createTask(int type,int id, Calendar startDate, Calendar estimatedFinish, Calendar endTime) {
        if(type == 1)
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
        this.tasks.remove(t);
    }
    public Task getTaskById(int id){
        for(Task t: this.tasks){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    public boolean verifyValidDate(){

    }
    public void updateTaskStatus(Task t, int value){
        t.setStatus(value);
    }
    public void giveTask(Person p,Task t){

    }
    public ArrayList<Task> listNotStarted(){
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task t : this.tasks){
            if(t.getStatus() == 0){
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
                if (t.endTime.after(t.estimatedFinish)) {
                    tasks.add(t);
                }
            }
        }
        return tasks;
    }
    public int getTotalPrice(){
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
}
