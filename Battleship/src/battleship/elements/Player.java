/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.elements;

import java.util.ArrayList;

/**
 *
 * @author rfmrm
 */
public class Player {
    public enum Kind{
        Patrol_Boat,
        Destroyer,
        Aircraft_Carrier;
    }
    private Board boardShips, boardAttacks;
    private int[][] location;
    private String name;
    private ArrayList<Kind> shipsAlive;
    private Patrol_Boat patBt;
    private Destroyer dest;
    private Aircraft_Carrier airCar;
    public int[][] getLocation() {
        return location;
    }

    public void setLocation(int[][] location, Ship shp) {
        this.location = location;
        int start, end;
                
        if (location[0][0] == location[1][0]){
            if (location[0][1] > location[1][1]){
                start = location[1][1];
                end = location[0][1];
            } else {
                start = location[0][1];
                end = location[1][1];
            }
        } else {
            if (location[0][0] > location[1][0]){
                start = location[1][0];
                end = location[0][0];
            } else {
                start = location[0][0];
                end = location[1][0];
            }
        }
        int spotIndex = 0;
        for (int i = start; i <= end; i++) {
            if (location[0][0] == location[1][0]){
                boardShips.setPlaces(i, location[0][0], 'S');
                shp.setSpotX(location[0][0], spotIndex);
                shp.setSpotY(i, spotIndex);
            } else {
                boardShips.setPlaces(location[0][1], i, 'S');
                shp.setSpotX(i, spotIndex);
                shp.setSpotY(location[0][1], spotIndex);
            }
            spotIndex++;
        }
        
        if (shp instanceof Patrol_Boat){
            patBt = (Patrol_Boat)shp;
        } else if (shp instanceof Destroyer){
            dest = (Destroyer)shp;
        } else {
            airCar = (Aircraft_Carrier)shp;
        }
    }
    
    public Player(String name){
        boardShips = new Board();
        boardAttacks = new Board();
        setName(name);
        shipsAlive = new ArrayList<>();
        shipsAlive.add(Kind.Patrol_Boat);
        shipsAlive.add(Kind.Destroyer);
        shipsAlive.add(Kind.Aircraft_Carrier);
    }

    public ArrayList<Kind> getShipsAlive() {
        return shipsAlive;
    }

    public void setShipsAlive(Kind shipDestroyed) {
        shipsAlive.remove(shipDestroyed);
    }
    
    public int getNumOfShips() {
        return shipsAlive.size();
    }
    
    public Ship getShip(Kind knd){
        switch (knd) {
            case Patrol_Boat:
                return patBt;
            case Destroyer:
                return dest;
            default:
                return airCar;
        }
    }
    
    public Ship findShip(int y, int x){
        if (coordenatesInShip(patBt, x, y)){
            return patBt;
        } else if (coordenatesInShip(dest, x, y)){
            return dest;
        } else {
            return airCar;
        }
    }
    
    private boolean coordenatesInShip(Ship shp, int x, int y){
        Spot spts[] = shp.getSpots();
        boolean find = false;
        for (int i = 0; i < shp.getSize();i++){
            if (spts[i].getxPosition() == x && spts[i].getyPosition() == y){
                find = true;
            }
        }
        return find;
    }
    
    public void showBoard(boolean boardShip){
        System.out.println("\n" + getName() + " board");
        if (boardShip == true){
            boardShips.drawBoard();
        } else {
            boardAttacks.drawBoard();
        }
        
    }

    public Board getBoard(boolean boardShip) {
        if (boardShip == true){
            return boardShips;
        } else {
            return boardAttacks;
        }
    }

    public void setBoard(Board board, boolean boardShip) {
        if (boardShip == true){
            this.boardShips = board;;
        } else {
            this.boardAttacks = board;
        }
    }
    
    public boolean occupied(int row, int column){
        //Devuelve el valor de la condición
        return boardShips.getPlaces(row, column) != 'O';
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
