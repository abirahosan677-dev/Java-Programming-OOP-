/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Chen En
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagementFrame extends javax.swing.JFrame {
    private JTextField userIdField, nameField, passwordField, roleField;
    private JTextArea userListArea;
    private List<String> users = new ArrayList<>();
    private final String USERS_FILE = "users.txt";
    
    public UserManagementFrame(String userRole) {
        if (!userRole.equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(null, "Access Denied! Admin Only.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        
        setTitle("User Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Manage Users"));
        
        formPanel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        formPanel.add(userIdField);
        
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        formPanel.add(passwordField);
        
        formPanel.add(new JLabel("Role (admin/staff):"));
        roleField = new JTextField();
        formPanel.add(roleField);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton addButton = new JButton("Add User");
        JButton modifyButton = new JButton("Modify User");
        JButton deleteButton = new JButton("Delete User");
        
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        
        userListArea = new JTextArea(10, 40);
        userListArea.setEditable(false);
        loadUsers();
        
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(userListArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        addButton.addActionListener(e -> addUser());
        modifyButton.addActionListener(e -> modifyUser());
        deleteButton.addActionListener(e -> deleteUser());
    }
    
    private void loadUsers() {
        users.clear();
        userListArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(line);
                    userListArea.append(line + "\n");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading users.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addUser() {
        String newUser = userIdField.getText() + "," + nameField.getText() + "," + passwordField.getText() + "," + roleField.getText();
        users.add(newUser);
        saveUsers();
    }
    
    private void modifyUser() {
        String userId = userIdField.getText().trim();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            String[] parts = users.get(i).split(",");
            if (parts.length == 4 && parts[0].equals(userId)) {
                users.set(i, userId + "," + nameField.getText() + "," + passwordField.getText() + "," + roleField.getText());
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            saveUsers();
        }
    }
    
    private void deleteUser() {
        String userId = userIdField.getText().trim();
        boolean removed = users.removeIf(user -> user.startsWith(userId + ","));
        if (!removed) {
            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            saveUsers();
        }
    }
    
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (String user : users) {
                writer.write(user + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving users.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        loadUsers();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(UserManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
