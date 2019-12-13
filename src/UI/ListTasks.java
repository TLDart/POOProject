package UI;

import Backend.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ListTasks extends JFrame {
    JSlider status;
    private JFrame frame;
    private JPanel panelA, panelB, comboBoxPanel, statusPanel;
    private Center center;
    private Project project;
    private JButton remove, back, buttonStatus, buttonSet, search;
    private JList tasksUnstarted, tasksUnfinished, tasksFinished;
    private JRadioButton buttonUnstarted, buttonUnfinished, buttonFinished, buttonScholar, buttonTeacher;
    private JComboBox teachers, scholars;
    private CardLayout cardLayout, cardLayoutJBox;
    private JLabel statusLabel;

    ListTasks(Center center, Project project) {
        this.center = center;
        this.project = project;
        frame = new JFrame();
        frame.setTitle("Tasks Associated with the Project");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        panelA = drawPanelLeft();
        panelB = drawPanelRight();

        panelA.setVisible(true);
        panelB.setVisible(true);
        frame.add(panelA, BorderLayout.WEST);
        frame.add(panelB, BorderLayout.EAST);

        frame.setVisible(true);

    }

    private JPanel drawPanelLeft() {
        JPanel panel = new JPanel(new GridLayout(8, 1));
        //panel.setLayout(new GridLayout(2, 1));
        JLabel title = new JLabel("Task List");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonUnstarted = new JRadioButton("Unstarted Tasks");
        buttonUnstarted.setActionCommand("A");
        buttonUnstarted.setHorizontalAlignment(SwingConstants.CENTER);
        buttonUnstarted.addActionListener(new ButtonListener());

        buttonUnfinished = new JRadioButton("Unfinished Tasks");
        buttonUnfinished.setActionCommand("B");
        buttonUnfinished.setHorizontalAlignment(SwingConstants.LEFT);
        buttonUnfinished.addActionListener(new ButtonListener());

        buttonFinished = new JRadioButton("Finished Tasks");
        buttonFinished.setActionCommand("C");
        buttonFinished.setHorizontalAlignment(SwingConstants.CENTER);
        buttonFinished.addActionListener(new ButtonListener());

        ButtonGroup group = new ButtonGroup();
        group.add(buttonUnstarted);
        group.add(buttonUnfinished);
        group.add(buttonFinished);

        buttonUnstarted.setSelected(true);
        buttonPanel.add(buttonUnstarted);
        buttonPanel.add(buttonUnfinished);
        buttonPanel.add(buttonFinished);

        //Options Pane
        JPanel removePanel = new JPanel(new GridLayout(1, 3));
        remove = new JButton("Remove");
        remove.setActionCommand("remove");
        remove.setHorizontalAlignment(SwingConstants.CENTER);
        remove.addActionListener(new ButtonListener());

        JPanel removePanelInside = new JPanel();
        JButton dummy1 = new JButton();
        JButton dummy2 = new JButton();
        dummy1.setVisible(false);
        dummy2.setVisible(false);
        removePanelInside.add(remove);
        removePanel.add(dummy1);
        removePanel.add(removePanelInside);
        removePanel.add(dummy2);

        JPanel options = new JPanel(new GridLayout(1, 3));

        buttonTeacher = new JRadioButton("Select a Teacher");
        buttonTeacher.setActionCommand("button teacher");
        buttonTeacher.setHorizontalAlignment(SwingConstants.CENTER);
        buttonTeacher.addActionListener(new ButtonListener());

        buttonScholar = new JRadioButton("Select a Scholar");
        buttonScholar.setActionCommand("button scholar");
        buttonScholar.setHorizontalAlignment(SwingConstants.CENTER);
        buttonScholar.addActionListener(new ButtonListener());

        buttonSet = new JButton("Set");
        buttonSet.setActionCommand("set");
        buttonSet.setHorizontalAlignment(SwingConstants.CENTER);
        buttonSet.addActionListener(new ButtonListener());

        JPanel buttonSetPanel = new JPanel();
        buttonSetPanel.add(buttonSet);

        ButtonGroup group2 = new ButtonGroup();

        buttonTeacher.setSelected(true);
        group2.add(buttonTeacher);
        group2.add(buttonScholar);

        options.add(buttonTeacher);
        options.add(buttonScholar);
        options.add(buttonSetPanel);

        cardLayoutJBox = new CardLayout();
        comboBoxPanel = new JPanel(cardLayoutJBox);
        teachers = new JComboBox<Teacher>();
        for (Teacher t : project.getTeachers()) {
            teachers.addItem(t);
        }
        scholars = new JComboBox<Scholar>();
        for (Scholar s : project.getScholars()) {
            scholars.addItem(s);
        }

        comboBoxPanel.add(teachers, "teachers");
        comboBoxPanel.add(scholars, "scholars");

        status = new JSlider(0, 100);
        status.addChangeListener(new Updater());

        statusPanel = new JPanel(new GridLayout(1, 2));
        JPanel statusPanelButton = new JPanel();

        statusLabel = new JLabel("50");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        buttonStatus = new JButton("Set Status");
        buttonStatus.setActionCommand("button status");
        buttonStatus.setHorizontalAlignment(SwingConstants.CENTER);
        buttonStatus.addActionListener(new ButtonListener());

        statusPanelButton.add(buttonStatus);
        statusPanel.add(statusLabel);
        statusPanel.add(statusPanelButton);

        JPanel backPanel = new JPanel(new GridLayout(1, 3));
        back = new JButton("Back");
        back.setActionCommand("back");
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.addActionListener(new ButtonListener());

        search = new JButton("Get Info");
        search.setActionCommand("get info");
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.addActionListener(new ButtonListener());


        JButton dummy3 = new JButton();
        dummy3.setVisible(false);

        JPanel backPanelInside = new JPanel();
        JPanel backPanelInside2 = new JPanel();

        backPanelInside.add(back);
        backPanelInside2.add(search);

        backPanel.add(backPanelInside);
        backPanel.add(dummy3);
        backPanel.add(backPanelInside2);

        panel.add(title);
        panel.add(buttonPanel);
        panel.add(removePanel);
        panel.add(options);
        panel.add(comboBoxPanel);
        panel.add(status);
        panel.add(statusPanel);
        panel.add(backPanel);
        return panel;
    }

    private JPanel drawPanelRight() {
        cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        //Unstarted Taskes
        DefaultListModel<Task> listModelUnstarted = new DefaultListModel<Task>();
        for (Task t : project.listNotStarted()) {
            listModelUnstarted.addElement(t);
        }
        tasksUnstarted = new JList(listModelUnstarted);
        tasksUnstarted.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) tasksUnstarted.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane listUnstarted = new JScrollPane(tasksUnstarted);

        //
        DefaultListModel<Task> listModelFinished = new DefaultListModel<Task>();
        for (Task t : project.listConcluded()) {
            listModelFinished.addElement(t);
        }
        tasksFinished = new JList(listModelFinished);
        tasksFinished.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer2 = (DefaultListCellRenderer) tasksFinished.getCellRenderer();
        renderer2.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane listFinished = new JScrollPane(tasksFinished);
        tasksFinished.setPreferredSize(new Dimension(250, 300));
        //
        DefaultListModel<Task> listModelUnfinished = new DefaultListModel<Task>();
        for (Task t : project.getTasks()) {
            if (t.getStatus() > 0 && t.getStatus() != 100)
                listModelUnfinished.addElement(t);
        }

        tasksUnfinished = new JList(listModelUnfinished);
        tasksUnfinished.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer3 = (DefaultListCellRenderer) tasksUnfinished.getCellRenderer();
        renderer3.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane listUnfinished = new JScrollPane(tasksUnfinished);
        tasksUnfinished.setPreferredSize(new Dimension(250, 300));


        listUnstarted.setMaximumSize(new Dimension(100, 100));
        listFinished.setMaximumSize(new Dimension(100, 100));
        listUnfinished.setMaximumSize(new Dimension(100, 100));


        cardPanel.add(listUnstarted, "unstarted");
        cardPanel.add(listFinished, "finished");
        cardPanel.add(listUnfinished, "unfinished");

        //panel.setLayout(new GridLayout(2, 1));

        return cardPanel;
    }


    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals(("back"))) {
                frame.setVisible(false);
                frame.dispose();
                new ProjectMainMenu(center, project);
            }
            if (cmd.equals(("get info"))) {
                Task t = null;
                if (buttonUnstarted.isSelected()) {
                    t = (Task) tasksUnstarted.getSelectedValue();
                } else if (buttonUnfinished.isSelected()) {
                    t = (Task) tasksUnfinished.getSelectedValue();
                } else if (buttonFinished.isSelected()) {
                    t = (Task) tasksFinished.getSelectedValue();
                }
                if (t != null)
                    new InframeTask(t);
            }
            if (cmd.equals(("button status"))) {
                Task t = null;
                if (buttonUnstarted.isSelected()) {
                    t = (Task) tasksUnstarted.getSelectedValue();
                } else if (buttonUnfinished.isSelected()) {
                    t = (Task) tasksUnfinished.getSelectedValue();
                } else if (buttonFinished.isSelected()) {
                    t = (Task) tasksFinished.getSelectedValue();
                }
                if (t != null) {
                    if (buttonFinished.isSelected()) {
                        JOptionPane.showMessageDialog(null, "You can't edit a finished task");
                    } else {
                        if (t.getStatus() != 100) {
                            t.setStatus(status.getValue());
                            frame.setVisible(false);
                            frame.dispose();
                            new ListTasks(center, project);
                            if (status.getValue() == 100) {
                                t.setEndTime(new GregorianCalendar());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You can't edit a finished task");
                        }
                    }

                }
            }
            if (cmd.equals("remove")) {
                Task t = null;
                if (buttonUnstarted.isSelected()) {
                    t = (Task) tasksUnstarted.getSelectedValue();
                } else if (buttonUnfinished.isSelected()) {
                    t = (Task) tasksUnfinished.getSelectedValue();
                } else if (buttonFinished.isSelected()) {
                    t = (Task) tasksFinished.getSelectedValue();
                }
                if (t != null) {
                    if (buttonFinished.isSelected() || t.getStatus() == 100) {
                        JOptionPane.showMessageDialog(null, "You can edit a finished task");
                    } else {
                        int confirmed = JOptionPane.showConfirmDialog(null,
                                "You are about to delete a task; are you sure?", "Task",
                                JOptionPane.YES_NO_OPTION);
                        if (confirmed == JOptionPane.YES_OPTION) {
                            if (t.getResponsible() == null) {
                                if (project.deleteTask(t)) {
                                    JOptionPane.showMessageDialog(null, "Task Deleted Sucessfully");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Something went Wrong");
                                }
                            } else {
                                center.getPersonbByName(t.getResponsible().getName()).remTask(t);
                                if (project.deleteTask(t)) {
                                    JOptionPane.showMessageDialog(null, "Task Deleted Sucessfully");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Something went Wrong");
                                }
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Task selected");
                }
            }
            if (cmd.equals(("set"))) {

                Task t = null;
                if (buttonUnstarted.isSelected()) {
                    t = (Task) tasksUnstarted.getSelectedValue();
                } else if (buttonUnfinished.isSelected()) {
                    t = (Task) tasksUnfinished.getSelectedValue();
                } else if (buttonFinished.isSelected()) {
                    t = (Task) tasksFinished.getSelectedValue();
                }
                if (t != null) {
                    if (t.getStatus() != 100) {
                        if (buttonFinished.isSelected()) {
                            JOptionPane.showMessageDialog(null, "You can't edit a finished task");
                        } else if (buttonUnstarted.isSelected()) {
                            t = (Task) tasksUnstarted.getSelectedValue();
                            if (buttonTeacher.isSelected()) {
                                Teacher th = (Teacher) teachers.getSelectedItem();
                                if (th != null) {
                                    if (th.overloaded(t.getStartDate())) {
                                        JOptionPane.showMessageDialog(null, "Current teacher is overloaded");
                                    } else {
                                        t.setResponsible(th);
                                        th.addTask(t);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No teacher Selected");
                                }
                            } else if (buttonScholar.isSelected()) {
                                Scholar sh = (Scholar) scholars.getSelectedItem();
                                if (sh != null) {
                                    if (sh.overloaded(t.getStartDate())) {
                                        JOptionPane.showMessageDialog(null, "Current Scholar is overloaded");
                                    } else {
                                        t.setResponsible(sh);
                                        sh.addTask(t);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No Scholar Selected");
                                }
                            }
                        } else if (buttonUnfinished.isSelected()) {
                            t = (Task) tasksUnfinished.getSelectedValue();
                            if (buttonTeacher.isSelected()) {
                                Teacher th = (Teacher) teachers.getSelectedItem();
                                if (th != null) {
                                    if (th.overloaded(t.getStartDate())) {
                                        JOptionPane.showMessageDialog(null, "Current teacher is overloaded");
                                    } else {
                                        t.getResponsible().remTask(t);
                                        t.setResponsible(th);
                                        th.addTask(t);
                                    }
                                }
                            } else if (buttonScholar.isSelected()) {
                                Scholar sh = (Scholar) scholars.getSelectedItem();
                                if (sh != null) {
                                    if (sh.overloaded(t.getStartDate())) {
                                        JOptionPane.showMessageDialog(null, "Current Scholar is overloaded");
                                    } else {
                                        t.getResponsible().remTask(t);
                                        t.setResponsible(sh);
                                        sh.addTask(t);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No Scholar Selected");
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You cannot edit a finished Task");
                    }
                }
            }

            if (buttonUnstarted.isSelected()) {
                cardLayout.show(panelB, "unstarted");
            }
            if (buttonUnfinished.isSelected()) {
                cardLayout.show(panelB, "unfinished");
            }
            if (buttonFinished.isSelected()) {
                cardLayout.show(panelB, "finished");
            }
            if (buttonTeacher.isSelected()) {
                cardLayoutJBox.show(comboBoxPanel, "teachers");
            }
            if (buttonScholar.isSelected()) {
                cardLayoutJBox.show(comboBoxPanel, "scholars");

            }
        }
    }

    private class Updater implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            statusLabel.setText(String.valueOf(status.getValue()));
        }
    }
}