package UI;

import Backend.*;
import com.sun.source.util.TreeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPeople {
    private JFrame frame;
    private JPanel panelA, panelB;
    private Button GetInfo;
    private Center center;
    private JList teachers, scholars;
    private JComboBox project;

    public AddPeople(Center center) {
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

    JPanel drawPanel() {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(300, 400));
        panel.setLayout(new GridLayout(6, 1));

        JLabel title = new JLabel("Add Someone to your project");
        title.setFont(new Font("Monaco", Font.BOLD, 17));
        title.setHorizontalAlignment(SwingConstants.CENTER);


        project = new JComboBox<Project>();
        for (Project tester : center.listNotFinished()) {
            project.addItem(tester);
        }

        JLabel labelA = new JLabel("Select Teachers");
        labelA.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<Teacher> listModel = new DefaultListModel<>();
        for (Teacher t : center.getTeachers()) {
            listModel.addElement(t);
        }

        teachers = new JList<>(listModel);
        teachers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) teachers.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(teachers);

        DefaultListModel<Scholar> listModelB = new DefaultListModel<>();
        for (Scholar s : center.listFreeScholar()) {
            listModelB.addElement(s);
        }

        JLabel labelB = new JLabel("Select Scholars");
        labelB.setHorizontalAlignment(SwingConstants.CENTER);
        scholars = new JList<>(listModelB);
        scholars.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer2 = (DefaultListCellRenderer) teachers.getCellRenderer();
        renderer2.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane scrollPane2 = new JScrollPane(scholars);


        panel.add(title);
        panel.add(project);
        panel.add(labelA);
        panel.add(scrollPane);
        panel.add(labelB);
        panel.add(scrollPane2);

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

        button2.setText("Add");
        button2.setActionCommand("add");
        button2.addActionListener(new ButtonListener());
        button2.setVisible(true);

        panel.add(button);
        panel.add(button2);
        return panel;

    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("add")) {
                try {
                    Project p = (Project) project.getSelectedItem();
                    if (p != null) {
                        for (Object o : teachers.getSelectedValuesList()) {
                            Teacher t = (Teacher) o;
                            if (!p.getTeachers().contains(t)) {
                                center.addTeacherToProject(p, t);
                                p.getTeachers().add(t);
                            }
                        }
                        for (Object o : scholars.getSelectedValuesList()) {
                            Scholar s = (Scholar) o;
                            if (p.getEndDate().after(s.getStartDate()) && s.getProject() == null) {
                                center.addScholarToProject(p, s);
                                p.getScholars().add(s);
                                s.setProject(p);
                            }
                        }
                        JOptionPane.showConfirmDialog(null,
                                "Added Sucessfully", "Sucess",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (ClassCastException j) {
                    JOptionPane.showConfirmDialog(null,
                            "Must select a project", "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                }

            }
            if (cmd.equals(("back"))) {
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
        }
    }


}
