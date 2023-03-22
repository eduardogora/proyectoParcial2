/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import battleship.elements.Destroyer;
import battleship.elements.Patrol_Boat;
import battleship.elements.Player;
import battleship.elements.Player.Kind;
import battleship.elements.Ship;
import java.io.IOException;

/**
 *
 * @author rfmrm
 */
public class Battleship {
    private Player ply1, ply2;
    private Ship shp;
    private int turn;
    
    public Battleship(){
        ply1 = new Player("Player 1");
        ply2 = new Player("Player 2");
        turn = 0;
    }
    
    public int getTurn() {
        return turn;
    }

    public void setTurn() {
        turn++;
    }
    
    public Player getPly1() {
        return ply1;
    }

    public void setPly1(Player ply1) {
        this.ply1 = ply1;
    }

    public Player getPly2() {
        return ply2;
    }

    public void setPly2(Player ply2) {
        this.ply2 = ply2;
    }

    public Ship getShp() {
        return shp;
    }

    public void setShp(Ship shp) {
        this.shp = shp;
    }
    
    public char attack(Player attkPly, Player recPly, Ship shp, int row, int column) throws IOException{
        char result, symbol;
        int attack = shp.getAttack();
        boolean destroyedShip = false;
        
        switch (recPly.getBoard(true).getPlaces(row, column)){
            case 'S': //Spot of a ship
                //You find a spot of a ship!");
                recPly.findShip(row, column).setLifeByAttack(row, column, attack);
                if (recPly.findShip(row, column).getLife() == 0){
                    //You destroyed all the ship!")
                    destroyedShip = true;
                    symbol = 'X';
                    result = 'X';
                } else if (recPly.findShip(row, column).getSpotDefense(row, column) <= 0){
                    //You destroyed the spot!");
                    symbol = 'X';
                    result = 'x';
                } else {
                    //You damaged the spot!");
                    symbol = 'P';
                    result = 'P';
                }
                break;
            case 'P': //Spot of a ship partial damaged
                recPly.findShip(row, column).setLifeByAttack(row, column, attack);
                if (recPly.findShip(row, column).getLife() == 0){
                    //You destroyed all the ship!");
                    destroyedShip = true;
                    symbol = 'X';
                    result = 'X';
                } else if (recPly.findShip(row, column).getSpotDefense(row, column) <= 0){
                    //You destroyed a spot of a ship!");
                    symbol = 'X';
                    result = 'x';
                } else {
                    //System.out.println("\nYou damaged again a spot of a ship!");
                    symbol = 'P';
                    result = 'P';
                }
                break;
            default: //'O' space with nothing  -> change to 'H'
                //Attack failed");
                symbol = 'H';
                result = 'H';
                break;
        }
        
        attkPly.getBoard(false).setPlaces(row, column, symbol);
        recPly.getBoard(true).setPlaces(row, column, symbol);
        
        if (destroyedShip == true){
            Kind kind;
            if (recPly.findShip(row, column) instanceof Patrol_Boat){
                kind = Kind.Patrol_Boat;
            } else if (recPly.findShip(row, column) instanceof Destroyer){
                kind = Kind.Destroyer;
            } else {
                kind = Kind.Aircraft_Carrier;
            }
            
            recPly.setShipsAlive(kind);
        }
        
        return result;
    }
    
    public int playerWon(){
        if (ply1.getNumOfShips()== 0){
            //Player 2 has won
            return 2;
        } else if (ply2.getNumOfShips()== 0){
            //Player 1 has won
            return 1;
        } else {
            return 0;
        }
    }
    
    public int[][] endPositions(int x, int y, Player ply, Ship shp) throws IOException{
        int  xEnd, yEnd, cont = 0, positions[][] = new int[4][2], spaces;
        
        for(int i = 0; i < 4; i++){
            positions[i][0] = -1;
            positions[i][1] = -1;
        }
        
        if (shp instanceof Patrol_Boat){
            spaces = 1;
        } else if (shp instanceof Destroyer){
            spaces = 2; 
        } else {
            spaces = 4;
        }
                
        yEnd = y;
        //Right
        xEnd = x+spaces;
        if (validPosition(xEnd, yEnd, ply) == true && validSpaces(x, y, xEnd, yEnd, ply) == true){
            positions[cont][0] = xEnd;
            positions[cont][1] = yEnd;
            cont++;
        }
        
        //Left
        xEnd = x-spaces;
        if (validPosition(xEnd, yEnd, ply) == true && validSpaces(x, y, xEnd, yEnd, ply) == true){
            positions[cont][0] = xEnd;
            positions[cont][1] = yEnd;
            cont++;
        }
        
        xEnd = x;
        //Up
        yEnd = y-spaces;
        if (validPosition(xEnd, yEnd, ply) == true && validSpaces(x, y, xEnd, yEnd, ply) == true){
            positions[cont][0] = xEnd;
            positions[cont][1] = yEnd;
            cont++;
        }
        
        //Down
        yEnd = y+spaces;
        if (validPosition(xEnd, yEnd, ply) == true && validSpaces(x, y, xEnd, yEnd, ply) == true){
            positions[cont][0] = xEnd;
            positions[cont][1] = yEnd;
            cont++;
        }
        
        return positions;
    }
    
    public boolean validSpaces(int x, int y, int xEnd, int yEnd, Player ply){
        int start, end;
        boolean error = false;
        
        if (x == xEnd){
            if (y > yEnd){
                start = yEnd+1;
                end = y;
            } else {
                start = y+1;
                end = yEnd;
            } 
        } else {
            if (x > xEnd){
                start = xEnd+1;
                end = x;
            } else {
                start = x+1;
                end = xEnd;
            }
        }
        
        for (int i = start; i < end; i++) {
            if (x == xEnd){
                if (ply.occupied(i, x) == true){
                    error = true;
                }
            } else {
                if (ply.occupied(y, i) == true){
                    error = true;
                }
            }
        }
        return !error;
    }
    
    public boolean validPosition(int x,int y, Player ply){
        return outOfLimitColumn(x) == false && outOfLimitRow(y) == false && ply.occupied(y, x) == false;
    }
    
    private boolean outOfLimitRow(int row){
        //Devuelve el valor de la condición
        return row < 0 || row > 10;
    }
    
    private boolean outOfLimitColumn(int column){
        //Devuelve el valor de la condición
        return column < 0|| column > 7;
    }
}
