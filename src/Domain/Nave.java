/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Eyleen
 */
public class Nave {

    private int vida;
    private int x;
    private int y;

    public Nave() {
        this.vida = 0;
        this.x = 0;
        this.y = 0;
    }

    public Nave(int vida, int x, int y) {
        this.vida = vida;
        this.x = x;
        this.y = y;
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
}
