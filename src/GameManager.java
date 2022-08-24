// The Game Manager is the Class that manages the actual flow of the game. It owns and manages instances of pretty much all other classes under shortened Names (cpu, ply, gb, sb, etc..)
//

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameManager {

    public Computer cpu;
    public GameBoard gb;
    public ShipBuilder sb;
    public BoardManager bm;

    public Player ply;

    public UserResponseField usr;

    public Scanner scanner;
    public Scanner numReader;

    public final int[] whichBoats = {2}; //Specify which boat types should be in the game.Default : 1x2, 1x3, 1x4, 1x5
    public final int boardLength = 10;  //Size of the board x-axis
    public final int boardWidth = 10; //Size of the board y-axis

    public final boolean colourMode = true;  //Switch between using ASCII escape colours or not

    GameManager() {
        scanner = new Scanner(System.in);
        numReader = new Scanner(System.in);
        bm = new BoardManager();
        sb = new ShipBuilder();
        gb = new GameBoard(boardLength, boardWidth);
        cpu = new Computer(initiateBoats(), gb.makeNewBoard());
        ply = new Player(initiateBoats(), gb.makeNewBoard());
        usr = new UserResponseField(gb.makeNewBoard());
        initiateGame();
        gameLoop();
    }


    public void gameLoop() {
        while (didSomeoneLoose() == 3) {
            gameStep();
        }
        endofGame();
    }

    //endofGame() Ends the game. Displays who has won, and offers a toast.
    public void endofGame() {
        if (didSomeoneLoose() == 2) {
            System.out.println("Congratulastions" + ply.name + " you have defeated the enemy fleet!");

        } else {
            System.out.println("CPU has won");


        }


    }

    //didSomeoneLoose() checks if any of the 2 participants (player ply or Computer cpu) have lost all their boats.
    public int didSomeoneLoose() {
        if (ply.haveILost()) {
            return 1;
        } else if (cpu.haveILost()) {
            return 2;
        }
        return 3;
    }

    //GameStep() does one whole rotation of actions, the player calls a shot, then the computer, and then the state of both players Boards are printed out.
    public void gameStep() {
        PlayersTurn();
        dumbComputerTurn();
        printGameInfo();
    }

    //printGameInfo() prints out both the Board of the Player ply and the Computer cpu
    public void printGameInfo() {
        System.out.println("Your territory:");
        System.out.println(gb.BoardToString(ply.eigenesGebiet, colourMode));
        System.out.println("Enemy Territory: \n");
        System.out.println(gb.BoardToString(usr.displayField, colourMode));

    }

    //initiateGame() starts the game. Displays a toast, initialises the cComputer instance with an empty Board, then filling it by calling cpu.placeOwnBoats.
    //Also Allows the player to place boats. After both the computer and the player have placed all boats, this method breaks.
    public void initiateGame() {


        System.out.println("""
                WELCOME TO\s

                d8888b.  .d8b.  d888888b d888888b db      d88888b .d8888. db   db d888888b d8888b. .d8888.\s
                88  `8D d8' `8b `~~88~~' `~~88~~' 88      88'     88'  YP 88   88   `88'   88  `8D 88'  YP\s
                88oooY' 88ooo88    88       88    88      88ooooo `8bo.   88ooo88    88    88oodD' `8bo.  \s
                88~~~b. 88~~~88    88       88    88      88~~~~~   `Y8b. 88~~~88    88    88~~~     `Y8b.\s
                88   8D 88   88    88       88    88booo. 88.     db   8D 88   88   .88.   88      db   8D\s
                Y8888P' YP   YP    YP       YP    Y88888P Y88888P `8888Y' YP   YP Y888888P 88      `8888Y'\s
                                                                                                          \s"""
                + "\n"
        );

        printLegend();
        cpu.placeOwnBoats();
        //TESTING CHEAT
        System.out.println(gb.BoardToString(cpu.getOwnArea(), colourMode));
        //TESTING CHEAT
        System.out.print("Enter your Name to start! \n" + "Your Name:");
        ply.setName(scanner.next());
        System.out.println("You have chosen: " + ply.getName());
        System.out.println("Next, we will place your boats, Commander " + ply.getName() + "!");
        System.out.println("You have these Boats available:");
        printOutBoats(ply.getAvailableBoats());
        letPlayerPlaceBoats();
        usr.displayField = gb.makeNewBoard();
    }

    //dumbComputerTurn() Computer shoots a random location, thats why the method has "dumb" in its name
    public void dumbComputerTurn() {
        int[] coords = cpu.generateRandomCords().getCoords();
        boolean check;
        do {
            check = false;
            if (ply.eigenesGebiet[coords[0]][coords[1]].isDestroyed()) {
                check = true;
            }
        } while (check);

        ply.eigenesGebiet[coords[0]][coords[1]].setDestroyed();

        if (!ply.eigenesGebiet[coords[0]][coords[1]].getType().equals("Water")) {
            System.out.println("Computer Scored a hit!");

        } else {
            System.out.println("Computer missed!");
        }


    }

    //Prints out a legend containbing the icons which will be displayed and what they mean
    public void printLegend() {
        if (colourMode) {
            System.out.println(
                    "S = Submarine \n" +
                            "C = Cruiser \n" +
                            "A = Aircraft Carrier \n" +
                            "F = Frigatte \n" +
                            GameBoard.Colour.BLUE + "~" + GameBoard.Colour.RESET + " = Water Tile \n" +
                            GameBoard.Colour.GREEN + "0" + GameBoard.Colour.RESET + " = Hit Water Tile \n" +
                            "\u001B[31m0\u001B[0m = Marked Location \n" +
                            GameBoard.Colour.YELLOW + "X" + GameBoard.Colour.RESET + " = Hit Ship \n"
            );

        } else {
            System.out.println(
                    "S = Submarine \n" +
                            "C = Cruiser \n" +
                            "A = Aircraft Carrier \n" +
                            "F = Frigatte \n" +
                            " " + " = Water Tile \n" +
                            "0 = Hit Water Tile \n" +
                            "M = Marked Location \n" +
                            "X= Hit Ship \n"


            );


        }
    }

    // PlayersTurn() is responsible for letting the user shoot a tile. It checks if the Tile that will be shot at has already been shot, or if its water. Then it adjusts the board and prints if the user has hit.
    public void PlayersTurn() {
        System.out.println("Commander, where do you want to shoot?");
        int[] coordsToBeShot;
        boolean checkIfAlreadyShot;
        do {
            checkIfAlreadyShot = false;
            coordsToBeShot = playerCoordInput();
            if (cpu.eigenesGebiet[coordsToBeShot[0]][coordsToBeShot[1]].isDestroyed()) {
                checkIfAlreadyShot = true;
                System.out.println("You Have already shot that Tile!");
            }
        } while (checkIfAlreadyShot);

        cpu.eigenesGebiet[coordsToBeShot[0]][coordsToBeShot[1]].setDestroyed();
        usr.displayField[coordsToBeShot[0]][coordsToBeShot[1]].setDestroyed();
        if (cpu.eigenesGebiet[coordsToBeShot[0]][coordsToBeShot[1]].getType() != "Water") {
            System.out.println("Thats a hit!");
            usr.displayField[coordsToBeShot[0]][coordsToBeShot[1]].setType(cpu.eigenesGebiet[coordsToBeShot[0]][coordsToBeShot[1]].getType());
        } else {
            System.out.println("Thats a miss....");
        }


    }

    //playerCoordInput() lets the User input an x and y coordinate, then returns both in the form of an int array. Index 0 is x, index 1 is y.
    public int[] playerCoordInput() {
        scanner.next();
        System.out.print("First, The X-Coordinate:");
        int[] PlacementCoords = new int[]{-1, -1};
        while (PlacementCoords[0] == -1) {
            try {
                do {
                    PlacementCoords[0] = numReader.nextInt() - 1;
                    if (PlacementCoords[0] > boardLength || PlacementCoords[0] < 0) {
                        System.out.println("Invalid coordinate. Xcoord needs to be between 1 and " + boardLength);
                        System.out.print("First, the X coordinate: ");
                        PlacementCoords[0] = numReader.nextInt() - 1;


                    }
                } while (PlacementCoords[0] > boardLength || PlacementCoords[0] < 0);
            } catch (InputMismatchException e) {
                System.out.println("Please input a Number, not anything else!");
                numReader.next();

            }
        }
        System.out.println("Now, Enter the Y coordinate!");
        while (PlacementCoords[1] == -1) {
            try {
                do {
                    PlacementCoords[1] = numReader.nextInt() - 1;
                    if (PlacementCoords[1] > boardWidth || PlacementCoords[1] < 0) {
                        System.out.println("Invalid coordinate. Ycoord needs to be between 1 and " + boardLength);
                        System.out.print("Now, the Y coordinate: ");
                        PlacementCoords[1] = numReader.nextInt() - 1;


                    }
                } while (PlacementCoords[1] > boardWidth || PlacementCoords[1] < 0);
            } catch (InputMismatchException e) {
                System.out.println("Please input a Number, not anything else!");
                numReader.next();


            }
        }
        return PlacementCoords;
    }

    // letPlayerPlaceBoats() manages the placement of the players boats. it iterates through all available boats and calls the playerPlaceABoat method for each one.
    public void letPlayerPlaceBoats() {
        for (int i = 0; i < ply.verfügbareBoote.length; i++) {

            playerPlaceABoat(ply.verfügbareBoote[i]);
            if (i < ply.verfügbareBoote.length - 1) {
                System.out.println("\n \n Continuing With your next Boat... ");
            }
            clearScreen();
        }

    }

    // playerPlaceABoat() uses User input to place a given Boat pBoat at a location.
    public void playerPlaceABoat(Boat pBoat) {
        String direction;
        Boolean fail = false;
        int[] PlacementCoords = new int[]{-1, -1};
        boolean invalid = false;
        do {
            fail = false;
            usr.displayField = ply.getOwnArea().clone();

            System.out.println("Now enter the desired Location for your " + pBoat.getType());
            System.out.print("First, the X coordinate: ");
            while (PlacementCoords[0] == -1) {
                try {
                    do {
                        PlacementCoords[0] = numReader.nextInt() - 1;
                        if (PlacementCoords[0] > boardLength || PlacementCoords[0] < 0) {
                            System.out.println("Invalid coordinate. Xcoord needs to be between 1 and " + boardLength);
                            System.out.print("First, the X coordinate: ");
                            PlacementCoords[0] = numReader.nextInt() - 1;


                        }
                    } while (PlacementCoords[0] > boardLength || PlacementCoords[0] < 0);
                } catch (InputMismatchException e) {
                    System.out.println("Please input a Number, not anything else!");
                    numReader.next();

                }
            }
            System.out.println("Now, Enter the Y coordinate!");
            while (PlacementCoords[1] == -1) {
                try {
                    do {
                        PlacementCoords[1] = numReader.nextInt() - 1;
                        if (PlacementCoords[1] > boardWidth || PlacementCoords[1] < 0) {
                            System.out.println("Invalid coordinate. Ycoord needs to be between 1 and " + boardLength);
                            System.out.print("Now, the Y coordinate: ");
                            PlacementCoords[1] = numReader.nextInt() - 1;


                        }
                    } while (PlacementCoords[1] > boardWidth || PlacementCoords[1] < 0);
                } catch (InputMismatchException e) {
                    System.out.println("Please input a Number, not anything else!");
                    numReader.next();


                }
            }

            System.out.println(" ");
            usr.setMarkAt(PlacementCoords);
            System.out.println("Your selection: \n");
            System.out.println(gb.BoardToString(usr.getDisplayField(), colourMode));
            usr.removeMarkAt(PlacementCoords);

            System.out.println("Great Choice. Next, enter the Direction in which the boat should be placed. It can be either North, South, West or East.");
            do {
                direction = scanner.next();
                invalid = false;
                switch (direction) {
                    case "North":
                    case "South":
                    case "West":
                    case "East":
                        break;
                    default:
                        invalid = true;
                        System.out.println("Invalid Input. Try again (Case sensitive!)");

                }
            } while (invalid);
            if (bm.canPlaceBoat(PlacementCoords, direction, pBoat.getSize(), usr.displayField)) {
                System.out.println("Success!");
                bm.placeBoat(PlacementCoords, direction, pBoat, usr.displayField);
                System.out.println(gb.BoardToString(usr.displayField, colourMode));
            } else {
                clearScreen();
                System.out.println("Cant place a Boat there, please try again.");
                fail = true;
                PlacementCoords[0] = -1;
                PlacementCoords[1] = -1;
            }
        } while (fail);
        ply.eigenesGebiet = usr.displayField.clone();
    }

    //initiateGame(), sets up 2 arrays, one for CPU one for Player, then executes playerDeployShips()
    //playerDeployShips, Queries the player for information about boat placement. Place Boats on the board.
    public void printOutBoats(Boat[] pBoats) {
        String compose = "";
        for (int i = 0; i < pBoats.length; i++) {
            compose = compose + " " + pBoats[i].getType();

        }
        System.out.println(compose);
    }


    // initiateBoats() Builds Boats from the given sizes by final int[] whichBoats. A number 2 means to build a boat with the length of 2, a submarine. It returns an Array with he built boats.
    public Boat[] initiateBoats() {
        Boat[] output = new Boat[whichBoats.length];
        for (int i = 0; i < whichBoats.length; i++) {

            output[i] = sb.build_a_ship(whichBoats[i]);
        }

        return output;
    }

    public static void clearScreen() {
        try {

            Runtime.getRuntime().exec("cls");

        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public void sleepInSeconds(int time) {
        int timeSeconds = time * 1000;

        try {
            Thread.sleep(timeSeconds);
        } catch (IllegalArgumentException | InterruptedException e) {
            System.out.println("Invalid Time");

        }
    }
}
