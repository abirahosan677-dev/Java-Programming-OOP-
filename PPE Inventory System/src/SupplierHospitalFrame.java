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
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierHospitalFrame extends javax.swing.JFrame {
    private JTextField nameField, typeField, contactField;
    private JTextArea recordArea;
    private final String RECORD_FILE = "supplier_hospital.txt";

    public SupplierHospitalFrame() {
        setTitle("Supplier & Hospital Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Manage Records"));
        nameField = new JTextField();
        typeField = new JTextField();
        contactField = new JTextField();
        JButton addButton = new JButton("Add Record");
        JButton deleteButton = new JButton("Delete Record");
        JButton modifyButton = new JButton("Modify Record");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Type (Supplier/Hospital):"));
        inputPanel.add(typeField);
        inputPanel.add(new JLabel("Contact:"));
        inputPanel.add(contactField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(modifyButton);

        recordArea = new JTextArea(15, 40);
        recordArea.setEditable(false);
        loadRecords();

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(recordArea), BorderLayout.CENTER);

        addButton.addActionListener(this::addRecord);
        deleteButton.addActionListener(this::deleteRecord);
        modifyButton.addActionListener(this::modifyRecord);
    }

    private void loadRecords() {
        recordArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recordArea.append(line + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading supplier_hospital.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addRecord(ActionEvent e) {
        String name = nameField.getText().trim();
        String type = typeField.getText().trim();
        String contact = contactField.getText().trim();

        if (name.isEmpty() || type.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORD_FILE, true))) {
            writer.write(name + "," + type + "," + contact + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving record!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        loadRecords();
        nameField.setText("");
        typeField.setText("");
        contactField.setText("");
    }

    private void deleteRecord(ActionEvent e) {
        String nameToDelete = nameField.getText().trim();
        if (nameToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a name to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> records = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(nameToDelete + ",")) {
                    records.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Record not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORD_FILE))) {
            for (String record : records) {
                writer.write(record + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        loadRecords();
    }

    private void modifyRecord(ActionEvent e) {
        String nameToModify = nameField.getText().trim();
        String newType = typeField.getText().trim();
        String newContact = contactField.getText().trim();

        if (nameToModify.isEmpty() || newType.isEmpty() || newContact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled to modify!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> records = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(nameToModify + ",")) {
                    records.add(nameToModify + "," + newType + "," + newContact);
                    found = true;
                } else {
                    records.add(line);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Record not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORD_FILE))) {
            for (String record : records) {
                writer.write(record + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        loadRecords();
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
            java.util.logging.Logger.getLogger(SupplierHospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierHospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierHospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierHospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierHospitalFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
