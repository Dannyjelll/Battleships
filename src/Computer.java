public class Computer {

    /**
     * The CPU and Player both have their own Boards, while the baord that is displayed to the player is obfuscated using userResponse field.
     */

    BoardManager creator = new BoardManager();
    Boat[] verfügbareBoote;
    BoatPart[][] eigenesGebiet;
    int length;
    int width;

    Computer(Boat[] pSpielerboote, BoatPart[][] pComputergebiet) {
        verfügbareBoote = pSpielerboote;
        eigenesGebiet = pComputergebiet;
        length = pComputergebiet.length - 1;
        width = pComputergebiet.length - 1;
    }


    public BoatPart[][] getOwnArea() {
        return eigenesGebiet;
    }


    /**
     * This Method generates random placement ideas for the computer, checks if they are valid and then places them. In the End, the instance Variable of the Object is overwritten.
     */
    public void placeOwnBoats() {
        BoatPart[][] output = eigenesGebiet.clone();
        for (int i = 0; i < verfügbareBoote.length; i++) {

            Randoms container = generateRandomCords();

            while (!creator.canPlaceBoat(container.getCoords(), container.getDirection(), verfügbareBoote[i].getSize(), output)) {   //
                container = generateRandomCords();

            }

            output = creator.placeBoat(container.getCoords(), container.getDirection(), verfügbareBoote[i], output);

        }


        this.eigenesGebiet = output;
    }


    /**
     * This Method generates a random coordinate as well as a random direction, which is used by placeOwnBoats to place the computers boats.
     */
    public Randoms generateRandomCords() {
        int[] coords = new int[2];
        coords[0] = (int) (Math.random() * ((length) + 1));
        coords[1] = (int) (Math.random() * ((width) + 1));

        // coords[0] = (int) (Math.random() * ((8) + 1));
        // coords[1] = (int) (Math.random() * ((8) + 1));

        int z = (int) (Math.random() * ((3) + 1)) + 1;
        String direction = switch (z) {
            case 1 -> "South";
            case 2 -> "North";
            case 3 -> "West";
            case 4 -> "East";
            default -> "null";
        };

        return new Randoms(coords, direction);
    }

    public BoatPart[][] shootRandom(BoatPart[][] pTable) {
        BoatPart[][] table = pTable.clone();
        Randoms numbers = generateRandomCords();
        table[numbers.getCoords()[0]][numbers.getCoords()[1]].setDestroyed();


        return table;
    }

    public boolean haveILost() {
        int lostShips = 0;
        for (int i = 0; i < verfügbareBoote.length; i++) {
            if (verfügbareBoote[i].isDestroyed()) {
                lostShips++;
            }
        }
        if (lostShips == verfügbareBoote.length) {

            return true;
        }
        return false;
    }

}
