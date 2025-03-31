package Monopoly;

/**
 * The game board for a simulated game of Monopoly.
 * 
 * @author D.L. Bailey, SCE, Carleton University 
 * @version 1.21
 */
public class Board
{
    /** 
     * The number of squares in the board.  
     */
    private static final int SIZE = 40; 
    
    /** The squares on the board. */
    private Square[] squares;

    /** 
     * The location of the square where all players are located
     * at the start of the game.
     */
    private final int GO_SQUARE = 0;    

    /**
     * Constructs a new Board, populating it with squares.
     */
    public Board()
    {
        squares = new Square[SIZE];
        buildSquares();
    }

    /**
     * Populates the board with squares numbered 1, 2, 3, ..., SIZE.
     * The square numbered "n" is stored in squares[n-1].
     * Each square is named after the corresponding square on a real 
     * Monopoly board (Canadian edition). 
     * Each square has "type", which is either GO, LOT, INCOME_TAX, or EMPTY.
     */
    private void buildSquares()
    {
        int i = GO_SQUARE;
        squares[i] = new Square(Square.GO, "Go", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "Duckworth Street", i+1);
        squares[i].setPrice(new Money(60, 0));
        
        i++;
        squares[i] = new Square(Square.EMPTY, "Community Chest", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "Signal Hill", i+1);
        squares[i].setPrice(new Money(60, 0)); 
        
        i++;
        squares[i] = new Square(Square.INCOME_TAX, "Income Tax", i+1);
        
        i++;        
        squares[i] = new Square(Square.EMPTY, "CN Rail", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "Waterloo Row", i+1);
        squares[i].setPrice(new Money(100, 0));
        
        i++; 
        squares[i] = new Square(Square.EMPTY, "Chance", i+1);
        
        i++; 
        squares[i] = new Square(Square.LOT, "King Street", i+1);
        
        squares[i].setPrice(new Money(100, 0));        
        i++;        
        squares[i] = new Square(Square.LOT, "Queen Street", i+1);
        squares[i].setPrice(new Money(120, 0)); 
        
        i++;        
        squares[i] = new Square(Square.EMPTY, "Jail", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "University Avenue", i+1);
        squares[i].setPrice(new Money(140, 0));
        
        i++;
        squares[i] = new Square(Square.EMPTY, "Telephone Company", i+1);
        
        i++;     
        squares[i] = new Square(Square.LOT, "Barrington Street", i+1);
        squares[i].setPrice(new Money(140, 0));
        
        i++;
        squares[i] = new Square(Square.LOT, "Spring Garden Road", i+1);
        squares[i].setPrice(new Money(160, 0)); 
        
        i++; 
        squares[i] = new Square(Square.EMPTY, "Ontario Northland", i+1);
        
        i++;       
        squares[i] = new Square(Square.LOT, "Rue St-Jean", i+1);
        squares[i].setPrice(new Money(160, 0));
        
        i++; 
        squares[i] = new Square(Square.EMPTY, "Community Chest", i+1);
        
        i++;        
        squares[i] = new Square(Square.LOT, "Rue Ste-Catherine", i+1);
        squares[i].setPrice(new Money(180, 0));  
        
        i++;
        squares[i] = new Square(Square.LOT, "Rue St-Jacques", i+1);
        squares[i].setPrice(new Money(200, 0)); 
        
        i++;
        squares[i] = new Square(Square.EMPTY, "Free Parking", i+1);
        
        i++; 
        squares[i] = new Square(Square.LOT, "Sussex Drive", i+1);
        squares[i].setPrice(new Money(220, 0)); 
        
        i++;       
        squares[i] = new Square(Square.EMPTY, "Chance", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "Yonge Street", i+1);
        squares[i].setPrice(new Money(220, 0));  
        
        i++; 
        squares[i] = new Square(Square.LOT, "Bay Street", i+1);
        squares[i].setPrice(new Money(240, 0)); 
        
        i++;        
        squares[i] = new Square(Square.EMPTY, "CP Rail", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "Portage & Main", i+1);
        squares[i].setPrice(new Money(260, 0));
        
        i++;
        squares[i] = new Square(Square.LOT, "Victoria Avenue", i+1);
        squares[i].setPrice(new Money(260, 0)); 
        
        i++; 
        squares[i] = new Square(Square.EMPTY, "Gas Company", i+1);
        
        i++;       
        squares[i] = new Square(Square.LOT, "Albert Street", i+1);
        squares[i].setPrice(new Money(280, 0));   
        
        i++;        
        squares[i] = new Square(Square.EMPTY, "Go to Jail", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "Jasper Avenue", i+1);
        squares[i].setPrice(new Money(300, 0));  
        
        i++;
        squares[i] = new Square(Square.LOT, "St. Albert Trail", i+1);
        squares[i].setPrice(new Money(300, 0));  
        
        i++; 
        squares[i] = new Square(Square.EMPTY, "Community Chest", i+1);
        
        i++;        
        squares[i] = new Square(Square.LOT, "MacLeod Trail", i+1);
        squares[i].setPrice(new Money(320, 0)); 
        
        i++;
        squares[i] = new Square(Square.EMPTY, "B.C. Rail", i+1);
        
        i++;
        squares[i] = new Square(Square.EMPTY, "Chance", i+1);
        
        i++; 
        squares[i] = new Square(Square.LOT, "Granville Street", i+1);
        squares[i].setPrice(new Money(350, 0)); 
        
        i++;       
        squares[i] = new Square(Square.EMPTY, "Luxury Tax", i+1);
        
        i++;
        squares[i] = new Square(Square.LOT, "Douglas Street", i+1); 
        squares[i].setPrice(new Money(400, 0));            
    }
 
    /**
     * Returns the square located "distance" squares after aSquare.
     */
    public Square nextSquare(Square aSquare, int distance)
    {
        /* Assumes that squares are numbered consecutively, starting
         * from 1, and that the square numbered "n" is stored
         * in squares[n-1]. See buildSquares().
         */
        int i = (aSquare.number() - 1 + distance) % squares.length;
        return squares[i]; 
    } 
 
    /**
     * Returns the square where all players are located at the start of the game.
     */
    public Square startingSquare()
    {
        return squares[GO_SQUARE];
    } 
    
    /*
     * A bit of code to help with interactive testing.
     */
    public void printBoard()
    {
        for (Square square : squares) {
            System.out.print("Square " + square.number() + ": ");
            System.out.println(square.description());
        }
    }      
}

   