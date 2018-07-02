/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Eyleen
 */
public class SupportSpaceship extends Spaceship {

    private Image image;

    public SupportSpaceship() {
        super();
        this.image = null;
    }

    public SupportSpaceship(int vida, int x, int y, String type) {
        super(vida, x, y, type);
        this.image = new ImageIcon(("src/Assets/support.png")).getImage();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
