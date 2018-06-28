/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.MotherSpaceship;
import Domain.Spaceship;
import Domain.SupportSpaceship;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Steven
 */
public class FormationPanel extends JLayeredPane implements MouseListener {

    private int[][] positionMatrix;
    private int size;
    private String spaceship;
    private int supportQuantity;
    private int motherQuantity;
    private int modeRecSize;
    Spaceship mother;
    Spaceship support;

    public FormationPanel(int r, int c, int size) {
        if (size == 3) {
            this.modeRecSize = 200;
            this.supportQuantity = 2;
        } else {
            this.modeRecSize = 120;
            this.supportQuantity = 4;
        }
        this.motherQuantity = 1;
        this.spaceship = "";
        this.positionMatrix = new int[r][c];
        for (int i = 0; i < this.positionMatrix.length; i++) {
            for (int j = 0; j < this.positionMatrix.length; j++) {
                this.positionMatrix[i][j] = 0;
            }
        }
        this.size = size;
        this.addMouseListener(this);
        this.setLayout(null);
        this.setSize(605, 605);
        this.setVisible(true);
    }

    void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        int x = 0;
        int y = 0;
        for (int i = 0; i < this.positionMatrix.length; i++) {
            for (int j = 0; j < this.positionMatrix.length; j++) {
                g.drawRect(x + i * this.modeRecSize, y + j * this.modeRecSize, this.modeRecSize, this.modeRecSize);
            }
        }
        paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 0; i < this.positionMatrix.length; i++) {
            for (int j = 0; j < this.positionMatrix.length; j++) {
                if (this.positionMatrix[j][i] == 7) {
                    g.drawImage(this.mother.getImage(), (this.mother.getX() / this.modeRecSize) * this.modeRecSize, (this.mother.getY() / this.modeRecSize) * this.modeRecSize,
                            this.modeRecSize, this.modeRecSize, this);
                }
                if(this.positionMatrix[j][i] == 9){
                    g.drawImage(this.support.getImage(), (this.support.getX() / this.modeRecSize) * this.modeRecSize, (this.support.getY() / this.modeRecSize) * this.modeRecSize,
                            this.modeRecSize, this.modeRecSize, this);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int r = 0;
        int c = 0;
        if (this.size == 3) {
            c = e.getX() / 200;
            r = e.getY() / 200;
        } else {
            c = e.getX() / 120;
            r = e.getY() / 120;
        }
        if (this.spaceship.equals("Mother")) {
            if (this.motherQuantity != 0) {
                this.positionMatrix[r][c] = 7;
                this.motherQuantity--;
                this.mother = new MotherSpaceship(2, x, y);
                repaint();
            }
        }
        if (this.spaceship.equals("Support")){
            if(this.supportQuantity != 0){
                this.positionMatrix[r][c] = 9;
                this.supportQuantity--;
                this.support = new SupportSpaceship(1, x, y);//se debe crear en un arraylist para que no se borre
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
