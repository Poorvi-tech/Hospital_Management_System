package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    Choice choice;
    JTable table;

    SearchRoom(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setLayout(null);
        panel.setBackground(new Color(100,225,225));
        add(panel);

        JLabel For = new JLabel("Search for Room");
        For.setBounds(250,11,186,31);
        For.setFont(new Font("Tahoma",Font.BOLD,20));
        For.setForeground(Color.BLACK);
        panel.add(For);

        JLabel status = new JLabel("Status :");
        status.setBounds(70,70,80,20);
        status.setFont(new Font("Tahoma",Font.BOLD,14));
        status.setForeground(Color.BLACK);
        panel.add(status);

        choice =new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(10,187,700,210);
        table.setBackground(new Color(100,225,225));
        table.setForeground(Color.BLACK);
        panel.add(table);

        try{
            connection c = new connection();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel Roomno = new JLabel("Room Number :");
        Roomno.setBounds(10,162,150,20);
        Roomno.setFont(new Font("Tahoma",Font.BOLD,14));
        Roomno.setForeground(Color.BLACK);
        panel.add(Roomno);

        JLabel available = new JLabel("Availability :");
        available.setBounds(185,162,150,20);
        available.setFont(new Font("Tahoma",Font.BOLD,14));
        available.setForeground(Color.BLACK);
        panel.add(available);

        JLabel price = new JLabel("Price :");
        price.setBounds(358,162,150,20);
        price.setFont(new Font("Tahoma",Font.BOLD,14));
        price.setForeground(Color.BLACK);
        panel.add(price);

        JLabel Bed = new JLabel("Bed :");
        Bed.setBounds(533,162,150,20);
        Bed.setFont(new Font("Tahoma",Font.BOLD,14));
        Bed.setForeground(Color.BLACK);
        panel.add(Bed);

        JButton Search = new JButton("Search");
        Search.setBounds(200,420,120,25);
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.WHITE);
        panel.add(Search);
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from Room where Availability = '"+choice.getSelectedItem()+"'";

                try{
                    connection c = new connection();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(380,420,120,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });




        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);

    }

    public static void main(String[] args){

        new SearchRoom();
    }
}
