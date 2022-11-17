/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.jframekash;
//import javax.swing.JFrame;

import banco.TbMaquina;
import banco.TbMaquinaCrud;
import banco.TbUsuario;
import banco.TbUsuarioCrud;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Login extends javax.swing.JFrame {
    public Login() {
        initComponents();
        ImageIcon img = new ImageIcon("src/main/resources/logoWhite.png");
        img.setImage(img.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), 1));
        lblLogo.setIcon(img);
    }
    TelaConfirmacaoCaptura in = new TelaConfirmacaoCaptura();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogin = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblAlerta = new javax.swing.JLabel();
        lblErro = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(6, 35, 53));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 381, 290));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogin.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblLogin.setText("SerialNumber");
        lblLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 21));

        txtSerial.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtSerial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerialActionPerformed(evt);
            }
        });
        getContentPane().add(txtSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 97, -1));

        btnEntrar.setBackground(new java.awt.Color(6, 35, 53));
        btnEntrar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnEntrar.setText("  Entrar  ");
        btnEntrar.setBorder(null);
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 215, -1, -1));

        lblLogo.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoWhite.png"))); // NOI18N
        lblLogo.setText("Kash+");
        getContentPane().add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, 45));
        getContentPane().add(lblAlerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1121, 6, -1, -1));

        lblErro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblErro.setText("   ");
        getContentPane().add(lblErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 251, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblTitulo.setText("LOGIN");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        TbMaquinaCrud maquinaCrud = new TbMaquinaCrud();
        TbMaquina validacao = new TbMaquina();
        String serialNumber = txtSerial.getText();
        
        validacao = maquinaCrud.selecionar(serialNumber);
        
        
        if (validacao != null) {
            System.out.println("Deu select");
            if (serialNumber.equalsIgnoreCase(validacao.getSerialNumber())) {
                in.setLocationRelativeTo(null);
                in.setVisible(true);
                in.setResizable(false);
                in.setSerialNumber(serialNumber);
                this.dispose();
            }else {
                lblErro.setText("ERRO - Login/Senha inválidos");
                System.out.println("Erro no select");
            }
        }
        else {
            lblErro.setText("Desculpe, mas não encontramos seu cadastro");
            System.out.println("Erro no cadastro");
        }
        
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtSerialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerialActionPerformed
    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel lblAlerta;
    private javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtSerial;
    // End of variables declaration//GEN-END:variables
}
