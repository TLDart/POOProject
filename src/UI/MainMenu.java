package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MainMenu extends JFrame {
    private JPanel panelA, panelB;
    private JFrame frame;
    private JButton createProject, addPersonel, listConcluded, listNotConcluded, listPeople, save, back;
    private Center center;
    private int saveSwitch = 0;

    MainMenu(Center center) {
        this.center = center;
        frame = new JFrame();
        frame.setTitle(center.getName());
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
        createProject = new JButton();
        addPersonel = new JButton();
        listConcluded = new JButton();
        listNotConcluded = new JButton();
        listPeople = new JButton();

        createProject.setText("Create a new Project");
        addPersonel.setText("Add People");
        listConcluded.setText("List Concluded Projects");
        listNotConcluded.setText("List Not Concluded Projects");
        listPeople.setText("List People");

        createProject.setActionCommand("Create a new Project");
        addPersonel.setActionCommand("Add People");
        listConcluded.setActionCommand("List Concluded Projects");
        listNotConcluded.setActionCommand("List Not Concluded Projects");
        listPeople.setActionCommand("List People");

        createProject.addActionListener(new ButtonListener());
        addPersonel.addActionListener(new ButtonListener());
        listConcluded.addActionListener(new ButtonListener());
        listNotConcluded.addActionListener(new ButtonListener());
        listPeople.addActionListener(new ButtonListener());

        panel.add(createProject);
        panel.add(addPersonel);
        panel.add(listConcluded);
        panel.add(listNotConcluded);
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
                                "You have unsaved Changes Do You want to go back", "Save",
                                JOptionPane.YES_NO_OPTION);
                        if (confirmed == JOptionPane.YES_OPTION) {
                            frame.setVisible(false);
                            frame.dispose();
                            new LoadMain();
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
                case "Create a new Project":
                    frame.setVisible(false);
                    frame.dispose();
                    new CreateProject(center);
                    break;
                case "List People":
                    frame.setVisible(false);
                    frame.dispose();
                    new ListPeople(center);
                    break;
                case "List Concluded Projects":
                    frame.setVisible(false);
                    frame.dispose();
                    new ListConcluded(center);
                    break;
                case "List Not Concluded Projects":
                    frame.setVisible(false);
                    frame.dispose();
                    new ListNotConcluded(center);
                    break;
                case "Add People":
                    frame.setVisible(false);
                    frame.dispose();
                    new AddPeople(center);
                    break;
            }
        }
    }
}
