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
public class NaveGuerrera extends Nave {

    private Image image;

    public NaveGuerrera() {
        super();
        this.image = null;
    }

    public NaveGuerrera(Image image, int vida, int x, int y) {
        super(vida, x, y);
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
