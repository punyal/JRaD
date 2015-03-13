package com.punyal.jrad.test;

import com.punyal.jrad.core.radius.RADIUS;
import static com.punyal.jrad.core.radius.RADIUS.Code.*;
import java.awt.GridLayout;
import javax.swing.*;

public class ClientGUI2 {
    
    JLabel tagCode = new JLabel("Code", JLabel.CENTER);
    JLabel tagID = new JLabel("ID", JLabel.CENTER);
    JLabel tagLength = new JLabel("Length", JLabel.CENTER);
    JComboBox<RADIUS.Code> code = new JComboBox<>();
    JTextField iD = new JTextField("");
    JTextField length = new JTextField("");
    JLabel tagAuthenticator = new JLabel("Authenticator", JLabel.CENTER);
    JTextField authenticator = new JTextField("");
    
    ClientGUI2() {
        JFrame frame = new JFrame("JRaD Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(1, 1, 10, 10);
        frame.setLayout(layout);
        addRADIUSMessage(frame);
        loadRADIUSValues();
        frame.pack();
        frame.setVisible(true);
    }
    
    
    private void addRADIUSMessage(JFrame frame) {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(2, 1, 0, 0);
        panel.setLayout(layout);
        // panel 1
        JPanel panel1 = new JPanel();
        GridLayout layout1 = new GridLayout(1, 3, 0, 0);
        panel1.setLayout(layout1);
            JPanel panel1_1 = new JPanel();
            GridLayout layout1_1 = new GridLayout(2, 1, 0, 0);
            panel1_1.setLayout(layout1_1);
            panel1_1.add(tagCode);
            panel1_1.add(code);
            panel1.add(panel1_1);
            
            JPanel panel1_2 = new JPanel();
            GridLayout layout1_2 = new GridLayout(2, 1, 0, 0);
            panel1_2.setLayout(layout1_2);
            panel1_2.add(tagID);
            iD.setEnabled(false);
            panel1_2.add(iD);
            panel1.add(panel1_2);
            
            JPanel panel1_3 = new JPanel();
            GridLayout layout1_3 = new GridLayout(2, 1, 0, 0);
            panel1_3.setLayout(layout1_3);
            panel1_3.add(tagLength);
            length.setEnabled(false);
            panel1_3.add(length);
            panel1.add(panel1_3);
        panel.add(panel1);
            
        // panel 2
        JPanel panel2 = new JPanel();
        GridLayout layout2 = new GridLayout(2, 1, 0, 0);
        panel2.setLayout(layout2);
        panel2.add(tagAuthenticator);
        authenticator.setEnabled(false);
        panel2.add(authenticator);
        
        
        panel.add(panel2);
        
        frame.getContentPane().add(panel);
    }
    
    public void loadRADIUSValues() {
        code.addItem(ACCESS_REQUEST);
        code.addItem(ACCESS_ACCEPT);
    }
    public static void main(String[] args){
        ClientGUI2 app = new ClientGUI2();
    }
}