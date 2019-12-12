package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class CreateProject extends JFrame {
    JComboBox mainT;
    private JFrame frame;
    private JPanel panelA, panelB;
    private JTextField name, nick, startDateDay, startDateMonth, startDateYear, endDateDay, endDateMonth, endDateYear;
    private Center center;
    private JList teachersList, peopleList;

    CreateProject(Center center) {
        this.center = center;
        frame = new JFrame();
        frame.setTitle("Add someone to a project");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.GRAY);

        panelA = drawPanel();
        panelB = bottomFrame();

        panelA.setVisible(true);
        panelB.setVisible(true);
        frame.add(panelA, BorderLayout.CENTER);
        frame.add(panelB, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel drawPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        JLabel title = new JLabel("Create a New Project");
        title.setFont(new Font("Monaco", Font.BOLD, 17));
        title.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel nameLabel = new JLabel("Name: ");
        name = new JTextField();
        name.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel nickLabel = new JLabel("Nick: ");
        nick = new JTextField(10);
        nick.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel startDatePanel = new JPanel(new GridLayout(1, 4));
        JLabel startDateLabel = new JLabel("StartDate");
        startDateLabel.setToolTipText("dd-mm-yyyy");
        startDateDay = new JTextField(22);
        startDateDay.setHorizontalAlignment(SwingConstants.CENTER);
        startDateMonth = new JTextField(22);
        startDateMonth.setHorizontalAlignment(SwingConstants.CENTER);
        startDateYear = new JTextField(22);
        startDateYear.setHorizontalAlignment(SwingConstants.CENTER);

        startDatePanel.add(startDateLabel);
        startDatePanel.add(startDateDay);
        startDatePanel.add(startDateMonth);
        startDatePanel.add(startDateYear);

        JPanel endDatePanel = new JPanel(new GridLayout(1, 4));
        JLabel endDateLabel = new JLabel("EndDate");
        endDateLabel.setToolTipText("dd-mm-yyyy");
        endDateDay = new JTextField(2);
        endDateDay.setHorizontalAlignment(SwingConstants.CENTER);
        endDateMonth = new JTextField(2);
        endDateMonth.setHorizontalAlignment(SwingConstants.CENTER);
        endDateYear = new JTextField(4);
        endDateYear.setHorizontalAlignment(SwingConstants.CENTER);

        endDatePanel.add(endDateLabel);
        endDatePanel.add(endDateDay);
        endDatePanel.add(endDateMonth);
        endDatePanel.add(endDateYear);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 2));

        upperPanel.add(nameLabel);
        upperPanel.add(name);

        upperPanel.add(nickLabel);
        upperPanel.add(nick);


        mainT = new JComboBox();

        mainT = new JComboBox<Project>();
        mainT.addItem("Choose a main Teacher");
        for (Teacher t : center.getTeachers()) {
            mainT.addItem(t);
        }

        DefaultListModel<Teacher> teacherListModel = new DefaultListModel<Teacher>();
        for (Teacher t : center.getTeachers())
            teacherListModel.addElement(t);
        teachersList = new JList(teacherListModel);
        JScrollPane teacherScroller = new JScrollPane(teachersList);


        DefaultListModel<Scholar> peopleListModel = new DefaultListModel<>();
        for (Scholar s : center.listFreeScholar())
            peopleListModel.addElement(s);
        peopleList = new JList(peopleListModel);

        JScrollPane scholarScroller = new JScrollPane(peopleList);

        panel.add(title);
        panel.add(upperPanel);
        panel.add(startDatePanel);
        panel.add(endDatePanel);
        panel.add(mainT);
        panel.add(teacherScroller);
        panel.add(scholarScroller);

        panel.setVisible(true);
        return panel;
    }

    public JPanel bottomFrame() {
        JPanel panel = new JPanel();
        JButton button = new JButton();
        JButton button2 = new JButton();
        panel.setLayout(new GridLayout(1, 2));

        button.setText("Back");
        button.setActionCommand("back");
        button.addActionListener(new ButtonListener());

        button2.setText("Create");
        button2.setActionCommand("create");
        button2.addActionListener(new ButtonListener());

        panel.add(button);
        panel.add(button2);
        return panel;
    }


    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String format = "dd-MM-yyyy";
            Boolean valid = true;
            if (cmd.equals("back")) {
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
            if (cmd.equals("create")) {
                try {
                    Date startDate = new SimpleDateFormat(format).parse(String.format("%d-%d-%d", Integer.parseInt(startDateDay.getText()), Integer.parseInt(startDateMonth.getText()), Integer.parseInt(startDateYear.getText())));
                    Date endDate = new SimpleDateFormat(format).parse(String.format("%d-%d-%d", Integer.parseInt(endDateDay.getText()), Integer.parseInt(endDateMonth.getText()), Integer.parseInt(endDateYear.getText())));
                    Calendar calendarStart = new GregorianCalendar();
                    calendarStart.setTime(startDate);
                    Calendar calendarEnd = new GregorianCalendar();
                    calendarEnd.setTime(endDate);
                    ArrayList<Teacher> teacherArrayList = new ArrayList<>();
                    ArrayList<Scholar> scholarArrayList = new ArrayList<>();
                    if (calendarStart.before(new GregorianCalendar())) {
                        JOptionPane.showMessageDialog(null,
                                "Start date Invalid", "Error",
                                JOptionPane.WARNING_MESSAGE);
                        valid = false;
                    }
                    Teacher main = (Teacher) mainT.getSelectedItem();

                    for (Object o : teachersList.getSelectedValuesList()) {
                        Teacher t = (Teacher) o;
                        if (!teacherArrayList.contains(t))
                            teacherArrayList.add(t);
                    }
                    for (Object o : peopleList.getSelectedValuesList()) {
                        Scholar s = (Scholar) o;
                        if (!scholarArrayList.contains(s) && s.getEndDate().before(startDate))
                            scholarArrayList.add(s);
                        else {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid Scholar", "Error",
                                    JOptionPane.WARNING_MESSAGE);
                            valid = false;
                        }
                    }

                    if (!name.getText().equals("") && !nick.getText().equals("") && main != null && valid) {
                        Project p = new Project(name.getText(), nick.getText(), calendarStart, calendarEnd, main, teacherArrayList, scholarArrayList, new ArrayList<Task>());
                        center.getProjects().add(p);
                    }
                    JOptionPane.showConfirmDialog(null,
                            "Project Created Succesfully", "Success",
                            JOptionPane.WARNING_MESSAGE);
                } catch (ParseException | NumberFormatException f) {
                    JOptionPane.showMessageDialog(null,
                            "InvalidFormat", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } catch (ClassCastException k) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Main Teacher", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}


