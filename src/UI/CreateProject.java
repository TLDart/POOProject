package UI;
import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateProject extends JFrame {
    JComboBox mainT;
    private JFrame frame;
    private JPanel panelA, panelB;
    private JTextField name, nick, endDate, startDate;
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
        panel.setLayout(new GridLayout(4, 1));

        JLabel nameLabel = new JLabel("Name: ");
        name = new JTextField();
        name.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel nickLabel = new JLabel("Nick: ");
        nick = new JTextField(10);
        nick.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel startDateLabel = new JLabel("StartDate(dd-mm-yyyy)");
        startDate = new JTextField(10);
        startDate.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel endDateLabel = new JLabel("StartDate(dd-mm-yyyy)");
        endDate = new JTextField(10);
        endDate.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(4, 2));

        upperPanel.add(nameLabel);
        upperPanel.add(name);

        upperPanel.add(nickLabel);
        upperPanel.add(nick);
        upperPanel.add(startDateLabel);
        upperPanel.add(startDate);
        upperPanel.add(endDateLabel);
        upperPanel.add(endDate);

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

        panel.add(upperPanel);
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
        button2.setVisible(false);
        panel.setLayout(new GridLayout(1, 2));

        button.setText("Back");
        button.setActionCommand("back");
        button.addActionListener(new ButtonListener());

        button2.setText("Create");
        button2.setActionCommand("Create");
        button2.addActionListener(new ButtonListener());

        panel.add(button);
        panel.add(button2);
        return panel;

    }


    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("back")) {
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
            if (cmd.equals("create")) {

            }
        }
    }
}


