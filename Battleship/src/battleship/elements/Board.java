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
public class Board {
    private final char Places [][] = new char [11][8];
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BLACK = "\u001B[30m";
    
    public Board(){
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 8; j++){
                setPlaces(i, j, 'O');
            }
        }
    }

    public char getPlaces(int row, int column) {
        return Places[row][column];
    }

    public void setPlaces(int row, int column, char symbol) {
        Places[row][column] = symbol;
    }
    
    public void drawBoard(){
        for(int j = 1; j < 9; j++){
                System.out.print("\t" + j);
        }
        
        for(int i = 0; i < 11; i++){
            if (i != 0 ){
                System.out.println("\t" + ANSI_BLACK  + i);
            } else {
                System.out.println();
            }
            for(int j = 0; j < 8; j++){
                char symbol = getPlaces(i,j);
                switch (symbol) {
                    case 'O':
                        //Spot of a ship destroyed
                        System.out.print("\t"+ ANSI_BLUE  + getPlaces(i, j));
                        break;
                    case 'H':
                        //Space that has been hit
                        System.out.print("\t"+ ANSI_CYAN + getPlaces(i, j));
                        break;
                    case 'S':
                        //Spot of a ship
                        System.out.print("\t"+ ANSI_PURPLE  + getPlaces(i, j));
                        break;
                    case 'X':
                        //Destroyed
                        System.out.print("\t"+ ANSI_RED  + getPlaces(i, j));
                        break;
                    case 'P':
                        ////Spot of a ship partial damaged
                        System.out.print("\t"+ ANSI_YELLOW  + getPlaces(i, j));
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println("\t" + ANSI_BLACK + 11);
    }
}
