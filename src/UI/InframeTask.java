package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Backend.*;

import Backend.*;

public class InframeTask extends JFrame {
    JList list;
    private JFrame frame;
    private JPanel panelA, panelB;
    ;
    private Task task;

    public InframeTask(Task task) {
        this.task = task;
        frame = new JFrame();
        frame.setTitle("Task");
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
        JPanel panel = new JPanel(new GridLayout(6, 2));
        //List Tasks
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String calendarEnd = (task.getEndTime() == null) ? "" : sdf.format(task.getEndTime().getTime());
        String responsible = (task.getResponsible() == null) ? "None" : task.getResponsible().getName();
        String[] words = {"task ID:", String.valueOf(task.getId()), "Status: ", String.valueOf(task.getStatus()), "StartDate: ", sdf.format(task.getStartDate().getTime()), "Estimated End: ", sdf.format(task.getEstimatedFinish().getTime()), "Task End: ", calendarEnd, "Responsible: ", responsible};


        for (String s : words) {
            JLabel label = new JLabel(s);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);
        }
        panel.setVisible(true);
        return panel;
    }


    public JPanel bottomFrame() {
        JPanel panel = new JPanel();
        JButton button = new JButton();

        button.setText("Close");
        button.setActionCommand("close");
        button.addActionListener(new ButtonListener());

        panel.add(button);
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
        }
    }

}
