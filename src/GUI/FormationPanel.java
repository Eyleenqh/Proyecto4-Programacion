/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.MotherSpaceship;
import Domain.Player;
import Domain.SupportSpaceship;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLayeredPane;

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
    private Player player;

    public FormationPanel(int r, int c, int size, Player player) {
        this.player = player;
        
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
        g.setColor(Color.WHITE);
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
        for(int i = 0; i<this.player.getSpaceships().size(); i++){
            g.drawImage(this.player.getSpaceships().get(i).getImage(), this.player.getSpaceships().get(i).getX()*this.modeRecSize, 
                    this.player.getSpaceships().get(i).getY()*this.modeRecSize, this.modeRecSize, this.modeRecSize, this);
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
                this.player.getSpaceships().add(new MotherSpaceship(2, c, r, this.spaceship));
                this.motherQuantity--;
            }
        }
        if (this.spaceship.equals("Support")){
            if(this.supportQuantity != 0){
                this.player.getSpaceships().add(new SupportSpaceship(1, c, r, this.spaceship));
                this.supportQuantity--;
            }
        }
        repaint();
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
