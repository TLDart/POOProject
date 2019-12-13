package UI;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class LoadMain extends JFrame {
    private JPanel panelA, panelB, Dpanel;
    private JFrame frame;
    private JTextField text;
    private JRadioButton optionA, optionB;
    private JButton button1, button2, button3;

    public LoadMain() {

        frame = new JFrame();
        frame.setTitle("Project Manager");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        panelA = drawPanel();
        panelB = bottomFrame();
        Dpanel = dynamicPanel();

        panelA.setVisible(true);
        panelB.setVisible(true);
        Dpanel.setVisible(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(panelA);
        panel.add(Dpanel);
        frame.add(panel);
        frame.add(panelB, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel drawPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Welcome to Marenager");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        ButtonGroup group = new ButtonGroup();
        optionA = new JRadioButton("I want to create a new Center");
        optionA.addActionListener(new ButtonListener());
        optionA.setActionCommand("New Project");
        optionA.setSelected(true);
        group.add(optionA);
        optionB = new JRadioButton("I already have a Center");
        optionB.addActionListener(new ButtonListener());
        optionB.setActionCommand("Load Project");

        group.add(optionB);


        text = new JTextField(10);
        text.setVisible(false);
        text.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(label);
        panel.add(optionA);
        panel.add(optionB);
        panel.add(dynamicPanel());
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

    public JPanel dynamicPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        File f = new File("./configs/cache.txt");
        ArrayList<String> names = new ArrayList<>();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((s = br.readLine()) != null) {
                names.add(s);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 3 && i < names.size(); i++) {
            if (i == 0) {
                button1 = new JButton();
                button1.setText(names.get(i));
                button1.setVisible(true);
                button1.setActionCommand("button1");
                button1.addActionListener(new ButtonListener());
                panel.add(button1);
            }
            if (i == 1) {
                button2 = new JButton();
                button2.setActionCommand("button2");
                button2.addActionListener(new ButtonListener());
                button2.setText(names.get(i));
                button2.setVisible(true);
                panel.add(button2);
            }
            if (i == 2) {
                button3 = new JButton();
                button3.setActionCommand("button3");
                button3.addActionListener(new ButtonListener());
                button3.setText(names.get(i));
                button3.setVisible(true);
                panel.add(button3);
            }
        }

        panel.setVisible(false);
        return panel;
    }

    public Center bootloader(String path) {
        File f = new File(path);
        Center center;
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(f));
            center = (Center) os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            center = null;
        }
        return center;

    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            //System.out.println(cmd);
            if (cmd.equals("next")) {
                if (optionA.isSelected()) {
                    frame.setVisible(false);
                    frame.dispose();
                    new LoadNew();
                }
                if (optionB.isSelected()) {
                    Center center = bootloader("./saves/" + text.getText());
                    if (text.getText().endsWith(".obj") && center != null) {
                        frame.setVisible(false);
                        frame.dispose();
                        new MainMenu(center);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid File", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            if (cmd.equals("button1")) {
                Center center = bootloader("./saves/" + button1.getText());
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
            if (cmd.equals("button2")) {
                Center center = bootloader("./saves/" + button2.getText());
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
            if (cmd.equals("button3")) {
                Center center = bootloader("./saves/" + button3.getText());
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
            if (cmd.equals("New Project")) {
                text.setVisible(false);
                Dpanel.setVisible(false);
                panelA.revalidate();
                text.repaint();
            }
            if (cmd.equals("Load Project")) {
                text.setVisible(true);
                text.requestFocus();
                Dpanel.setVisible(true);
                panelA.revalidate();
            }
        }
    }
}
