package Backend;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Center implements Serializable {
    private String name;
    private ArrayList<Teacher> teachers;
    private ArrayList<Scholar> scholars;
    private ArrayList<Project> projects;

    /**
     * Class Constructor specifying the name of the Center
     * and containing all the teachers,scholars and projects in the Project
     */
    public Center(String name, ArrayList<Teacher> teachers, ArrayList<Scholar> scholars, ArrayList<Project> projects) {
        this.name = name;
        this.teachers = teachers;
        this.scholars = scholars;
        this.projects = projects;
    }

    /**
     * Class Constructor containing all the teachers,scholars and projects in the Project
     */
    public Center(ArrayList<Teacher> teachers, ArrayList<Scholar> scholars, ArrayList<Project> projects) {
        this.teachers = teachers;
        this.scholars = scholars;
        this.projects = projects;
    }

    /**
     * Class Constructor specifying the name of the Center, automatically the scholar,teacher and projects array to null
     */
    public Center(String name) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.scholars = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    /**
     * Loads the initial configuration from a config file,setting the teachers, scholars, tasks and projects
     * <p>
     * Takes the configuration file and parses its data ensuring that the data is correct and with that, loads the correct components into the project
     * The correct order for the parser to work correctly is, teachers, scholars (of all kinds), projects and finally task. A different file order my cause error or for the project to fail loading
     * </p>
     *
     * @param path Specifies the file path
     * @return True if the file is successfully loaded, False if there is an error while loading
     */
    public Boolean simpleBootloader(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String s;
            String[] parsedData;
            String format = "dd-MM-yyyy";
            while ((s = br.readLine()) != null) {
                parsedData = s.split(",");
                if (parsedData[0].equals("Teacher")) {//Loading Teacher
                    this.teachers.add(new Teacher(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), parsedData[4], Integer.parseInt(parsedData[5])));
                }
                if (parsedData[0].equals("Bachelor")) {//Loading Bachelor
                    calendarStartEnd dates = new calendarStartEnd(parsedData[4], parsedData[5]);

                    ArrayList<Teacher> coordinators = new ArrayList<Teacher>();
                    for (int i = 6; i < parsedData.length; i++) {
                        coordinators.add(getTeacherbyName(parsedData[i]));
                    }

                    this.scholars.add(new Bachelor(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), dates.getCalendarStart(), dates.getCalendarEnd(), coordinators));
                }
                if (parsedData[0].equals("Graduate")) {//Loading Graduate
                    calendarStartEnd dates = new calendarStartEnd(parsedData[4], parsedData[5]);
                    ArrayList<Teacher> coordinators = new ArrayList<>();
                    for (int i = 6; i < parsedData.length; i++) {
                        coordinators.add(getTeacherbyName(parsedData[i]));
                    }

                    this.scholars.add(new Graduate(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), dates.getCalendarStart(), dates.getCalendarEnd(), coordinators));
                }
                if (parsedData[0].equals("PhD")) {//Loading PhD
                    calendarStartEnd dates = new calendarStartEnd(parsedData[4], parsedData[5]);
                    this.scholars.add(new PhD(parsedData[1], parsedData[2], Integer.parseInt(parsedData[3]), new ArrayList<>(), dates.getCalendarStart(), dates.getCalendarEnd()));

                }
                if (parsedData[0].equals("Project")) {//Loading Project
                    Date startDate = new SimpleDateFormat(format).parse(parsedData[3]);
                    Calendar calendarStart = new GregorianCalendar();
                    calendarStart.setTime(startDate);

                    ArrayList<Teacher> teachers = new ArrayList<>();
                    ArrayList<Task> tasks = new ArrayList<>();
                    ArrayList<Scholar> scholars = new ArrayList<>();

                    for (int k = 7; k < parsedData.length; k++) {//Load both scholars and teachers into arrays
                        if (parsedData[k].startsWith("/")) {
                            teachers.add(getTeacherbyName(parsedData[k].replace("/", "")));
                        }
                        if (parsedData[k].startsWith("_")) {
                            scholars.add(getScholarByName(parsedData[k].replace("_", "")));
                        }
                    }
                    if (parsedData[5].equals("null")) {// If there is no endDate start a project with a different constructor
                        Project p = new Project(parsedData[1], parsedData[2], calendarStart, Integer.parseInt(parsedData[4]), getTeacherbyName(parsedData[6]), teachers, scholars, tasks);
                        this.projects.add(p);
                        for (Scholar sc : scholars) {
                            sc.setProject(p);
                        }
                    } else {// If there is an end date
                        Date FinishDate = new SimpleDateFormat(format).parse(parsedData[3]);
                        Calendar calendarFinish = new GregorianCalendar();
                        calendarFinish.setTime(FinishDate);

                        System.out.println(parsedData[6]);
                        System.out.println(getTeacherbyName(parsedData[6]));

                        Project p = new Project(parsedData[1], parsedData[2], calendarStart, Integer.parseInt(parsedData[4]), calendarFinish, getTeacherbyName(parsedData[6]), teachers, scholars, tasks);
                        this.projects.add(p);
                        for (Scholar sc : scholars) {
                            sc.setProject(p);
                        }
                    }
                }
                if (parsedData[0].equals("Development") || parsedData[0].equals("Design") || parsedData[0].equals("Documentation")) {//Loading Tasks
                    calendarStartEnd dates = new calendarStartEnd(parsedData[2], parsedData[3]);
                    if (!parsedData[4].equals("null")) {//If there is no end Date
                        Date FinishDate = new SimpleDateFormat(format).parse(parsedData[3]);
                        Calendar calendarFinish = new GregorianCalendar();
                        calendarFinish.setTime(FinishDate);
                        switch (parsedData[0]) {
                            case "Development":
                                Development task = new Development(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd(), calendarFinish, getPersonbByName(parsedData[7]), 100);
                                getProjectByName(parsedData[5]).getTasks().add(task);
                                getPersonbByName(parsedData[7]).addTask(task);
                                break;
                            case "Design":
                                Design task2 = new Design(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd(), calendarFinish, getPersonbByName(parsedData[7]), 100);
                                getProjectByName(parsedData[5]).getTasks().add(task2);
                                getPersonbByName(parsedData[7]).addTask(task2);
                                break;
                            case "Documentation":
                                Documentation task3 = new Documentation(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd(), calendarFinish, getPersonbByName(parsedData[7]), 100);
                                getProjectByName(parsedData[5]).getTasks().add(task3);
                                getPersonbByName(parsedData[7]).addTask(task3);
                                break;
                        }
                    } else {
                        if (parsedData.length == 7) {//Tasks that do not have a responsible
                            switch (parsedData[0]) {
                                case "Development":
                                    getProjectByName(parsedData[5]).getTasks().add(new Development(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd()));
                                    break;
                                case "Design":
                                    getProjectByName(parsedData[5]).getTasks().add(new Design(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd()));
                                    break;
                                case "Documentation":
                                    getProjectByName(parsedData[5]).getTasks().add(new Documentation(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd()));
                                    break;
                            }
                        } else {
                            switch (parsedData[0]) {//Tasks that a responsible but to do not have a finished time
                                case "Development":
                                    Development task = new Development(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd(), getPersonbByName(parsedData[7]), Integer.parseInt(parsedData[6]));
                                    getProjectByName(parsedData[5]).getTasks().add(task);
                                    getPersonbByName(parsedData[7]).addTask(task);
                                    break;
                                case "Design":
                                    Design task2 = new Design(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd(), getPersonbByName(parsedData[7]), Integer.parseInt(parsedData[6]));
                                    getProjectByName(parsedData[5]).getTasks().add(task2);
                                    getPersonbByName(parsedData[7]).addTask(task2);
                                    break;
                                case "Documentation":
                                    Documentation task3 = new Documentation(Integer.parseInt(parsedData[1]), dates.getCalendarStart(), dates.getCalendarEnd(), getPersonbByName(parsedData[7]), Integer.parseInt(parsedData[6]));
                                    getProjectByName(parsedData[5]).getTasks().add(task3);
                                    getPersonbByName(parsedData[7]).addTask(task3);
                                    break;
                            }
                        }
                    }
                }
            }
            br.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Saves the project data into the specified path
     *
     * @param pathName Specifies the file path
     * @return Returns true if the file was successfully saved or false if the if the file fails to be saved
     */
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

    /**
     * Adds a teacher to a certain project
     *
     * @param p Project to add the teacher to
     * @param t Teacher to be added
     * @see Project,Teacher
     */
    public void addTeacherToProject(Project p, Teacher t) {
        p.getTeachers().add(t);
    }

    /**
     * Adds a scholar to a certain project
     *
     * @param p Project to add the scholar to
     * @param s Scholar to be added
     * @see Project,Scholar
     */
    public void addScholarToProject(Project p, Scholar s) {
        p.getScholars().add(s);
    }

    /**
     * Verifies if a person is in the current project
     *
     * @param p  Person to Verify
     * @param pr Pro
     * @return Returns true if the person is in the project,otherwise,returns false
     * @see Person,Scholar,Teacher,Project
     */
    public boolean VerifyIfInProject(Person p, Project pr) {
        if (pr.getMainTeacher().getId() == p.getId()) {
            return true;
        }
        for (Scholar s : pr.getScholars()) {
            if (s.getId() == p.getId()) {
                return true;
            }
        }
        for (Teacher t : pr.getTeachers()) {
            if (t.getId() == p.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a Person in the project
     *
     * @param s Name of the Person
     * @return Return either a Scholar or Teacher Object, if it is found, or null if it is not
     * @see Person,Scholar,Teacher
     */
    public Person getPersonbByName(String s) {
        for (Scholar sc : this.scholars) {
            if (sc.getName().equals(s)) {
                return sc;
            }
        }
        for (Teacher t : this.teachers) {
            if (t.getName().equals(s)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Searches for a specific teacher, by its name, in the array of teachers in the center
     *
     * @param name Name of the Teacher
     * @return The an Object-Type Teacher if true, null if false
     * @see Teacher
     */
    public Teacher getTeacherbyName(String name) {
        for (Teacher t : this.teachers) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Searches for a specific Project, by its name, in the array of projects in the center
     *
     * @param name Name of the project
     * @return Returns the requested project
     * @see Project
     */
    public Project getProjectByName(String name) {
        for (Project p : this.projects) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Searches for a specific Scholar, by its name, in the array of projects in the center
     *
     * @param name Name of the Scholar
     * @return Returns a Scholar-type Object if found, null if not found
     * @see Scholar
     */
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
            if (isFinished(p)) {
                projects.add(p);
                p.setFinished(true);
            }

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

    public Boolean isFinished(Project p) {
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

    /**
     * @return Array with all the teachers in the center
     */
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * @return Array with all the scholars in the center
     */
    public ArrayList<Scholar> getScholars() {
        return scholars;
    }

    /**
     * @return Array with all the projects in the center
     */
    public ArrayList<Project> getProjects() {
        return projects;
    }

    /**
     * @return Name of the center
     */
    public String getName() {
        return name;
    }

    /**
     * Private Class criated used to simplify and reduce the code load on the bootloader
     */
    private class calendarStartEnd {
        String format = "dd-MM-yyyy";
        Calendar calendarStart, calendarEnd;

        /**
         * @param A String containing the date in a specified format
         * @param B String containing the date in a specified format
         * @throws ParseException
         */
        calendarStartEnd(String A, String B) throws ParseException {
            Date startDate = new SimpleDateFormat(format).parse(A);
            Date endDate = new SimpleDateFormat(format).parse(B);
            this.calendarStart = new GregorianCalendar();
            this.calendarEnd = new GregorianCalendar();
            this.calendarStart.setTime(startDate);
            this.calendarEnd.setTime(endDate);

        }

        /**
         * @return Calendar type object, the result of a successful string A parsing
         */
        public Calendar getCalendarStart() {
            return calendarStart;
        }

        /**
         * @return Calendar type object, the result of a successful string B parsing
         */
        public Calendar getCalendarEnd() {
            return calendarEnd;
        }
    }

}
