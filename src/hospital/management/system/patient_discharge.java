package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class patient_discharge extends JFrame {

    patient_discharge(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setLayout(null);
        panel.setBackground(new Color(100,225,225));
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100,20,150,20);
        label.setBackground(new Color(100,225,225));
        label.setFont(new Font("Tahoma",Font.BOLD,20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel label2 = new JLabel("Customer Id");
        label2.setBounds(30,80,150,20);
        label2.setBackground(new Color(100,225,225));
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try{
            connection c = new connection();
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info");
            while (resultSet.next()){
                choice.add(resultSet.getString("number"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30,130,150,20);
        label3.setBackground(new Color(100,225,225));
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JLabel RNo = new JLabel();
        RNo.setBounds(200,130,150,20);
        RNo.setBackground(new Color(100,225,225));
        RNo.setFont(new Font("Tahoma",Font.BOLD,14));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30,180,150,20);
        label4.setBackground(new Color(100,225,225));
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JLabel INTime = new JLabel();
        INTime.setBounds(200,180,250,20);
        INTime.setBackground(new Color(100,225,225));
        INTime.setFont(new Font("Tahoma",Font.BOLD,14));
        INTime.setForeground(Color.WHITE);
        panel.add(INTime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30,230,150,20);
        label5.setBackground(new Color(100,225,225));
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        Date date = new Date();

        JLabel OUTTime = new JLabel(""+date);
        OUTTime.setBounds(200,230,250,20);
        OUTTime.setBackground(new Color(100,225,225));
        OUTTime.setFont(new Font("Tahoma",Font.BOLD,14));
        OUTTime.setForeground(Color.WHITE);
        panel.add(OUTTime);

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);

        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection c = new connection();
                try {
                    c.statement.executeUpdate("delete from Patient_Info where number = '"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '"+RNo.getText()+"'");
                    JOptionPane.showMessageDialog(null,"Done");
                    setVisible(false);

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(170,300,120,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection c = new connection();
                try{
                    ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info where number = '"+choice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        RNo.setText(resultSet.getString("Room_Number"));
                        INTime.setText(resultSet.getString("Time"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(300,300,120,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args){
        new patient_discharge();
    }
}
