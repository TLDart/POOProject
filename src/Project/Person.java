package Project;

import java.util.ArrayList;

abstract class Person {
    private final String name;
    private final String email;
    private int id;
    protected int workload;
    protected ArrayList<Task> tasks;

    public Person(String name, String email, int id, ArrayList<Task> tasks) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.tasks = tasks;
    }

    public boolean overloaded(){
        return true;
    }
    public void calcWorkload(){

    }
    public void addTask(){

    }
    public void remTask(){

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

    public int getWorkload() {
        return workload;
    }
}
