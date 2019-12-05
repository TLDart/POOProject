package Project;

import java.util.ArrayList;

public class Teacher extends Person {
    private String field;
    private int number;

    public Teacher(String name, String email, int id, ArrayList<Task> tasks, String field, int number) {
        super(name, email, id, tasks);
        this.field = field;
        this.number = number;
    }

    public String getField() {
        return field;
    }

    public int getNumber() {
        return number;
    }
}
