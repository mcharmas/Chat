/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConnectionPanel.java
 *
 * Created on 2010-04-12, 23:46:10
 */
package gui;

import client.Client;
import client.ClientListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import packet.Msg;

/**
 *
 * @author orbit
 */
public class ChatPanel extends javax.swing.JPanel implements ClientListener {

    Client client = null;
    ArrayList<String> clientList = new ArrayList<String>();

    /** Creates new form ConnectionPanel */
    public ChatPanel() {
        initComponents();
        connectionPanel1.setParent(this);
    }

    public boolean isInClientList(String client) {
        for (String c : clientList) {
            if (c.equals(client)) {
                return true;
            }
        }
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userList = new javax.swing.JScrollPane();
        jUserList = new javax.swing.JList();
        messageTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        sendButton = new javax.swing.JButton();
        connectionPanel1 = new gui.ConnectionPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();

        jUserList.setEnabled(false);
        userList.setViewportView(jUserList);

        messageTextField.setEnabled(false);
        messageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageTextFieldKeyPressed(evt);
            }
        });

        jLabel1.setText("Message:");

        sendButton.setText("Send");
        sendButton.setEnabled(false);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(messageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sendButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectionPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(userList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

        String text = messageTextField.getText();

        if (!text.equals("")) {
            if (text.startsWith("/msg ")) {
                String txt = text.substring(5);
                String[] tmp = txt.split(" ");
                if (tmp.length <= 1) {
                    return;
                }
                String username = tmp[0];
                if (isInClientList(username)) {
                    String message = txt.substring(username.length());
                    Msg msg = new Msg(Boolean.FALSE, Boolean.FALSE);
                    msg.setTo(username);
                    msg.setMessage(message);
                    msg.setFrom(client.getUsername());
                    client.sendMessage(msg);
                    messageTextField.setText("");
                    chatArea.append("\n[P] to " + msg.getTo() + ": " + msg.getMessage());
                    chatArea.setCaretPosition(chatArea.getText().length() - 1);
                }
            } else {
                Msg message = new Msg(Boolean.FALSE, Boolean.FALSE);
                message.setFrom(client.getUsername());
                message.setMessage(messageTextField.getText());
                client.sendMessage(message);
                messageTextField.setText("");
            }

        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void messageTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageTextFieldKeyPressed
        if (evt.getKeyChar() == '\n') {
            sendButtonActionPerformed(null);
        }
    }//GEN-LAST:event_messageTextFieldKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatArea;
    private gui.ConnectionPanel connectionPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList jUserList;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JButton sendButton;
    private javax.swing.JScrollPane userList;
    // End of variables declaration//GEN-END:variables

    public void gotMessage(Msg message) {
        if (message.getTo() != null) {
            chatArea.append("\n[P] " + message.getFrom() + ": " + message.getMessage());
            chatArea.setCaretPosition(chatArea.getText().length() - 1);
        } else {
            chatArea.append("\n" + message.getFrom() + ": " + message.getMessage());
            chatArea.setCaretPosition(chatArea.getText().length() - 1);
        }
    }

    public void gotUserList(ArrayList<String> userList) {
        DefaultListModel model = new DefaultListModel();
        for (String c : userList) {
            model.addElement(c);
        }
        jUserList.setModel(model);
        clientList = userList;
    }

    public void connectionStateChanged(State state) {
        if (state == State.CONNECTED) {
            client = connectionPanel1.getClient();
            setComponentsEnabled(true);
            connectionPanel1.setInputEnbaled(false);
        } else if (state == State.DISCONNECTED) {
            client = null;
            setComponentsEnabled(false);
            connectionPanel1.setInputEnbaled(true);
            ((DefaultListModel) jUserList.getModel()).clear();
            connectionPanel1.setConnected(false);
        }
    }

    private void setComponentsEnabled(boolean enabled) {
        jUserList.setEnabled(enabled);
        chatArea.setEnabled(enabled);
        messageTextField.setEnabled(enabled);
        sendButton.setEnabled(enabled);
    }
}
