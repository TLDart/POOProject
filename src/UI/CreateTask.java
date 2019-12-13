package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTask {
    private JFrame frame;
    JRadioButton buttonA, buttonB, buttonC;
    private JTextField id, startDateDay, startDateMonth, startDateYear, endDateDay, endDateMonth, endDateYear;
    private JPanel panelA, panelB;
    private JComboBox teachers, scholars, taskSelect;
    private Center center;
    private Project project;

    CreateTask(Center center, Project p) {
        this.center = center;
        this.project = p;
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
        JPanel panel = new JPanel(new GridLayout(10, 1));

        JLabel label = new JLabel("Create you task");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        taskSelect = new JComboBox<String>();

        taskSelect.addItem("Select you Task type");
        taskSelect.addItem("Documentation");
        taskSelect.addItem("Design");
        taskSelect.addItem("Development");


        JPanel panelA = new JPanel(new GridLayout(1, 2));
        JLabel labelA = new JLabel("ID");
        labelA.setHorizontalAlignment(SwingConstants.CENTER);
        id = new JTextField(10);
        panelA.add(labelA);
        panelA.add(id);

        JPanel panelB = new JPanel(new GridLayout(1, 2));
        JPanel innerpanelB = new JPanel(new GridLayout(1, 3));

        JLabel labelB = new JLabel("Start Day");
        labelB.setToolTipText("dd-mm-yyyy");
        startDateDay = new JTextField(2);
        startDateMonth = new JTextField(2);
        startDateYear = new JTextField(4);

        startDateDay.setHorizontalAlignment(SwingConstants.CENTER);
        startDateMonth.setHorizontalAlignment(SwingConstants.CENTER);
        startDateYear.setHorizontalAlignment(SwingConstants.CENTER);

        innerpanelB.add(startDateDay);
        innerpanelB.add(startDateMonth);
        innerpanelB.add(startDateYear);

        panelB.add(labelB);
        panelB.add(innerpanelB);

        JPanel panelC = new JPanel(new GridLayout(1, 2));
        JPanel innerpanelC = new JPanel(new GridLayout(1, 3));
        JLabel labelC = new JLabel("Estimated end Day");
        endDateDay = new JTextField(2);
        endDateMonth = new JTextField(2);
        endDateYear = new JTextField(4);

        endDateDay.setHorizontalAlignment(SwingConstants.CENTER);
        endDateMonth.setHorizontalAlignment(SwingConstants.CENTER);
        endDateYear.setHorizontalAlignment(SwingConstants.CENTER);


        innerpanelC.add(endDateDay);
        innerpanelC.add(endDateMonth);
        innerpanelC.add(endDateYear);

        panelC.add(labelC);
        panelC.add(innerpanelC);
        teachers = new JComboBox<Teacher>();
        for (Teacher t : project.getTeachers()) {
            teachers.addItem(t);
        }
        scholars = new JComboBox<Scholar>();
        for (Scholar s : project.getScholars()) {
            scholars.addItem(s);
        }

        scholars.setVisible(false);
        teachers.setVisible(false);

        buttonA = new JRadioButton("Select a Teacher");
        buttonA.setHorizontalAlignment(SwingConstants.CENTER);
        buttonA.addActionListener(new ButtonListener());
        buttonA.setActionCommand("A");
        buttonB = new JRadioButton("Select a Scholar");
        buttonB.setHorizontalAlignment(SwingConstants.CENTER);
        buttonB.addActionListener(new ButtonListener());
        buttonB.setActionCommand("B");

        buttonC = new JRadioButton("Start the task without Responsible");
        buttonC.setHorizontalAlignment(SwingConstants.CENTER);
        buttonC.addActionListener(new ButtonListener());
        buttonC.setActionCommand("C");
        buttonC.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(buttonA);
        group.add(buttonB);
        group.add(buttonC);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JPanel buttonPanel2 = new JPanel();
        buttonPanel.add(buttonA);

        buttonPanel.add(buttonB);
        buttonPanel2.add(buttonC);

        JLabel responsible = new JLabel("Select your responsible");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(label);
        panel.add(taskSelect);
        panel.add(panelA);
        panel.add(panelB);
        panel.add(panelC);
        panel.add(responsible);
        panel.add(buttonPanel);
        panel.add(buttonPanel2);
        panel.add(teachers);
        panel.add(scholars);

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
            if (cmd.equals("back")) {
                frame.setVisible(false);
                frame.dispose();
                new ProjectMainMenu(center, project);
            }
            if (cmd.equals("create")) {
                try {
                    Date startDate = new SimpleDateFormat(format).parse(String.format("%d-%d-%d", Integer.parseInt(startDateDay.getText()), Integer.parseInt(startDateMonth.getText()), Integer.parseInt(startDateYear.getText())));
                    Calendar calendarStart = new GregorianCalendar();
                    calendarStart.setTime(startDate);
                    Date endDate = new SimpleDateFormat(format).parse(String.format("%d-%d-%d", Integer.parseInt(endDateDay.getText()), Integer.parseInt(endDateMonth.getText()), Integer.parseInt(endDateYear.getText())));
                    Calendar calendarEnd = new GregorianCalendar();
                    calendarEnd.setTime(endDate);
                    int identifier = Integer.parseInt(id.getText());

                    if (calendarEnd.after(calendarStart) && calendarStart.after(new GregorianCalendar())) {//Verify ID is an int and start date does not overlap end date

                        switch ((String) taskSelect.getSelectedItem()) {//Select the type of task
                            case "Documentation":
                                if (buttonC.isSelected()) {
                                    project.getTasks().add(new Documentation(identifier, calendarStart, calendarEnd));
                                    JOptionPane.showMessageDialog(null, "Task created Succefully");
                                    frame.setVisible(false);
                                    frame.dispose();
                                    new ProjectMainMenu(center, project);
                                }
                                if (buttonA.isSelected()) {
                                    Teacher t = (Teacher) teachers.getSelectedItem();
                                    if (t != null) {
                                        if (center.getTeacherbyName(t.getName()).overloaded(calendarStart)) {
                                            Task nt = new Documentation(identifier, calendarStart, calendarEnd, center.getTeacherbyName(t.getName()), 0);
                                            project.getTasks().add(nt);
                                            center.getTeacherbyName(t.getName()).addTask(nt);
                                            JOptionPane.showMessageDialog(null, "Task created Succefully");
                                            frame.setVisible(false);
                                            frame.dispose();
                                            new ProjectMainMenu(center, project);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Teacher Overloaded for that Day");
                                        }
                                    }
                                }
                                if (buttonB.isSelected()) {
                                    Scholar s = (Scholar) scholars.getSelectedItem();
                                    if (s != null) {
                                        Task nt = new Documentation(identifier, calendarStart, calendarEnd, center.getScholarByName(s.getName()), 0);
                                        if (!center.getScholarByName(s.getName()).overloaded(calendarStart)) {
                                            //System.out.println(center.getScholarByName(s.getName()).getEndDate());
                                            if (center.getScholarByName(s.getName()).getEndDate().before(calendarEnd)) {
                                                JOptionPane.showMessageDialog(null, "Scholar leaves before task is Finished");
                                            } else {
                                                center.getProjectByName(project.getName()).getTasks().add(nt);
                                                center.getScholarByName(s.getName()).addTask(nt);
                                                JOptionPane.showMessageDialog(null, "Task created Succefully");
                                                frame.setVisible(false);
                                                frame.dispose();
                                                new ProjectMainMenu(center, project);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Scholar overloaded in That day");
                                        }

                                    }
                                }
                                break;
                            case "Development":
                                if (buttonC.isSelected()) {
                                    project.getTasks().add(new Development(identifier, calendarStart, calendarEnd));
                                    JOptionPane.showMessageDialog(null, "Task created Succefully");
                                    frame.setVisible(false);
                                    frame.dispose();
                                    new ProjectMainMenu(center, project);
                                }
                                if (buttonA.isSelected()) {
                                    Teacher t = (Teacher) teachers.getSelectedItem();
                                    if (t != null) {
                                        if (center.getTeacherbyName(t.getName()).overloaded(calendarStart)) {
                                            Task nt = new Development(identifier, calendarStart, calendarEnd, center.getTeacherbyName(t.getName()), 0);
                                            project.getTasks().add(nt);
                                            center.getTeacherbyName(t.getName()).addTask(nt);
                                            JOptionPane.showMessageDialog(null, "Task created Succefully");
                                            frame.setVisible(false);
                                            frame.dispose();
                                            new ProjectMainMenu(center, project);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Teacher Overloaded for that Day");
                                        }
                                    }
                                }
                                if (buttonB.isSelected()) {
                                    Scholar s = (Scholar) scholars.getSelectedItem();
                                    if (s != null) {
                                        Task nt = new Development(identifier, calendarStart, calendarEnd, center.getScholarByName(s.getName()), 0);
                                        if (!center.getScholarByName(s.getName()).overloaded(calendarStart)) {
                                            //System.out.println(center.getScholarByName(s.getName()).getEndDate());
                                            if (center.getScholarByName(s.getName()).getEndDate().before(calendarEnd)) {
                                                JOptionPane.showMessageDialog(null, "Scholar leaves before task is Finished");
                                            } else {
                                                center.getProjectByName(project.getName()).getTasks().add(nt);
                                                center.getScholarByName(s.getName()).addTask(nt);
                                                JOptionPane.showMessageDialog(null, "Task created Succefully");
                                                frame.setVisible(false);
                                                frame.dispose();
                                                new ProjectMainMenu(center, project);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Scholar overloaded in That day");
                                        }

                                    }
                                }
                                break;
                            case "Design":
                                if (buttonC.isSelected()) {
                                    project.getTasks().add(new Design(identifier, calendarStart, calendarEnd));
                                    JOptionPane.showMessageDialog(null, "Task created Succefully");
                                    frame.setVisible(false);
                                    frame.dispose();
                                    new ProjectMainMenu(center, project);
                                }
                                if (buttonA.isSelected()) {
                                    Teacher t = (Teacher) teachers.getSelectedItem();
                                    if (t != null) {
                                        if (center.getTeacherbyName(t.getName()).overloaded(calendarStart)) {
                                            Task nt = new Design(identifier, calendarStart, calendarEnd, center.getTeacherbyName(t.getName()), 0);
                                            project.getTasks().add(nt);
                                            center.getTeacherbyName(t.getName()).addTask(nt);
                                            JOptionPane.showMessageDialog(null, "Task created Succefully");
                                            frame.setVisible(false);
                                            frame.dispose();
                                            new ProjectMainMenu(center, project);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Teacher Overloaded for that Day");
                                        }
                                    }
                                }
                                if (buttonB.isSelected()) {
                                    Scholar s = (Scholar) scholars.getSelectedItem();
                                    if (s != null) {
                                        Task nt = new Design(identifier, calendarStart, calendarEnd, center.getScholarByName(s.getName()), 0);
                                        if (!center.getScholarByName(s.getName()).overloaded(calendarStart)) {
                                            //System.out.println(center.getScholarByName(s.getName()).getEndDate());
                                            if (center.getScholarByName(s.getName()).getEndDate().before(calendarEnd)) {
                                                JOptionPane.showMessageDialog(null, "Scholar leaves before task is Finished");
                                            } else {
                                                center.getProjectByName(project.getName()).getTasks().add(nt);
                                                center.getScholarByName(s.getName()).addTask(nt);
                                                JOptionPane.showMessageDialog(null, "Task created Succefully");
                                                frame.setVisible(false);
                                                frame.dispose();
                                                new ProjectMainMenu(center, project);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Scholar overloaded in that day");
                                        }

                                    }
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "You must select a task Type ", "Error", JOptionPane.WARNING_MESSAGE);
                                break;
                        }

                    } else {
                        if (calendarStart.after(calendarEnd))
                            JOptionPane.showMessageDialog(null, "End Date can't be before Start Date", "Error", JOptionPane.WARNING_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null, "You can create a Task prior to Today", "Error", JOptionPane.WARNING_MESSAGE);

                    }

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Incorrect Input", "Error", JOptionPane.WARNING_MESSAGE);
                } catch (NumberFormatException nbr) {
                    JOptionPane.showMessageDialog(null, "Incorrect Task ID", "Error", JOptionPane.WARNING_MESSAGE);
                }

            }
            if (buttonA.isSelected()) {
                scholars.setVisible(false);
                teachers.setVisible(true);
            }
            if (buttonB.isSelected()) {
                scholars.setVisible(true);
                teachers.setVisible(false);
            }
            if (buttonC.isSelected()) {
                scholars.setVisible(false);
                teachers.setVisible(false);
            }
        }
    }
}
