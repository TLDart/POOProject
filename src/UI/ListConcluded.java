package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListConcluded {
    private JFrame frame;
    private JPanel panelA, panelB;
    private Center center;
    private JList list;

    public ListConcluded(Center center) {
        this.center = center;
        frame = new JFrame();
        frame.setTitle("Concluded Projects");
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

    public JPanel drawPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        String str = "<html>List of all concluded Projects <br/> Hit Search for more info</html>"; //Make a formatted string, same label but with a breakline

        JLabel partA = new JLabel(str);
        partA.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<Project> listModel = new DefaultListModel<>();
        for (Project p : center.listFinished()) {
            listModel.addElement(p);
        }

        list = new JList<>(listModel);
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
        JButton back = new JButton();
        JButton search = new JButton();

        panel.setLayout(new GridLayout(1, 2));
        back.setText("Back");
        back.setActionCommand("back");
        back.addActionListener(new ButtonListener());

        search.setText("Get Info");
        search.setActionCommand("get info");
        search.addActionListener(new ButtonListener());

        panel.add(back);
        panel.add(search);
        return panel;
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("get info")) {
                Project p = (Project) list.getSelectedValue();
                frame.setVisible(false);
                frame.dispose();
                new ProjectMainMenu(center, p);
            }
            if (cmd.equals(("back"))) {
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
        }
    }


}
