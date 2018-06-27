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
public class NaveMadre extends Nave {

    private Image ataque;
    private Image image;

    public NaveMadre() {
        super();
        this.ataque = null;
        this.image = null;
    }

    public NaveMadre(Image ataque, Image image, int vida, int x, int y) {
        super(vida, x, y);
        this.ataque = ataque;
        this.image = image;
    }

    public Image getAtaque() {
        return ataque;
    }

    public void setAtaque(Image ataque) {
        this.ataque = ataque;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
