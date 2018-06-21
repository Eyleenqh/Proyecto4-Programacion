/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Steven
 */
public class FormationPanel extends JPanel{
    private int[][] positionMatrix;
    
    public FormationPanel(int r, int c){
        this.positionMatrix = new int[r][c];
        for (int i = 0; i < this.positionMatrix.length; i++) {
            for (int j = 0; j < this.positionMatrix.length; j++) {
                this.positionMatrix[i][j] = 0;
            }
        }
        this.setLayout(null);
        this.setSize(605, 605);
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        int x = 0;
        int y = 0;
        for (int i = 0; i < this.positionMatrix.length; i++) {
            for (int j = 0; j < this.positionMatrix.length; j++) {
                g.drawRect(x + i * 200, y + j * 200, 200, 200);
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g
    ) {
        paint(g);
    }
}