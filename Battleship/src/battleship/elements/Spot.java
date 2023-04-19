/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.elements;


public class Spot {
    private int xPosition;
    private int yPosition;
    private int defense;
    
    public Spot(){
        setxPosition(-1);
        setyPosition(-1);
        setDefense(-1);
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public void setDefenseByAttack(int attack){
        defense -= attack;
        if (defense < 0){
            defense = 0;
        }
    }
}
