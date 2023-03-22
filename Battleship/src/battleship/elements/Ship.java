/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.elements;

/**
 *
 * @author rfmrm
 */
public class Ship {
    protected int size;
    protected int defense;
    protected int attack;
    protected Spot[] spots;
    protected int life;
    protected int cooldown;
    protected boolean disabled;
    protected int turnDisabled;
    
    public Ship(int size, int defense, int attack, int life, int cooldown){
        setSize(size);
        setDefense(defense);
        setAttack(attack);
        setSpots();
        setSpotsDefense();
        setLife(life);
        setCooldown(cooldown);
        setDisabled(false);
        setTurnDisabled(-1);
    }
    
    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getTurnDisabled() {
        return turnDisabled;
    }

    public void setTurnDisabled(int turnDisabled) {
        this.turnDisabled = turnDisabled;
    }
    
    public Spot[] getSpots() {
        return spots;
    }

    public void setSpots() {
        spots = new Spot[getSize()];
        for (int i = 0; i < getSize();i++){
            spots[i] = new Spot();
        }
    }

    public int getSpotX(int i) {
        return spots[i].getxPosition();
    }

    public void setSpotX(int xPos, int i) {
        spots[i].setxPosition(xPos);
    }
    
    public int getSpotY(int i) {
        return spots[i].getyPosition();
    }

    public void setSpotY(int yPos, int i) {
        spots[i].setyPosition(yPos);
    }

    public void setSpotsDefense() {
        for (int i = 0; i < getSize(); i++){
            spots[i].setDefense(getDefense());
        }
    }
    
    public int getSpotDefense(int y, int x) {
        for (int i = 0; i < getSize();i++){
            if (spots[i].getxPosition() == x && spots[i].getyPosition() == y){
                return spots[i].getDefense();
            }
        }
        return -1;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDefense() {
        return defense;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    public void setLifeByAttack(int y, int x, int attack) {
        life = 0;
        for (int i = 0; i < getSize();i++){
            if (spots[i].getxPosition() == x && spots[i].getyPosition() == y){
                spots[i].setDefenseByAttack(attack);
                break;
            }
        }
        for (int i = 0; i < getSize();i++){
            life += spots[i].getDefense();
        }
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
