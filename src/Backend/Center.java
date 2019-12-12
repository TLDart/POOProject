package Backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Center implements Serializable {
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

    public Center(ArrayList<Teacher> teachers, ArrayList<Scholar> scholars, ArrayList<Project> projects) {
        this.teachers = teachers;
        this.scholars = scholars;
        this.projects = projects;
    }

    public Center(String name) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.scholars = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public Center() {
    }

    public Boolean bootloader(String path) {
        File f = new File(path);
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(f));
        } catch (IOException e) {
            System.out.print("RIP");
        }
        return true;

    }

    public Boolean simpleBootloader(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String s;
            String parsedData[];
            while ((s = br.readLine()) != null) {
                parsedData = s.split(",");
                if (parsedData[0].equals("Teacher")) {
                    this.teachers.add(new Teacher(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), parsedData[4], Integer.parseInt(parsedData[5])));
                }
                if (parsedData[0].equals("Bachelor")) {
                    String[] parsedCalendarA = parsedData[4].split("-");
                    String[] parsedCalendarB = parsedData[5].split("-");
                    ArrayList<Teacher> coordinators = new ArrayList<Teacher>();
                    for (int i = 6; i < parsedData.length; i++) {
                        coordinators.add(getTeacherbyName(parsedData[i]));
                    }

                    this.scholars.add(new Bachelor(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                            new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0])), coordinators));
                }
                if (parsedData[0].equals("Graduate")) {
                    String[] parsedCalendarA = parsedData[4].split("-");
                    String[] parsedCalendarB = parsedData[5].split("-");
                    ArrayList<Teacher> coordinators = new ArrayList<>();
                    for (int i = 6; i < parsedData.length; i++) {
                        coordinators.add(getTeacherbyName(parsedData[i]));
                    }

                    this.scholars.add(new Graduate(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                            new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0])), coordinators));
                }
                if (parsedData[0].equals("PhD")) {
                    String[] parsedCalendarA = parsedData[4].split("-");
                    String[] parsedCalendarB = parsedData[5].split("-");

                    this.scholars.add(new PhD(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                            new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0]))));

                }
                if (parsedData[0].equals("Project")) {
                    String[] parsedCalendarA = parsedData[3].split("-");
                    String[] parsedCalendarB = parsedData[4].split("-");
                    ArrayList<Teacher> teachers = new ArrayList<>();
                    ArrayList<Task> tasks = new ArrayList<>();
                    ArrayList<Scholar> scholars = new ArrayList<>();

                    for (int k = 5; k < parsedData.length; k++) {
                        if (parsedData[k].startsWith("/")) {
                            teachers.add(getTeacherbyName(parsedData[k].replace("/", "")));
                        }
                        if (parsedData[k].startsWith("_")) {
                            scholars.add(getScholarByName(parsedData[k].replace("_", "")));
                        }
                    }
                    Project p = new Project(parsedData[1], parsedData[2], new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                            new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0])), getTeacherbyName(parsedData[5]), teachers, scholars, tasks);
                    this.projects.add(p);
                    for (Scholar sc : scholars) {
                        sc.setProject(p);
                    }
                }

                if (parsedData[0].equals("Development")) {
                    String[] parsedCalendarA = parsedData[2].split("-");
                    String[] parsedCalendarB = parsedData[3].split("-");
                    if (parsedData.length == 5) {
                        getProjectByName(parsedData[4]).getTasks().add(new Development(Integer.parseInt(parsedData[1]), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                                new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0]))));
                    } else {
                        getProjectByName(parsedData[4]).getTasks().add(new Development(Integer.parseInt(parsedData[1]), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                                new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0])), getPersonbByName(parsedData[5]), Integer.parseInt(parsedData[6])));
                    }

                }
                if (parsedData[0].equals("Design")) {
                    String[] parsedCalendarA = parsedData[2].split("-");
                    String[] parsedCalendarB = parsedData[3].split("-");
                    if (parsedData.length == 5) {
                        getProjectByName(parsedData[4]).getTasks().add(new Design(Integer.parseInt(parsedData[1]), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                                new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0]))));
                    } else {
                        Design task = new Design(Integer.parseInt(parsedData[1]), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                                new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0])), getPersonbByName(parsedData[5]), Integer.parseInt(parsedData[6]));

                        getProjectByName(parsedData[4]).getTasks().add(task);
                        getPersonbByName(parsedData[5]).getTasks().add(task);
                    }


                }
                if (parsedData[0].equals("Documentation")) {
                    String[] parsedCalendarA = parsedData[2].split("-");
                    String[] parsedCalendarB = parsedData[3].split("-");
                    if (parsedData.length == 5) {
                        getProjectByName(parsedData[4]).getTasks().add(new Documentation(Integer.parseInt(parsedData[1]), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                                new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0]))));
                    } else {
                        getProjectByName(parsedData[4]).getTasks().add(new Documentation(Integer.parseInt(parsedData[1]), new GregorianCalendar(Integer.parseInt(parsedCalendarA[2]), Integer.parseInt(parsedCalendarA[1]), Integer.parseInt(parsedCalendarA[0])),
                                new GregorianCalendar(Integer.parseInt(parsedCalendarB[2]), Integer.parseInt(parsedCalendarB[1]), Integer.parseInt(parsedCalendarB[0])), getPersonbByName(parsedData[5]), Integer.parseInt(parsedData[6])));
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean saveProject(String pathName) {
        try {
            File f = new File(pathName + ".obj");
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
            os.writeObject(this);
            os.close();
        } catch (IOException e) {
            System.out.print("File Error Outbound");
            return false;
        }

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
        Calendar today = Calendar.getInstance();
        for (Task t : p.getTasks()) {
            if (t.status != 100) {
                return false;
            }
        }
        System.out.printf("%s %s %s %s\n", p.getName(), p.getEstimatedEnd().get(Calendar.YEAR), p.getEstimatedEnd().get(Calendar.MONTH), p.getEstimatedEnd().get(Calendar.DAY_OF_MONTH));
        return today.after(p.getEstimatedEnd());
    }

    /**
     * Retrieves the available Scholar to said {@code date}
     *
     * @return Array containing the available Scholars (That are not in a project)
     */
    public ArrayList<Scholar> listFreeScholar() {
        ArrayList<Scholar> arr = new ArrayList<>();
        for (Scholar s : this.scholars) {
            if (s.getProject() == null) {
                arr.add(s);
            }
        }
        return arr;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Scholar> getScholars() {
        return scholars;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public String getName() {
        return name;
    }


}
