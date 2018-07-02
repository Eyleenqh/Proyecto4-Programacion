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
public class BackgroundInstructions extends JPanel{
    private Image background;

    public BackgroundInstructions() {
        this.background = new ImageIcon(("src/Assets/instructionsBackground.JPG")).getImage();
        this.setLayout(null);
        this.setSize(1360, 720);
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);
    }
}
