public class Main {

    public static void main(String[] args) {
        GameManager gm = new GameManager();



    }
}


/*
 * int[] coords = new int[]{1, 5};
 * GameBoard test = new GameBoard(10, 10);
 * <p>
 * test.BoardToString(test.makeNewBoard());
 * <p>
 * ShipBuilder werft = new ShipBuilder();
 * BoardManager manager = new BoardManager();
 * Boat lel = werft.build_a_ship(2);
 * BoatPart[][] platziert = manager.placeBoat(coords, "North", lel, test.makeNewBoard());
 * <p>
 * <p>
 * System.out.println(platziert[0][5].typeofship);
 * platziert[0][5].setdestroyed();
 * platziert[1][5].setdestroyed();
 * System.out.println("Is the submarine destroyed?" + lel.isDestroyed());
 * <p>
 * test.BoardToString(platziert);
 * //System.out.println(manager.canPlaceBoat(coords, "North", 2, test.makeNewBoard()));
 * Compare check test (BoardManager)
 * int[] coord = new int[]{5,5};
 * ShipBuilder bau = new ShipBuilder();
 * BoardManager test = new BoardManager();
 * GameBoard test1 = new GameBoard(10,10);
 * BoatPart[][] t1 = test1.makeNewBoard();
 * BoatPart[][] t2 = test1.makeNewBoard();
 * t2 = test.placeBoat(coord,"North", bau.build_a_ship(2), t2);
 * System.out.println(test.BoardToString(t2) + "\n \n \n" + test.BoardToString(t1));
 * System.out.println("Wanted output:" + test.areBoardsEqual(t1,t2));
 * <p>
 * <p>
 * <p>
 * <p>
 * Test The CPU Board generation, print the generated Board
 * <p>
 * <p>
 * ShipBuilder werft = new ShipBuilder();
 * GameBoard cpubord = new GameBoard(10,10);
 * <p>
 * BoatPart[][] brett = cpubord.makeNewBoard();
 * <p>
 * Boat[] boote = new Boat[]{werft.build_a_ship(3), werft.build_a_ship(5)};
 * <p>
 * Computer robo = new Computer(boote, brett);
 * robo.placeOwnBoats();
 * String lole = cpubord.BoardToString(robo.getOwnArea());
 */

/* Compare check test (BoardManager)
 *  int[] coord = new int[]{5,5};
 *         ShipBuilder bau = new ShipBuilder();
 *         BoardManager test = new BoardManager();
 *         GameBoard test1 = new GameBoard(10,10);
 *         BoatPart[][] t1 = test1.makeNewBoard();
 *         BoatPart[][] t2 = test1.makeNewBoard();
 *         t2 = test.placeBoat(coord,"North", bau.build_a_ship(2), t2);
 *         System.out.println(test.BoardToString(t2) + "\n \n \n" + test.BoardToString(t1));
 *         System.out.println("Wanted output:" + test.areBoardsEqual(t1,t2));
 *
 *
 *
 *
 */


/* Test The CPU Board generation, print the generated Board
 *

 ShipBuilder werft = new ShipBuilder();
 GameBoard cpubord = new GameBoard(10,10);

 BoatPart[][] brett = cpubord.makeNewBoard();

 Boat[] boote = new Boat[]{werft.build_a_ship(3), werft.build_a_ship(5)};

 Computer robo = new Computer(boote, brett);
 robo.placeOwnBoats();
 String lole = cpubord.BoardToString(robo.getOwnArea());
 *
 *
 */