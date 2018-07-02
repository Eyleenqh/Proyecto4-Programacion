/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class Player {
    //atributos
    private String name;
    private ArrayList<Spaceship> spaceships;

    //constructor
    public Player() {
    }
    //constructor
    public Player(String name) {
        this.name = name;
        this.spaceships = new ArrayList<Spaceship>();
    }

    //metodos accesores
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Spaceship> getSpaceships() {
        return spaceships;
    }

    public void setSpaceships(ArrayList<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }
    
    //metodo para identificar si se acerto a una nave o no
    public Spaceship attackedSpaceship(int x, int y){
        //se recorre el arraylist para buscar si se le dio a una nave del jugador o no
        for (int i = 0; i < this.spaceships.size(); i++) {
            if (this.spaceships.get(i).getX() == x) {
                if (this.spaceships.get(i).getY() == y) {
                    return this.spaceships.get(i);
                }
            }
        }
        return null;
    }
}
