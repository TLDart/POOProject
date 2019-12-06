package UI;

import Project.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoadMain extends JFrame {
    private JPanel panelA, panelB;
    private JFrame frame;
    private JTextField text;
    private JRadioButton optionA, optionB;

    public LoadMain() {

        frame = new JFrame();
        frame.setTitle("Project Manager");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private JPanel drawPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Welcome to Marenager");
        label.setFont(new Font("Monaco", Font.PLAIN, 20));
        ButtonGroup group = new ButtonGroup();
        optionA = new JRadioButton("I want to create a new Project");
        optionA.addActionListener(new ButtonListener());
        optionA.setActionCommand("New Project");
        group.add(optionA);
        optionB = new JRadioButton("I already have a project");
        optionB.addActionListener(new ButtonListener());
        optionB.setActionCommand("Load Project");
        group.add(optionB);

        text = new JTextField(10);
        text.setVisible(false);

        panel.add(label);
        panel.add(optionA);
        panel.add(optionB);
        panel.add(text);
        return panel;

    }

    public JPanel bottomFrame() {
        JPanel panel = new JPanel();
        JButton button = new JButton();
        JButton button2 = new JButton();
        button2.setVisible(false);
        panel.setLayout(new GridLayout(1, 2));
        button.setText("Next");
        button.setActionCommand("next");
        button.addActionListener(new ButtonListener());

        panel.add(button2);
        panel.add(button);
        return panel;

    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("next")) {
                if (optionA.isSelected()) {
                    frame.setVisible(false);
                    frame.dispose();
                    new LoadNew();
                }
                if (optionB.isSelected()) {
                    Center center = new Center();
                    if (center.bootloader(text.getText())) {
                        frame.setVisible(false);
                        frame.dispose();
                        new MainMenu(center);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid File", "Error", JOptionPane.WARNING_MESSAGE);
                    }


                }
            }
            if (cmd.equals("New Project")) {
                text.setVisible(false);
                panelA.revalidate();
                text.repaint();
            }
            if (cmd.equals("Load Project")) {
                text.setVisible(true);
                text.requestFocus();
                panelA.revalidate();
            }
        }
    }

}
