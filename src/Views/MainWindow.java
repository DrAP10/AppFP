/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.Controller;
import Models.Transaction;
import Models.TransactionType;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class MainWindow extends javax.swing.JFrame {
    public AddTransactionForm addTransactionFormView;
    public GeneralAdjustmentForm generalAdjustmentFormView;
    public Duration duration;
    public Effort effort;
    private JTable tabla;
    private DefaultTableModel dtm;
    private boolean transactionFormOutVisible;
    
    /**
     * Creates new form mainWindow
     */
    public MainWindow() {
        initComponents();
        addTransactionFormView = new AddTransactionForm(this, true);
        generalAdjustmentFormView = new GeneralAdjustmentForm(this, true);
        duration = new Duration(this, true);
        effort = new Effort(this, true);
        inicializarComponentes();
    }
    
    
    public Transaction getTransaction(){
        TransactionType type;
        switch (addTransactionFormView.comboBoxTransactionType.getSelectedIndex()) {
            case 0:
                type = TransactionType.EE;
                break;
            case 1:
                type = TransactionType.SE;
                break;
            case 2:
                type = TransactionType.CE;
                break;
            case 3:
                type = TransactionType.GLI;
                break;
            case 4:
                type = TransactionType.GLE;
                break;
            default:
                type = TransactionType.EE;
        }
        
        Transaction t;
        
        if (getTransactionFormOutVisible()) {
             t = new Transaction(type,
                    addTransactionFormView.textFieldDenominacion.getText(), 
                    Integer.valueOf(addTransactionFormView.textFieldNumFiles.getText()), 
                    Integer.valueOf(addTransactionFormView.textFieldNumFilesSalida.getText()), 
                    Integer.valueOf(addTransactionFormView.textFieldNumData.getText()),
                    Integer.valueOf(addTransactionFormView.textFieldNumDataSalida.getText()));
        } else {
            t = new Transaction(type,
                    addTransactionFormView.textFieldDenominacion.getText(), 
                    Integer.valueOf(addTransactionFormView.textFieldNumFiles.getText()), 
                    Integer.valueOf(addTransactionFormView.textFieldNumData.getText()));
        }
        return t;
    }
    
    
    public boolean isTransactionValid(){
        if (getTransactionFormOutVisible()) {
            return !(addTransactionFormView.textFieldDenominacion.getText().equals("")
                    || addTransactionFormView.textFieldNumData.getText().equals("")
                    || addTransactionFormView.textFieldNumFilesSalida.getText().equals("")
                    || addTransactionFormView.textFieldNumDataSalida.getText().equals("")
                    || addTransactionFormView.textFieldNumFiles.getText().equals(""));
        } else {
            return !(addTransactionFormView.textFieldDenominacion.getText().equals("")
                    || addTransactionFormView.textFieldNumData.getText().equals("")
                    || addTransactionFormView.textFieldNumFiles.getText().equals(""));
        }
    }
    
    
    public void setTransactionFormOutVisible(boolean visible) {
        transactionFormOutVisible = visible;
        this.addTransactionFormView.numDataSalidaLabel.setVisible(visible);
        this.addTransactionFormView.numFilesSalidaLabel.setVisible(visible);
        this.addTransactionFormView.textFieldNumDataSalida.setVisible(visible);
        this.addTransactionFormView.textFieldNumFilesSalida.setVisible(visible);
    }
    
    
    public boolean getTransactionFormOutVisible(){
            return transactionFormOutVisible;
    }
    
    
    private void inicializarComponentes() {
        // configuramos los componentes

        dtm = new DefaultTableModel();
        tabla = this.jTable;
        tabla.setModel(dtm);
        String columNames[] = {"EE", "SE", "CE", "GLI", "GLE"};
        dtm.setColumnIdentifiers(new Object[]{"Descripción", "Sencilla", "Media", "Compleja", "Total PF"});
        dtm.setNumRows(5);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.setValueAt(columNames[i], i, 0);
        }
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(); //alinear al centro 
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    /**
     * 
     * @param m Matriz de complejidad
     */
    public void updateTable(int[][] m){
        //Sencillas
        dtm.setValueAt(m[0][0]+" x 3 ", 0, 1);
        dtm.setValueAt(m[1][0]+" x 4 ", 1, 1);
        dtm.setValueAt(m[2][0]+" x 7 ", 2, 1);
        dtm.setValueAt(m[3][0]+" x 5 ", 3, 1);
        dtm.setValueAt(m[4][0]+" x 3 ", 4, 1);

        //Media
        dtm.setValueAt(m[0][1]+" x 4 ", 0, 2);
        dtm.setValueAt(m[1][1]+" x 5 ", 1, 2);
        dtm.setValueAt(m[2][1]+" x 10 ", 2, 2);
        dtm.setValueAt(m[3][1]+" x 7 ", 3, 2);
        dtm.setValueAt(m[4][1]+" x 4", 4, 2);

        //Compleja
        dtm.setValueAt(m[0][2]+" x 6 ", 0, 3);
        dtm.setValueAt(m[1][2]+" x 7 ", 1, 3);
        dtm.setValueAt(m[2][2]+" x 15 ", 2, 3);
        dtm.setValueAt(m[3][2]+" x 10 ", 3, 3);
        dtm.setValueAt(m[4][2]+" x 6 ", 4, 3);

        //Totales PF
        dtm.setValueAt(String.valueOf(m[0][0]*3+m[0][1]*4+m[0][2]*6), 0, 4);
        dtm.setValueAt(String.valueOf(m[1][0]*4+m[1][1]*5+m[1][2]*7), 1, 4);
        dtm.setValueAt(String.valueOf(m[2][0]*7+m[2][1]*10+m[2][2]*15), 2, 4);
        dtm.setValueAt(String.valueOf(m[3][0]*5+m[3][1]*7+m[3][2]*10), 3, 4);
        dtm.setValueAt(String.valueOf(m[4][0]*3+m[4][1]*4+m[4][2]*6), 4, 4);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addTransacionButton = new javax.swing.JButton();
        caracteristicasButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        resumeLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pfnajLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fAjLabel = new javax.swing.JLabel();
        pAjLabel = new javax.swing.JLabel();
        durationButton = new javax.swing.JButton();
        effortButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        duracionjLabel = new javax.swing.JLabel();
        esfuerzojLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("App Puntos Función");

        addTransacionButton.setBackground(new java.awt.Color(51, 204, 255));
        addTransacionButton.setText("Añadir Transacción");
        addTransacionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTransacionButtonActionPerformed(evt);
            }
        });

        caracteristicasButton.setBackground(new java.awt.Color(51, 204, 255));
        caracteristicasButton.setText("Características Generales");
        caracteristicasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caracteristicasButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable);

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        resumeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        resumeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resumeLabel.setText("Resumen");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("TOTAL PUNTOS FUNCIÓN NO AJUSTADOS (PFNA):");

        pfnajLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("FACTOR DE AJUSTE : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("PUNTOS DE FUNCIÓN AJUSTADOS:");

        fAjLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pAjLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        durationButton.setBackground(new java.awt.Color(51, 204, 255));
        durationButton.setText("Duración");
        durationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationButtonActionPerformed(evt);
            }
        });

        effortButton.setBackground(new java.awt.Color(51, 204, 255));
        effortButton.setText("Esfuerzo");
        effortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                effortButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("ESFUERZO:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("DURACIÓN:");

        duracionjLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        esfuerzojLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(resumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pfnajLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fAjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(durationButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addTransacionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(caracteristicasButton, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(effortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pAjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(duracionjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(esfuerzojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTransacionButton)
                    .addComponent(caracteristicasButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(durationButton)
                    .addComponent(effortButton))
                .addGap(70, 70, 70)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resumeLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(pfnajLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fAjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(pAjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(duracionjLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(esfuerzojLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTransacionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTransacionButtonActionPerformed
        // Botón para abrir la ventana del form de añadir funcionalidad
        addTransactionFormView.setVisible(true);
        
    }//GEN-LAST:event_addTransacionButtonActionPerformed

    
    private void caracteristicasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caracteristicasButtonActionPerformed
        // Botón para abrir la ventana del form de Ajuste de PF con características generales del Sistema
        generalAdjustmentFormView.setVisible(true);
        
    }//GEN-LAST:event_caracteristicasButtonActionPerformed

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void durationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationButtonActionPerformed
        // Botón para abrir la ventana con los datos de estimación de esfuerzo
        duration.setVisible(true);
    }//GEN-LAST:event_durationButtonActionPerformed

    private void effortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_effortButtonActionPerformed
        // Botón para abrir la ventana con los datos de estimación de esfuerzo
        effort.setVisible(true);
    }//GEN-LAST:event_effortButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
/*        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindows = new MainWindow();
                mainWindows.setVisible(true);
                mainWindows.setLocationRelativeTo(null);
                Controller controller = new Controller(mainWindows, null);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTransacionButton;
    private javax.swing.JButton caracteristicasButton;
    public javax.swing.JLabel duracionjLabel;
    private javax.swing.JButton durationButton;
    private javax.swing.JButton effortButton;
    public javax.swing.JLabel esfuerzojLabel;
    public javax.swing.JLabel fAjLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable;
    public javax.swing.JLabel pAjLabel;
    public javax.swing.JLabel pfnajLabel;
    private javax.swing.JLabel resumeLabel;
    // End of variables declaration//GEN-END:variables
}
