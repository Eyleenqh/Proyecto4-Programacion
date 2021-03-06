/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Player;
import javax.swing.JOptionPane;

/**
 *
 * @author Steven
 */
public class MainWindow extends javax.swing.JFrame {
    private BackgroundPanel background;
    private String player;
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.background = new BackgroundPanel();
        this.getContentPane().add(this.background);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        bntStart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        topMenu = new javax.swing.JMenu();
        menuInstructions = new javax.swing.JMenuItem();
        menuTop = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spaceship War");
        setResizable(false);

        bntStart.setText("Start the game!");
        bntStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntStartActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username:");

        topMenu.setText("Options");

        menuInstructions.setText("Instructions");
        menuInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInstructionsActionPerformed(evt);
            }
        });
        topMenu.add(menuInstructions);

        menuTop.setText("Top Scores");
        topMenu.add(menuTop);

        jMenuBar1.add(topMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntStart)
                .addGap(337, 337, 337))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(259, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(bntStart)
                .addGap(127, 127, 127))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntStartActionPerformed
        if (this.txtUser.getText().isEmpty() || (this.txtUser.getText().length() <= 2)) {
            JOptionPane.showMessageDialog(this, "Please insert a valid Username.");
        } else {
            this.player = this.txtUser.getText();
            GameOptions go = new GameOptions(this.player);
            go.setVisible(true);
        }
    }//GEN-LAST:event_bntStartActionPerformed

    private void menuInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInstructionsActionPerformed
        InstructionsWindow iw = new InstructionsWindow();
        iw.setVisible(true);
    }//GEN-LAST:event_menuInstructionsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuInstructions;
    private javax.swing.JMenuItem menuTop;
    private javax.swing.JMenu topMenu;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
