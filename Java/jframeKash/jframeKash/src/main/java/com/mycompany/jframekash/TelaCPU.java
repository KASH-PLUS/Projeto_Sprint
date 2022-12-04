/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.jframekash;

import banco.TbUsuario;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class TelaCPU extends javax.swing.JFrame {

    Looca looca = new Looca();
    Processador processador = looca.getProcessador();
    ImageIcon img = new ImageIcon("src/main/resources/logoWhite.png");
    TbUsuario usuario = new TbUsuario();

    /**
     * Creates new form TelaCPU
     */
    public TelaCPU() {
        initComponents();
        lblFabricante.setText(lblFabricante.getText() + processador.getFabricante());
        lblNome.setText(lblNome.getText() + processador.getNome());
        lblID.setText(lblID.getText() + processador.getId());
        lblIdentificador.setText(lblIdentificador.getText() + processador.getIdentificador());
        lblMicroarquitetura.setText(lblMicroarquitetura.getText() + processador.getMicroarquitetura());
        lblFreq.setText(lblFreq.getText() + processador.getFrequencia().toString());
        lblNumPacotes.setText(lblNumPacotes.getText() + processador.getNumeroPacotesFisicos().toString());
        lblNumCPUsFisicas.setText(lblNumCPUsFisicas.getText() + processador.getNumeroCpusFisicas().toString());
        lblNumCPUsLogicas.setText(lblNumCPUsLogicas.getText() + processador.getNumeroCpusLogicas().toString());
        lblUso.setText(lblUso.getText() + processador.getUso().toString());

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
        btnSO = new javax.swing.JButton();
        btnDisco = new javax.swing.JButton();
        btnMemo = new javax.swing.JButton();
        lblFabricante = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblIdentificador = new javax.swing.JLabel();
        lblMicroarquitetura = new javax.swing.JLabel();
        lblFreq = new javax.swing.JLabel();
        lblNumPacotes = new javax.swing.JLabel();
        lblNumCPUsFisicas = new javax.swing.JLabel();
        lblNumCPUsLogicas = new javax.swing.JLabel();
        lblUso = new javax.swing.JLabel();
        btnProcessos = new javax.swing.JButton();
        btnTemp = new javax.swing.JButton();
        btnRede = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoWhite.png"))); // NOI18N
        lblLogo.setAlignmentX(-18.0F);
        lblLogo.setAlignmentY(-200.0F);

        lblTitulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblTitulo.setText("Processador");

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

        btnDisco.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnDisco.setText("Disco");
        btnDisco.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscoActionPerformed(evt);
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

        lblFabricante.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblFabricante.setText("Fabricante: ");

        lblNome.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblNome.setText("Nome: ");

        lblID.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblID.setText("ID: ");

        lblIdentificador.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblIdentificador.setText("Identificador: ");

        lblMicroarquitetura.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblMicroarquitetura.setText("Microarquitetura: ");

        lblFreq.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblFreq.setText("Frequência: ");

        lblNumPacotes.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblNumPacotes.setText("Pacotes Físicos: ");

        lblNumCPUsFisicas.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblNumCPUsFisicas.setText("CPUs Físicas: ");

        lblNumCPUsLogicas.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblNumCPUsLogicas.setText("CPUs Lógicas: ");

        lblUso.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblUso.setText("Em Uso: ");

        btnProcessos.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnProcessos.setText("Processos");
        btnProcessos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessosActionPerformed(evt);
            }
        });

        btnTemp.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        btnTemp.setText("Temperatura");
        btnTemp.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTempActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSO)
                            .addComponent(btnDisco)
                            .addComponent(btnMemo)
                            .addComponent(btnProcessos)
                            .addComponent(btnTemp)
                            .addComponent(btnRede))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(lblTitulo)
                        .addContainerGap(296, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblIdentificador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18))
                                            .addComponent(lblFabricante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(41, 41, 41))
                                    .addComponent(lblMicroarquitetura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblFreq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(40, 40, 40))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblNumCPUsFisicas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(32, 32, 32))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lblNumPacotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblNumCPUsLogicas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblUso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(13, 13, 13)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblTitulo)
                        .addGap(18, 18, 18)
                        .addComponent(lblFabricante)
                        .addGap(12, 12, 12)
                        .addComponent(lblNome)
                        .addGap(12, 12, 12)
                        .addComponent(lblID)
                        .addGap(12, 12, 12)
                        .addComponent(lblIdentificador)
                        .addGap(13, 13, 13)
                        .addComponent(lblMicroarquitetura)
                        .addGap(14, 14, 14)
                        .addComponent(lblFreq)
                        .addGap(12, 12, 12)
                        .addComponent(lblNumPacotes)
                        .addGap(14, 14, 14)
                        .addComponent(lblNumCPUsFisicas)
                        .addGap(12, 12, 12)
                        .addComponent(lblNumCPUsLogicas)
                        .addGap(17, 17, 17)
                        .addComponent(lblUso))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(btnSO)
                        .addGap(18, 18, 18)
                        .addComponent(btnDisco)
                        .addGap(18, 18, 18)
                        .addComponent(btnMemo)
                        .addGap(18, 18, 18)
                        .addComponent(btnProcessos)
                        .addGap(18, 18, 18)
                        .addComponent(btnTemp)
                        .addGap(18, 18, 18)
                        .addComponent(btnRede)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemoActionPerformed
        TelaMemoria in = new TelaMemoria();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnMemoActionPerformed

    private void btnSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSOActionPerformed
        TelaSO in = new TelaSO();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnSOActionPerformed

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

    private void btnTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTempActionPerformed
        TelaTemperatura in = new TelaTemperatura();
        in.setLocationRelativeTo(null);
        in.setVisible(true);
        in.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_btnTempActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCPU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDisco;
    private javax.swing.JButton btnMemo;
    private javax.swing.JButton btnProcessos;
    private javax.swing.JButton btnRede;
    private javax.swing.JButton btnSO;
    private javax.swing.JButton btnTemp;
    private javax.swing.JLabel lblFabricante;
    private javax.swing.JLabel lblFreq;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblIdentificador;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMicroarquitetura;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumCPUsFisicas;
    private javax.swing.JLabel lblNumCPUsLogicas;
    private javax.swing.JLabel lblNumPacotes;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUso;
    // End of variables declaration//GEN-END:variables
}
