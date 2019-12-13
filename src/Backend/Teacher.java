package Backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends Person implements Serializable {
    private String field;
    private int number;

    /**
     * Class Constructor that specifies the name,email,id,tasks to do,field and number of the teacher
     */
    public Teacher(String name, String email, int id, ArrayList<Task> tasks, String field, int number) {
        super(name, email, id, tasks);
        this.field = field;
        this.number = number;
    }
}
