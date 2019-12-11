package Backend;

import UI.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) throws ParseException {

        //String format = "dd-MM-yyyy";
        //String test = "15-1-2012";
        //Date date = new SimpleDateFormat(format).parse(test);
        //Calendar myCal = new GregorianCalendar();
        //myCal.setTime(date);
        //System.out.println(date.getMonth());
        //myCal.set(Calendar.MONTH, date.getMonth());
        //System.out.println(myCal.get(Calendar.DAY_OF_MONTH));
        //System.out.println(myCal.get(Calendar.MONTH));
        //System.out.println(myCal.get(Calendar.YEAR));

        new LoadMain();
        //System.out.print(new Center("name").simpleBootloader("config.txt"));
    }
}
