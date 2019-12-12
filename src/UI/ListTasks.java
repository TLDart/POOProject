package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListTasks extends JFrame {
    private JPanel panelA, panelB;
    private JFrame frame;
    private JButton search, back;
    private Center center;
    private Project project;
    private JList list;

    ListTasks(Center center, Project project) {
        this.center = center;
        frame = new JFrame();
        frame.setTitle("Task Done");
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
        //panel.setLayout(new GridLayout(2, 1));

        String str = "<html>Task on the current project<br/> Hit Search for more info</html>"; //Make a formatted string, same label but with a breakline

        JLabel partA = new JLabel(str);
        partA.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<Person> listModel = new DefaultListModel<Person>();
        for (Teacher t : center.getTeachers()) {
            listModel.addElement(t);
        }
        for (Scholar s : center.getScholars()) {
            listModel.addElement(s);
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane listScroller = new JScrollPane(list);
        list.setPreferredSize(new Dimension(250, 300));

        panel.add(partA);
        panel.add(listScroller);
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
            if (cmd.equals("search")) {
                new Infoframe((Person) list.getSelectedValue());
            }
            if (cmd.equals(("back"))) {
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
        }
    }
}