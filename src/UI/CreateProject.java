package UI;
import Project.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateProject extends JFrame {
    private JTextField nameTextField,nickTextField,endDateTextField;
    CreateProject(Center center) {
        JFrame frame = new JFrame();
        frame.setTitle("Creating Project");
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Upper Panel

        JLabel nameLabel = new JLabel("Name");
        JTextField nameTextField = new JTextField();
        JButton nameButton = new JButton("Add");
        nameButton.addActionListener(new ButtonListener());
        nameButton.setActionCommand("add1");

        JLabel nickLabel = new JLabel("Nick");
        JTextField nickTextField = new JTextField();
        JButton nickButton = new JButton("Add");
        nickButton.addActionListener(new ButtonListener());
        nickButton.setActionCommand("add2");

        JLabel endDateLabel=new JLabel("Data Final(Dia/Mês/Ano)");
        JTextField endDateTextField= new JTextField();
        JButton endDateButton=new JButton("Add");
        endDateButton.addActionListener(new ButtonListener());
        endDateButton.setActionCommand("add3");


        JPanel upperPanel= new JPanel();
        upperPanel.setLayout(new GridLayout(3,3));
        upperPanel.add(nameLabel);
        upperPanel.add(nameTextField);
        upperPanel.add(nameButton);
        upperPanel.add(nickLabel);
        upperPanel.add(nickTextField);
        upperPanel.add(nickButton);
        upperPanel.add(endDateLabel);
        upperPanel.add(endDateTextField);
        upperPanel.add(endDateButton);


        // Middle Panel

        /*DefaultListModel<Teacher> teacherListModel=new DefaultListModel<Teacher>();
        for (Teacher t : center.getTeachers())
            teacherListModel.addElement(t);
        JList teacherslist=new JList(teacherListModel);
        teacherslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane teacherScroller= new JScrollPane(teacherslist);

        JButton addMainTeacherButton = new JButton("Add Main Teacher");
        addMainTeacherButton.addActionListener(new ButtonListener());
        addMainTeacherButton.setActionCommand("add3"); */

        DefaultListModel<Person> peopleListModel = new DefaultListModel<Person>();
        for (Teacher t : center.getTeachers())
            peopleListModel.addElement(t);
        for (Scholar s : center.getScholars())
            peopleListModel.addElement(s);
        JList peopleList = new JList(peopleListModel);
        JScrollPane peopleScroller = new JScrollPane(peopleList);

        JButton addPeopleButton = new JButton("Add People");
        addPeopleButton.addActionListener(new ButtonListener());
        addPeopleButton.setActionCommand("add4");

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, 1));
        //middlePanel.add(teacherScroller);
        middlePanel.add(peopleScroller);

        //Bottom Panel

        JButton back = new JButton("Back");
        back.addActionListener(new ButtonListener());
        back.setActionCommand("back");
        JButton create = new JButton("Create");
        create.addActionListener(new ButtonListener());
        create.setActionCommand("create");

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2));
        bottomPanel.add(back);
        bottomPanel.add(create);

        frame.add(upperPanel,BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);














    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            Center center = new Center();
            String name,nick;
            Calendar start=new GregorianCalendar();
            Calendar end = new GregorianCalendar();
            if(cmd.equals("add1")) {
                name = nameTextField.getText();
            }
            if(cmd.equals("add2")) {
                nick = nickTextField.getText();
            }
            if(cmd.equals("add3")){
                String st= endDateTextField.getText();
                String[] result=st.split("\\s");
                int day=Integer.parseInt(result[0]);
                int month=Integer.parseInt(result[1]);
                int year=Integer.parseInt(result[2]);
                end.set(day, month, year);
            }
            if(cmd.equals("add4"));

            if(cmd.equals("back"));
            if(cmd.equals("create"));
                center.createProject(name,nick,start.getTime(),end.getTime(),);
        }
    }
}


