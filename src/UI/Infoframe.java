package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Backend.*;

import Backend.*;

public class Infoframe extends JFrame {
    JList list;
    private JFrame frame;
    private JPanel panelA, panelB;
    private Button GetInfo;
    private Person person;

    public Infoframe(Person person) {
        this.person = person;
        frame = new JFrame();
        frame.setTitle(person.getName());
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        panelA = drawPanel();
        panelB = bottomFrame();

        panelA.setVisible(true);
        panelB.setVisible(true);
        frame.add(panelA, BorderLayout.CENTER);
        frame.add(panelB, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public JPanel drawPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        DefaultListModel<Backend.Task> listModel = new DefaultListModel<>();

        for (Backend.Task t : person.getTasks()) {
            listModel.addElement(t);
        }

        //List Tasks
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setPreferredSize(new Dimension(250, 300));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane listScroller = new JScrollPane(list);

        String sText = String.format("<html>Information<br/>Name: %s <br/> Email: %s<br/> ID: %d</html>", person.getName(), person.getEmail(), person.getId()); //Make a formatted string, same label but with a breakline
        panel.add(new JLabel(sText));
        panel.add(listScroller);
        panel.setVisible(true);
        return panel;
    }


    public JPanel bottomFrame() {
        JPanel panel = new JPanel();
        JButton button = new JButton();
        JButton button2 = new JButton();
        panel.setLayout(new GridLayout(1, 2));

        button.setText("Close");
        button.setActionCommand("close");
        button.addActionListener(new ButtonListener());

        button2.setText("Get Info");
        button2.setActionCommand("get info");
        button2.addActionListener(new ButtonListener());

        panel.add(button);
        panel.add(button2);
        return panel;

    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("close")) {
                frame.setVisible(false);
                frame.dispose();
            }
            if (cmd.equals("get info")) {
                new InframeTask((Task) list.getSelectedValue());
            }

        }
    }

}
