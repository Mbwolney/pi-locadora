/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaigo.locadora.view;

import br.com.senaigo.locadora.utils.PosicaoFormulario;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author pfellype
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    PosicaoFormulario form = new PosicaoFormulario();

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBase = new javax.swing.JPanel();
        jDesktopPanePainelPrincipal = new javax.swing.JDesktopPane();
        jMenuBarMenuPrincipal = new javax.swing.JMenuBar();
        jMenuFuncionario = new javax.swing.JMenu();
        jMenuCliente = new javax.swing.JMenu();
        jMenuItemPessoaFisica = new javax.swing.JMenuItem();
        jMenuPessoaJuridica = new javax.swing.JMenuItem();
        jMenuItemMotorista = new javax.swing.JMenuItem();
        jMenuVeiculo = new javax.swing.JMenu();
        jMenuItemMarca = new javax.swing.JMenuItem();
        jMenuModelo = new javax.swing.JMenuItem();
        jMenuCategoria = new javax.swing.JMenuItem();
        jMenuCarro = new javax.swing.JMenuItem();
        jMenuLacacao = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 400));
        setSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout jPanelBaseLayout = new javax.swing.GroupLayout(jPanelBase);
        jPanelBase.setLayout(jPanelBaseLayout);
        jPanelBaseLayout.setHorizontalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanePainelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
        );
        jPanelBaseLayout.setVerticalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanePainelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );

        jMenuBarMenuPrincipal.setBackground(new java.awt.Color(255, 204, 0));

        jMenuFuncionario.setForeground(new java.awt.Color(0, 0, 0));
        jMenuFuncionario.setIcon(new javax.swing.ImageIcon("arquivo/imagens/user_1.png"));
        jMenuFuncionario.setText("Funcionário");
        jMenuBarMenuPrincipal.add(jMenuFuncionario);

        jMenuCliente.setForeground(new java.awt.Color(0, 0, 0));
        jMenuCliente.setText("Cliente");

        jMenuItemPessoaFisica.setText("Pessoa Física");
        jMenuItemPessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPessoaFisicaActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemPessoaFisica);

        jMenuPessoaJuridica.setText("Pessoa Jurídica");
        jMenuPessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPessoaJuridicaActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuPessoaJuridica);

        jMenuItemMotorista.setText("Motorista");
        jMenuItemMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMotoristaActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemMotorista);

        jMenuBarMenuPrincipal.add(jMenuCliente);

        jMenuVeiculo.setForeground(new java.awt.Color(0, 0, 0));
        jMenuVeiculo.setText("Veículo");

        jMenuItemMarca.setText("Marca");
        jMenuItemMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMarcaActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuItemMarca);

        jMenuModelo.setText("Modelo");
        jMenuModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuModeloActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuModelo);

        jMenuCategoria.setText("Categoria");
        jMenuCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCategoriaActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuCategoria);

        jMenuCarro.setText("Carro");
        jMenuCarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCarroActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuCarro);

        jMenuBarMenuPrincipal.add(jMenuVeiculo);

        jMenuLacacao.setForeground(new java.awt.Color(0, 0, 0));
        jMenuLacacao.setText("Locação");
        jMenuBarMenuPrincipal.add(jMenuLacacao);

        setJMenuBar(jMenuBarMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMarcaActionPerformed
        // TODO add your handling code here:
        try {
//            jDesktopPanePainelPrincipal.removeAll();
//            TelaMarca marca = new TelaMarca();
//            jDesktopPanePainelPrincipal.add(marca);
//            marca.setVisible(true);
//            marca.setMaximum(true);
            TelaMarca tela = new TelaMarca();
            jDesktopPanePainelPrincipal.removeAll();
            form.abrirFormulario(tela, jDesktopPanePainelPrincipal);
            
        } catch (Exception erro) {
        }
    }//GEN-LAST:event_jMenuItemMarcaActionPerformed

    private void jMenuItemPessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPessoaFisicaActionPerformed
        // TODO add your handling code here:
        try {
            jDesktopPanePainelPrincipal.removeAll();
            TelaPessoaFisica pf = new TelaPessoaFisica();
            jDesktopPanePainelPrincipal.add(pf);
            pf.setVisible(true);
            pf.setMaximum(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItemPessoaFisicaActionPerformed

    private void jMenuPessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPessoaJuridicaActionPerformed
        // TODO add your handling code here:
        try {
            jDesktopPanePainelPrincipal.removeAll();
            TelaPessoaJuridica pj = new TelaPessoaJuridica();
            jDesktopPanePainelPrincipal.add(pj);
            pj.setVisible(true);
            pj.setMaximum(true);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jMenuPessoaJuridicaActionPerformed

    private void jMenuCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCategoriaActionPerformed
        // TODO add your handling code here:
        try {
            TelaCategoria tela = new TelaCategoria();
            jDesktopPanePainelPrincipal.removeAll();
            form.abrirFormulario(tela, jDesktopPanePainelPrincipal);
            
        } catch (Exception erro) {
        }
    }//GEN-LAST:event_jMenuCategoriaActionPerformed

    private void jMenuItemMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMotoristaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemMotoristaActionPerformed

    private void jMenuModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuModeloActionPerformed
        // TODO add your handling code here:
		try {
			TelaModelo tela = new TelaModelo();
			jDesktopPanePainelPrincipal.removeAll();
			form.abrirFormulario(tela, jDesktopPanePainelPrincipal);

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir tela de modelos. " + erro.getMessage());
		}
    }//GEN-LAST:event_jMenuModeloActionPerformed

    private void jMenuCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCarroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuCarroActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPanePainelPrincipal;
    private javax.swing.JMenuBar jMenuBarMenuPrincipal;
    private javax.swing.JMenuItem jMenuCarro;
    private javax.swing.JMenuItem jMenuCategoria;
    private javax.swing.JMenu jMenuCliente;
    private javax.swing.JMenu jMenuFuncionario;
    private javax.swing.JMenuItem jMenuItemMarca;
    private javax.swing.JMenuItem jMenuItemMotorista;
    private javax.swing.JMenuItem jMenuItemPessoaFisica;
    private javax.swing.JMenu jMenuLacacao;
    private javax.swing.JMenuItem jMenuModelo;
    private javax.swing.JMenuItem jMenuPessoaJuridica;
    private javax.swing.JMenu jMenuVeiculo;
    private javax.swing.JPanel jPanelBase;
    // End of variables declaration//GEN-END:variables
}
