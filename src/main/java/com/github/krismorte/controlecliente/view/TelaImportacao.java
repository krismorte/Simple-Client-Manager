/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.view;

import com.github.krismorte.controlecliente.dao.ClienteDao;
import com.github.krismorte.controlecliente.model.Cliente;
import com.github.krismorte.controlecliente.util.NoPhoneNumberException;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author krisnamourtscf
 */
public class TelaImportacao extends javax.swing.JDialog {

    private ClienteDao dao = new ClienteDao();

    /**
     * Creates new form TelaImportacao
     */
    public TelaImportacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnImportar.setEnabled(false);
        barraDeProgresso.setEnabled(false);
        txtArquivo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtArquivo.getText().endsWith("vcf")) {
                    btnImportar.setEnabled(true);
                } else {
                    btnImportar.setEnabled(false);
                }
            }

        });
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
        txtArquivo = new javax.swing.JTextField();
        btnArquivo = new javax.swing.JButton();
        btnImportar = new javax.swing.JButton();
        barraDeProgresso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Importação de contatos");

        jLabel1.setText("Caminho:");

        btnArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-opened-folder-16.png"))); // NOI18N
        btnArquivo.setToolTipText("search file");
        btnArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArquivoActionPerformed(evt);
            }
        });

        btnImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-checked-16.png"))); // NOI18N
        btnImportar.setToolTipText("import");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnImportar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnArquivo))
                    .addComponent(barraDeProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barraDeProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArquivoActionPerformed
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("electronic business cards.", "vcf", "vCard");
        file.setFileFilter(filter);
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtArquivo.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtArquivo.setText(arquivo.getPath());
            btnImportar.setEnabled(true);
            barraDeProgresso.setEnabled(true);
        }
    }//GEN-LAST:event_btnArquivoActionPerformed

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
		
        barraDeProgresso.setStringPainted(true);
        barraDeProgresso.setValue(0);

        final SwingWorker w = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                List<VCard> list = Ezvcard.parse(
                        new InputStreamReader(
                                new FileInputStream(txtArquivo.getText()),"UTF-8")).all();
                int total = list.size();
                barraDeProgresso.setMaximum(list.size());
                for (int i = 0; i <= list.size() - 1; i++) {
                    try {
                        barraDeProgresso.setValue(i + 1);
                        barraDeProgresso.setString("importando " + (i + 1) + " de " + total);
                        salvarClienteImportado(list.get(i));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Importação finalizada!");
                fecharTela();
                return 0;
            }
        };

        w.execute();

    }//GEN-LAST:event_btnImportarActionPerformed

    private void fecharTela() {
        this.dispose();
    }

    private void salvarClienteImportado(VCard card) {
        try {
            Cliente cliente = new Cliente(card);
            dao.add(cliente);
        } catch (NullPointerException | NoPhoneNumberException ex) {

        }

    }

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
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaImportacao dialog = new TelaImportacao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraDeProgresso;
    private javax.swing.JButton btnArquivo;
    private javax.swing.JButton btnImportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtArquivo;
    // End of variables declaration//GEN-END:variables
}
