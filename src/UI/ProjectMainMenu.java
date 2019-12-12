package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ProjectMainMenu extends JFrame {
    private JPanel panelA, panelB;
    private JFrame frame;
    private JButton createTask, listTask, getCost, finish, listPeople, save, back;
    private Center center;
    private Project project;
    private int saveSwitch = 0;

    ProjectMainMenu(Center center, Project project) {
        this.center = center;
        this.project = center.getProjectByName(project.getName());
        frame = new JFrame();
        frame.setTitle(project.getName());
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
        panel.setLayout(new GridLayout(5, 1));
        createTask = new JButton();
        listTask = new JButton();
        getCost = new JButton();
        finish = new JButton();
        listPeople = new JButton();

        createTask.setText("Create a task");
        listTask.setText("List Task");
        getCost.setText("Get Cost");
        finish.setText("Finish");
        listPeople.setText("List people");

        createTask.setActionCommand("Create a task");
        listTask.setActionCommand("List Task");
        getCost.setActionCommand("Get Cost");
        finish.setActionCommand("Finish");
        listPeople.setActionCommand("list people");

        createTask.addActionListener(new ButtonListener());
        listTask.addActionListener(new ButtonListener());
        getCost.addActionListener(new ButtonListener());
        finish.addActionListener(new ButtonListener());
        listPeople.addActionListener(new ButtonListener());

        panel.add(createTask);
        panel.add(listTask);
        panel.add(getCost);
        panel.add(finish);
        panel.add(listPeople);
        panel.setVisible(true);
        return panel;

    }

    public JPanel bottomFrame() {
        JPanel panel = new JPanel();
        save = new JButton();
        back = new JButton();

        panel.setLayout(new GridLayout(1, 2));
        back.setText("Back");
        back.setActionCommand("back");
        back.addActionListener(new ButtonListener());

        save.setText("Save");
        save.setActionCommand("save");
        save.addActionListener(new ButtonListener());

        panel.add(back);
        panel.add(save);
        return panel;

    }

    private void saveToCache(String pathToSave) {
        File f = new File("./configs/cache.txt");
        ArrayList<String> names = new ArrayList<>();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((s = br.readLine()) != null) {
                names.add(s);
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, false));
            bw.write(pathToSave + ".obj" + "\n");
            for (int i = 0; i < 2 && i < names.size() && !names.isEmpty(); i++) {
                if (!names.get(i).equals(pathToSave + ".obj"))
                    bw.write(names.get(i) + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "back":
                    if (saveSwitch != 1) {
                        int confirmed = JOptionPane.showConfirmDialog(null,
                                "You have unsaved! Changes Do You want to go back to the Main Menu?", "Save",
                                JOptionPane.YES_NO_OPTION);
                        if (confirmed == JOptionPane.YES_OPTION) {
                            frame.setVisible(false);
                            frame.dispose();
                            new MainMenu(center);
                        }
                    } else {
                        frame.setVisible(false);
                        frame.dispose();
                        new LoadMain();
                    }
                    break;
                case "save":
                    if (center.saveProject("./saves/" + center.getName())) {
                        JOptionPane.showMessageDialog(null,
                                "File Saved Successfully");
                        saveToCache(center.getName());
                        saveSwitch = 1;
                    }
                    break;
                case "Create a task":
                    frame.setVisible(false);
                    frame.dispose();
                    //new CreateProject(center);
                    break;
                case "List Task":
                    frame.setVisible(false);
                    frame.dispose();
                    new ListTasks(center, project);
                    break;
                case "Get Cost":
                    JOptionPane.showMessageDialog(null, project.getTotalPrice(), "Cost of the project", JOptionPane.WARNING_MESSAGE);
                    break;
                case "list people":
                    frame.setVisible(false);
                    frame.dispose();
                    new ProjectListPeople(center, project);
                    break;
                case "Finish":
                    project.setFinished(true);
                    project.setEndDate(new GregorianCalendar());
                    new ListNotConcluded(center);
                    break;
            }
        }
    }

}
