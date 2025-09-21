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
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class TransactionFrame extends javax.swing.JFrame {
    private JTextField ppeField, quantityField, entityField, dateField;
    private JButton receiveButton, distributeButton, viewButton, addButton, deleteButton;
    private JTextArea transactionArea;
    private static final String TRANSACTION_FILE = "transactions.txt";

    public TransactionFrame() {
        setTitle("PPE Transactions");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Record Transaction"));
        
        inputPanel.add(new JLabel("PPE Item:"));
        ppeField = new JTextField();
        inputPanel.add(ppeField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        
        inputPanel.add(new JLabel("Supplier/Hospital:"));
        entityField = new JTextField();
        inputPanel.add(entityField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);
        
        JPanel buttonPanel = new JPanel();
        receiveButton = new JButton("Receive PPE");
        distributeButton = new JButton("Distribute PPE");
        viewButton = new JButton("View Transactions");
        addButton = new JButton("Add Transaction");
        deleteButton = new JButton("Delete Transaction");
        
        buttonPanel.add(receiveButton);
        buttonPanel.add(distributeButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.NORTH);
        
        transactionArea = new JTextArea(15, 50);
        transactionArea.setEditable(false);
        add(new JScrollPane(transactionArea), BorderLayout.CENTER);

        addButton.addActionListener(e -> addTransaction());
        deleteButton.addActionListener(e -> deleteTransaction());
        viewButton.addActionListener(e -> loadTransactions());
    }

    private void addTransaction() {
        String ppe = ppeField.getText().trim();
        String quantity = quantityField.getText().trim();
        String entity = entityField.getText().trim();
        String date = dateField.getText().trim();
        
        if (ppe.isEmpty() || quantity.isEmpty() || entity.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(ppe + "," + quantity + "," + entity + "," + date + "\n");
            JOptionPane.showMessageDialog(this, "Transaction added successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving transaction.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        loadTransactions();
    }

    private void deleteTransaction() {
        String ppeToDelete = ppeField.getText().trim();
        if (ppeToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a PPE item to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> transactions = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(ppeToDelete + ",")) {
                    transactions.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading transactions.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Transaction not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE))) {
            for (String record : transactions) {
                writer.write(record + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating transactions.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        loadTransactions();
    }

    private void loadTransactions() {
        transactionArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactionArea.append(line + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading transactions.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Creates new form TransactionFrame
     */

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
        SwingUtilities.invokeLater(() -> new TransactionFrame().setVisible(true));        
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
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
