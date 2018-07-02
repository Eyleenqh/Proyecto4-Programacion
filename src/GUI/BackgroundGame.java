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
public class BackgroundGame extends JPanel{

    private Image background;

    public BackgroundGame() {
        this.background = new ImageIcon(("src/Assets/panelBackground.png")).getImage();
        this.setLayout(null);
        this.setSize(1090, 661);
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);
    }
}
