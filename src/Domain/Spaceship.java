/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.awt.Image;

/**
 *
 * @author Eyleen
 */
public class Spaceship {

    private int vida;
    private int x;
    private int y;
    private Image image;
    private String type;

    public Spaceship() {
        this.vida = 0;
        this.x = 0;
        this.y = 0;
        this.type = "";
    }

    public Spaceship(int vida, int x, int y, String type) {
        this.vida = vida;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
