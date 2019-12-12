package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectListPeople extends JFrame {
    private JPanel panelA, panelB;
    private JFrame frame;
    private JButton search, back;
    private Center center;
    private Project project;
    private JList teachers, scholars;

    ProjectListPeople(Center center, Project project) {
        this.center = center;
        this.project = project;
        frame = new JFrame();
        frame.setTitle("People that Are part of the Project");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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
        JPanel mainTpanel = new JPanel(new GridLayout(1, 2));
        panel.setLayout(new GridLayout(5, 1));

        //String str = "<html>People Available at the Center <br/> Hit Search for more info</html>"; //Make a formatted string, same label but with a breakline

        JLabel mainT = new JLabel("Project Manager");
        mainT.setHorizontalAlignment(SwingConstants.CENTER);

        JButton mainTButton = new JButton(project.getMainTeacher().getName());
        mainTButton.setActionCommand("teacherInfo");
        mainTButton.addActionListener(new ButtonListener());

        mainTpanel.add(mainT);
        mainTpanel.add(mainTButton);


        JLabel teachersLabel = new JLabel("Teachers");
        teachersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<Teacher> listModel = new DefaultListModel<Teacher>();
        for (Teacher t : project.getTeachers()) {
            listModel.addElement(t);
        }

        teachers = new JList(listModel);
        teachers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) teachers.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane listScroller = new JScrollPane(teachers);
        teachers.setPreferredSize(new Dimension(250, 300));


        JLabel scholarsLabel = new JLabel("Scholars");
        scholarsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<Scholar> listModelB = new DefaultListModel<Scholar>();
        for (Scholar s : project.getScholars()) {
            listModelB.addElement(s);
        }

        scholars = new JList(listModelB);
        scholars.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer rendererB = (DefaultListCellRenderer) scholars.getCellRenderer();
        rendererB.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane listScrollerB = new JScrollPane(scholars);
        scholars.setPreferredSize(new Dimension(250, 300));


        panel.add(mainTpanel);
        panel.add(teachersLabel);
        panel.add(listScroller);
        panel.add(scholarsLabel);
        panel.add(listScrollerB);
        panel.setVisible(true);
        return panel;

    }

    public JPanel bottomFrame() {
        JPanel panel = new JPanel();
        search = new JButton();
        back = new JButton();

        panel.setLayout(new GridLayout(1, 2));
        back.setText("Back");
        back.setActionCommand("back");
        back.addActionListener(new ButtonListener());

        search.setText("Search");
        search.setActionCommand("search");
        search.addActionListener(new ButtonListener());

        panel.add(back);
        panel.add(search);
        return panel;

    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals("teacherInfo")) {
                new Infoframe(project.getMainTeacher());
            }

            if (cmd.equals("search")) {
                if (teachers.getSelectedValue() != null)
                    new Infoframe((Person) teachers.getSelectedValue());
                if (scholars.getSelectedValue() != null)
                    new Infoframe((Person) scholars.getSelectedValue());
            }
            if (cmd.equals(("back"))) {
                frame.setVisible(false);
                frame.dispose();
                new ProjectMainMenu(center, project);
            }
        }
    }
}