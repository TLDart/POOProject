package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


public class PhD extends Scholar implements Serializable {
    /**
     * Class Constructor with the name,email,id,tasks,start and ending of scholarship of a PhD scholar
     */
    public PhD(String name, String email, int id, ArrayList<Task> tasks, Calendar startDate, Calendar endDate) {
        super(name, email, id, tasks, startDate, endDate);
        this.salary = 1200;
    }
}
