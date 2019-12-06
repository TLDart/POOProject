package Project;

import java.util.ArrayList;
import java.util.Calendar;

public class Center {
    private String name;
    private ArrayList<Teacher> teachers;
    private ArrayList<Scholar> scholars;
    private ArrayList<Project> projects;

    public Center(String name, ArrayList<Teacher> teachers, ArrayList<Scholar> scholars, ArrayList<Project> projects) {
        this.name = name;
        this.teachers = teachers;
        this.scholars = scholars;
        this.projects = projects;
    }

    public Center() {
    }

    public Boolean bootloader(String path) {
        return true;

    }

    public Boolean simpleBootloader(String name, String path) {
        return true;

    }

    public void createProject(String name, String nick, Calendar startDate, Calendar endDate, Teacher mainTeacher) {
        projects.add(new Project(name, nick, startDate, endDate, mainTeacher));

    }

    public void addTeacherToProject(Project p, Teacher t) {
        p.getTeachers().add(t);
    }

    public void addScholarToProject(Project p, Scholar s) {
        p.getScholars().add(s);
    }

    public boolean VerifyIfInProject(Person p, Project pr) {
        if(pr.getMainTeacher().getId() == p.getId()){
            return true;
        }
        for(Scholar s: pr.getScholars()){
            if(s.getId() == p.getId()){
                return true;
            }
        }
        for(Teacher t :pr.getTeachers()){
            if(t.getId() == p.getId()){
                return true;
            }
        }
        return false;
    }
    public Person getPersonbByName(String s){
        for(Scholar sc: this.scholars){
            if(sc.getName().equals(s)){
                return sc;
            }
        }
        for(Teacher t : this.teachers){
            if(t.getName().equals(s)){
                return t;
            }
        }
        return null;
    }
    public Teacher getTeacherbyName(String name){
         for(Teacher t : this.teachers){
             if(t.getName().equals(name)){
                 return t;
             }
         }
         return null;
    }
    public Teacher getTeacherbyID(int id){
        for(Teacher t: this.teachers){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    public Project getProjectByName(String name){
        for(Project p: this.projects){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    public Project getProjectByNick(String nick){
        for(Project p: this.projects){
            if(p.getNick().equals(nick)){
                return p;
            }
        }
        return null;
    }

    public Scholar getScholarByName(String name) {
        for (Scholar s : this.scholars) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Project> listFinished() {
        ArrayList<Project> projects = new ArrayList<>();
        for (Project p : this.projects) {
            if (isFinished(p))
                projects.add(p);
        }
        return projects;
    }

    public ArrayList<Project> listNotFinished() {
        ArrayList<Project> projects = new ArrayList<>();
        for (Project p : this.projects) {
            if (!isFinished(p))
                projects.add(p);
        }
        return projects;
    }

    private Boolean isFinished(Project p) {
        //Returns true if a project is finished
        Calendar today = Calendar.getInstance();
        for (Task t : p.getTasks()) {
            if (t.status != 100) {
                return false;
            }
        }
        if (today.after(p.getEstimatedEnd()) || today.equals(p.getEstimatedEnd()))
            return true;
        return false;
    }


}
