package UI;

import Project.Center;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadNew extends JFrame {
    private JPanel panelA, panelB;
    private JFrame frame;
    private JTextField text, name;

    public LoadNew() {
        frame = new JFrame();
        frame.setTitle("Create a new Center");
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

    public JPanel bottomFrame() {
        JPanel panel = new JPanel();
        JButton button = new JButton();
        JButton button2 = new JButton();

        panel.setLayout(new GridLayout(1, 2));
        button.setText("Back");
        button.setActionCommand("Back");
        button.addActionListener(new ButtonListener());

        button2.setText("Next");
        button2.setActionCommand("Next");
        button2.addActionListener(new ButtonListener());
        panel.add(button);
        panel.add(button2);
        return panel;

    }

    public JPanel drawPanel() {
        JPanel panel = new JPanel();
        JLabel labelName = new JLabel("Insert the name of the center");
        name = new JTextField(10);
        JLabel label = new JLabel("Insert the name of the file to load items from:");
        text = new JTextField(10);

        panel.add(labelName);
        panel.add(name);
        panel.add(label);
        panel.add(text);
        return panel;

    }


    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("Back")) {
                frame.setVisible(false);
                frame.dispose();
                new LoadMain();
            }

            if (cmd.equals("Next")) {
                frame.setVisible(false);
                frame.dispose();
                Center center = new Center();
                if (center.simpleBootloader(name.getText(), text.getText())) {
                    frame.setVisible(false);
                    frame.dispose();
                    new MainMenu(center);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid File", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }

}
