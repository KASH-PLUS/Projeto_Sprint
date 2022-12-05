/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.jframekash;

import banco.TbUsuario;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class TelaTemperatura extends javax.swing.JFrame {
    Looca looca = new Looca();
    Temperatura temperatura = looca.getTemperatura();
    Sistema sistema = looca.getSistema();
    ImageIcon img = new ImageIcon("src/main/resources/logoWhite.png");
    TbUsuario usuario = new TbUsuario();
    
    public TelaTemperatura() {
        initComponents();
        
        Timer timer = new Timer();
        
        if (sistema.getSistemaOperacional().equalsIgnoreCase("Windows")) {
            lblAviso.setText("Aviso: O Windows não tem dá permissão para capturarmos a temperatura!");
        } else {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    lblTemperatura.setText("Temperatura Atual: ");
                    lblTemperatura.setText(lblTemperatura.getText() + temperatura.getTemperatura());
                    prgTemperatura.setValue((temperatura.getTemperatura()).intValue());
                }
            }, 00, 5000);
        }
        
        img.setImage(img.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), 1));
        lblLogo.setIcon(img);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        btnCPU = new javax.swing.JButton();
        btnSO = new javax.swing.JButton();
        btnMemo = new javax.swing.JButton();
        btnProcessos = new javax.swing.JButton();
        lblTemperatura = new javax.swing.JLabel();
        prgTemperatura = new javax.swing.JProgressBar();
        lblAviso = new javax.swing.JLabel();
        btnDisco = new javax.swing.JButton();
        btnRede = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(950, 510));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoWhite.png"))); // NOI18N
        lblLogo.setAlignmentX(-18.0F);
        lblLogo.setAlignmentY(-200.0F);

        lblTitulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblTitulo.setText("Temperatura");

        btnCPU.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnCPU.setText("CPU");
        btnCPU.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPUActionPerformed(evt);
            }
        });

        btnSO.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnSO.setText("Sistema");
        btnSO.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnSO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSOActionPerformed(evt);
            }
        });

        btnMemo.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnMemo.setText("Memória");
        btnMemo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnMemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMemoActionPerformed(evt);
            }
        });

        btnProcessos.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnProcessos.setText("Processos");
        btnProcessos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessosActionPerformed(evt);
            }
        });

        lblTemperatura.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblTemperatura.setText("Temperatura Atual: ");

        prgTemperatura.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        prgTemperatura.setForeground(new java.awt.Color(204, 0, 204));

        lblAviso.setText("    ");

        btnDisco.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnDisco.setText("Disco");
        btnDisco.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscoActionPerformed(evt);
            }
        });

        btnRede.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnRede.setText("Rede");
        btnRede.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnRede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(176, 176, 176))
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRede)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProcessos)
                            .addComponent(btnMemo)
                            .addComponent(btnCPU)
                            .addComponent(btnDisco)
                            .addComponent(btnSO))
                        .addGap(246, 246, 246)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(101, 101, 101))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblTemperatura)
                                        .addGap(139, 139, 139))
                                    .addComponent(prgTemperatura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(139, 139, 139))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lblTitulo)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(lblTemperatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prgTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(lblAviso)
                        .addGap(107, 107, 107))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btnCPU)
                        .addGap(18, 18, 18)
                        .addComponent(btnDisco)
                        .addGap(18, 18, 18)
                        .addComponent(btnSO)
                        .addGap(20, 20, 20)
                        .addComponent(btnMemo)
                        .addGap(18, 18, 18)
                        .addComponent(btnProcessos)
                        .addGap(18, 18, 18)
                        .addComponent(btnRede)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPUActionPerformed
        TelaCPU in = new TelaCPU();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnCPUActionPerformed

    private void btnSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSOActionPerformed
        TelaSO in = new TelaSO();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnSOActionPerformed

    private void btnMemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemoActionPerformed
        TelaMemoria in = new TelaMemoria();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnMemoActionPerformed

    private void btnProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessosActionPerformed
        TelaProcessos in = new TelaProcessos();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnProcessosActionPerformed

    private void btnDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscoActionPerformed
        TelaDisco in = new TelaDisco();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnDiscoActionPerformed

    private void btnRedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedeActionPerformed
        TelaRede in = new TelaRede();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnRedeActionPerformed

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
            java.util.logging.Logger.getLogger(TelaTemperatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaTemperatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaTemperatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaTemperatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaTemperatura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCPU;
    private javax.swing.JButton btnDisco;
    private javax.swing.JButton btnMemo;
    private javax.swing.JButton btnProcessos;
    private javax.swing.JButton btnRede;
    private javax.swing.JButton btnSO;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTemperatura;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JProgressBar prgTemperatura;
    // End of variables declaration//GEN-END:variables
}
