package UI;

import Backend.Center;
import Backend.Scholar;
import Backend.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTask {
    private JFrame frame;
    private JTextField id,endDate, startDate;;
    private JList teachersList, scholarsList;
    private Center center;
    private JTextField selectorText;
    private JScrollPane scholarScroller,teacherScroller;
    CreateTask( ){
             this.center = center;
            frame = new JFrame();
            frame.setTitle("Create a task");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            JPanel A=drawPanel();
            JPanel B=responsiblePanel();
            JPanel C=bottomPanel();
            frame.add(A,SwingConstants.NORTH);
            frame.add(B,SwingConstants.CENTER);
            frame.add(C,SwingConstants.SOUTH);


    }
        private JPanel drawPanel(){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 1));

            JLabel idLabel = new JLabel("ID ");
            id= new JTextField();
            id.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel startDateLabel = new JLabel("StartDate(dd-mm-yyyy)");
            startDate = new JTextField(10);
            startDate.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel endDateLabel = new JLabel("StartDate(dd-mm-yyyy)");
            endDate = new JTextField(10);
            endDate.setHorizontalAlignment(SwingConstants.CENTER);

            return panel;
    }
    private JPanel responsiblePanel(){
            JPanel panel= new JPanel();
            JTextField selectorText= new JTextField("Add teacher or scholar as responsible?");
            JButton selectorButton = new JButton("Add");
            selectorButton.setActionCommand("Add");
            selectorButton.addActionListener(new ButtonListener());

            DefaultListModel<Teacher> teacherListModel = new DefaultListModel<Teacher>();
            for (Teacher t : center.getTeachers())
                teacherListModel.addElement(t);
            teachersList = new JList(teacherListModel);
            teachersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane teacherScroller = new JScrollPane(teachersList);


            DefaultListModel<Scholar> scholarsListModel = new DefaultListModel<>();
            for (Scholar s : center.listFreeScholar())
                scholarsListModel.addElement(s);
            scholarsList = new JList(scholarsListModel);
            scholarsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scholarScroller = new JScrollPane(scholarsList);

        return panel;
    }
    public JPanel bottomPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton();
        JButton button2 = new JButton();
        button2.setVisible(false);
        panel.setLayout(new GridLayout(1, 2));

        button.setText("Back");
        button.setActionCommand("Back");
        button.addActionListener(new ButtonListener());

        button2.setText("Create");
        button2.setActionCommand("Create");
        button2.addActionListener(new ButtonListener());

        panel.add(button);
        panel.add(button2);
        return panel;

    }
    private JDialog  TeacherFrame(){
        JDialog  dialog= new JDialog ();
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(teacherScroller,BorderLayout.CENTER);
        dialog.getContentPane().setLayout(new FlowLayout());
        dialog.getContentPane().add((Component) teachersList.getSelectedValue());
        return dialog;
    }
    private JDialog  ScholarFrame(){
        JDialog  dialog= new JDialog ();
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(scholarScroller,BorderLayout.CENTER);
        dialog.getContentPane().setLayout(new FlowLayout());
        dialog.getContentPane().add((Component) scholarsList.getSelectedValue());
        return dialog;
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals("Back")) {
                frame.setVisible(false);
                frame.dispose();
                new MainMenu(center);
            }
            if (cmd.equals("Create")) {

            }
            if(cmd.equals("Add")){
                if(selectorText.getText().equals("Teacher")){
                    JDialog teacherFrame= TeacherFrame();
                    teacherFrame.setVisible(true);
                }
                if(selectorText.getText().equals("Scholar")){
                    JDialog scholarFrame= TeacherFrame();
                    scholarFrame.setVisible(true);
                }

            }
        }
    }
}
