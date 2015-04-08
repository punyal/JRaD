/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */
package com.punyal.jrad.test;

import com.punyal.jrad.JRaDclient;
import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.network.events.MessageListenerInt;
import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.core.radius.RADIUS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.EventObject;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class AuthenticationGUI extends JFrame {
    JRaDclient jRaDclient;
    
    public AuthenticationGUI() {
        super("jRaD Authentication Client tester");
        jRaDclient = new JRaDclient();
        initComponents();
        startListeners();
        jRaDclient.addListener(new MessageListenerInt() {

            @Override
            public void newIncomingMessage(EventObject evt) {
                if(((Message)evt.getSource()).response == null)
                    JOptionPane.showMessageDialog(rootPane, "Connection Timeout!\nPosible causes:\n\t- No connection to server\n\t- No correct Secret Key", "Connection Problem", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(rootPane, Utils.messagePrint(((Message)evt.getSource()).response));
            }
        });
    }
    
    /**
     * Actions defined here.
     */
    private void initComponents() {

        tagSecretKey = new javax.swing.JLabel();
        secretKey = new javax.swing.JTextField();
        tagServerIP = new javax.swing.JLabel();
        serverIP = new javax.swing.JTextField();
        tagUserName = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        tagUserPassword = new javax.swing.JLabel();
        userPassword = new javax.swing.JTextField();
        tagCallingStationID = new javax.swing.JLabel();
        callingStationID = new javax.swing.JTextField();
        tagCalledStationID = new javax.swing.JLabel();
        calledStationID = new javax.swing.JTextField();
        tagOpt1 = new javax.swing.JLabel();
        opt1 = new javax.swing.JTextField();
        tagOpt2 = new javax.swing.JLabel();
        opt2 = new javax.swing.JTextField();
        tagOpt4 = new javax.swing.JLabel();
        tagOpt3 = new javax.swing.JLabel();
        opt3 = new javax.swing.JTextField();
        Opt4 = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tagSecretKey.setText("NAS Secret Key");

        secretKey.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tagServerIP.setText("Server IP");

        serverIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        serverIP.setToolTipText("");

        tagUserName.setText("User Name");

        userName.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tagUserPassword.setText("User Password");

        userPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tagCallingStationID.setText("Calling Station ID");

        callingStationID.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tagCalledStationID.setText("Called Station ID");

        calledStationID.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tagOpt1.setText("Opt 1");

        opt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        opt1.setText("Not used");
        opt1.setToolTipText("");

        tagOpt2.setText("Opt 2");

        opt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        opt2.setText("Not used");

        tagOpt4.setText("Opt 4");

        tagOpt3.setText("Opt 3");

        opt3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        opt3.setText("Not used");

        Opt4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Opt4.setText("Not used");

        buttonSend.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tagSecretKey)
                    .addComponent(tagServerIP)
                    .addComponent(tagOpt2)
                    .addComponent(tagOpt3)
                    .addComponent(tagOpt4)
                    .addComponent(tagOpt1)
                    .addComponent(tagCalledStationID)
                    .addComponent(tagCallingStationID)
                    .addComponent(tagUserPassword)
                    .addComponent(tagUserName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverIP)
                    .addComponent(secretKey)
                    .addComponent(userName)
                    .addComponent(userPassword)
                    .addComponent(callingStationID)
                    .addComponent(calledStationID)
                    .addComponent(opt1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(Opt4)
                    .addComponent(opt3)
                    .addComponent(opt2))
                .addGap(9, 9, 9))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSend)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagServerIP)
                    .addComponent(serverIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagSecretKey)
                    .addComponent(secretKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagUserName)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagUserPassword)
                    .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagCallingStationID)
                    .addComponent(callingStationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagCalledStationID)
                    .addComponent(calledStationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagOpt1)
                    .addComponent(opt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagOpt2)
                    .addComponent(opt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagOpt3)
                    .addComponent(opt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagOpt4)
                    .addComponent(Opt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSend)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void buttonSendActionPerformed(ActionEvent evt) {
        jRaDclient.setSecretKey(secretKey.getText());
        jRaDclient.setServer(serverIP.getText(), RADIUS.DEFAULT_RADIUS_PORT);
        jRaDclient.authenticate(userName.getText(), userPassword.getText());
    }
    
    private void secretKeyActionPerformed(ActionEvent evt) {
        System.out.println("New secretKey");
    }
    
    /**
     * Main
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthenticationGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify      
    private javax.swing.JTextField Opt4;
    private javax.swing.JButton buttonSend;
    private javax.swing.JTextField calledStationID;
    private javax.swing.JTextField callingStationID;
    private javax.swing.JTextField opt1;
    private javax.swing.JTextField opt2;
    private javax.swing.JTextField opt3;
    private javax.swing.JTextField secretKey;
    private javax.swing.JTextField serverIP;
    private javax.swing.JLabel tagCalledStationID;
    private javax.swing.JLabel tagCallingStationID;
    private javax.swing.JLabel tagOpt1;
    private javax.swing.JLabel tagOpt2;
    private javax.swing.JLabel tagOpt3;
    private javax.swing.JLabel tagOpt4;
    private javax.swing.JLabel tagSecretKey;
    private javax.swing.JLabel tagServerIP;
    private javax.swing.JLabel tagUserName;
    private javax.swing.JLabel tagUserPassword;
    private javax.swing.JTextField userName;
    private javax.swing.JTextField userPassword;
    // End of variables declaration          
    
    
   
    private void startListeners() {
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });
        
    }
    
}
