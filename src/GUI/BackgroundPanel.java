/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Steven
 */
public class BackgroundPanel extends JPanel{
    private Image IMG;

    public BackgroundPanel() {
        this.IMG = new ImageIcon(("src/Assets/back.jpg")).getImage();
        this.setLayout(null);
        this.setSize(800, 500);
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(IMG, 0, 0, getWidth(), getHeight(), this);
    }
    
}
