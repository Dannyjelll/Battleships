/**
 *
 */
//The Board manager handles all actions on the boards. It can check if a boat can be placed at given points, and then actually place it.
public class BoardManager {

    BoardManager() {


    }

    //canPlaceBoat(int[] pCoords, String Direction, int pLength); -> Checks if boat can be placed at given coordinate
    public boolean canPlaceBoat(int[] pCoords, String Direction, int pLength, BoatPart[][] pBoard) {
        int  length = pBoard.length - 1;
        int  width = pBoard.length - 1;
        if (pCoords[0] > length || pCoords[0] < 0 || pCoords[1] > width || pCoords[1] < 0) {

            return false;
        }
        int Counter = 0;
        boolean finished = false;
        switch (Direction) {


            case "North":

                do {
                    if (Counter == pLength) {
                        finished = true;

                    }
                    else if (pCoords[0] - Counter < 0 || !pBoard[pCoords[0] - Counter][pCoords[1]].getType().equals("Water")  ) {
                        return false;
                    }
                    Counter++;

                } while (!finished);
            break;

            case "South":

                do {
                    if (Counter == pLength) {
                        finished = true;

                    }
                    else if (pCoords[0] + Counter > length || !pBoard[pCoords[0] + Counter][pCoords[1]].getType().equals("Water") ) {
                        return false;
                    }
                    Counter++;

                } while (!finished);
                break;
            case "West":

                do {
                    if (Counter == pLength) {
                        finished = true;

                    }
                    else if (pCoords[1] - Counter < 0 || !pBoard[pCoords[0]][pCoords[1] - Counter].getType().equals("Water") ) {
                        return false;
                    }
                    Counter++;

                } while (!finished);
                break;
            case "East":

                do {
                    if (Counter == pLength) {
                        finished = true;

                    }
                    else if (pCoords[1] + Counter > width || !pBoard[pCoords[0]][pCoords[1] + Counter].getType().equals("Water") ) {
                        return false;
                    }
                    Counter++;

                } while (!finished);
                break;

        }


        //Switch for differenciating between the placement in different Directions. To place a boat, you enter a coordinate and in which direction it should point.


        return true;
    }

    //placeBoat(int[] pCoords, String Direction, List<Boat> ),
    public BoatPart[][] placeBoat(int[] pCoords, String Direction, Boat pBoat, BoatPart[][] pBoard) {
        BoatPart[][] tempBoard = pBoard.clone();

        if (canPlaceBoat(pCoords, Direction, pBoat.getSize(), tempBoard)) { //Checks if positioning is valid

            switch (Direction) {
                case "North" -> {

                    for (int i = 0; i < pBoat.getSize(); i++) {

                        tempBoard[pCoords[0] - i][pCoords[1]] = pBoat.getParts()[i];

                    }
                    return tempBoard;
                }
                case "South" -> {

                    for (int i = 0; i < pBoat.getSize(); i++) {
                        tempBoard[pCoords[0] + i][pCoords[1]] = pBoat.getParts()[i];

                    }
                    return tempBoard;
                }
                case "West" -> {

                    for (int i = 0; i < pBoat.getSize(); i++) {

                        tempBoard[pCoords[0]][pCoords[1] - i] = pBoat.getParts()[i];

                    }
                    return tempBoard;
                }
                case "East" -> {

                    for (int i = 0; i < pBoat.getSize(); i++) {
                        tempBoard[pCoords[0]][pCoords[1] + i] = pBoat.getParts()[i];

                    }
                    return tempBoard;
                }
                default -> System.out.println("default case place boat");
            }

        } else {
            tempBoard[0][0] = new BoatPart("404");
        }


        return tempBoard;
    }

    public boolean areBoardsEqual(BoatPart[][] array1, BoatPart[][] array2) {

        if (array1.length == array2.length) {

            for (int i = 0; i < array1.length; ++i) {


                for (int j = 0; j < array1.length; ++j) {

                    String type1 = array1[i][j].getType();
                    String type2 = array2[i][j].getType();
                    if (!type1.equals(type2)) {

                        return false;
                    }


                }


            }
            return true;
        } else return false;


    }

    //TESTING ONLY

}


//TESTING ONLY
/**
 * switch (Direction) {
 * case "North":
 * int coord1 = pCoords[0];
 * for (int i = 0; i < pLength; i++) {
 * coord1 = coord1-1;
 * if(coord1 == -1){
 * return false;
 * <p>
 * <p>
 * }
 * <p>
 * if (!pBoard[i][pCoords[1]].getType().equals("Water")) {
 * System.out.println("Boat in way north");
 * return false;
 * }
 * <p>
 * }
 * <p>
 * <p>
 * break;
 * case "South":
 * int coord2 = pCoords[0];
 * for (int i = 0; i < pLength; i++) {
 * coord2 = coord2 + i;
 * <p>
 * if (coord2 == 10) {
 * return false;
 * <p>
 * }
 * if (!pBoard[i][pCoords[1]].getType().equals("Water")) {
 * return false;
 * }
 * <p>
 * }
 * <p>
 * break;
 * case "West":
 * int coord3 = pCoords[1];
 * for (int i = 0; i < pLength; i++) {
 * coord3 = coord3 - i;
 * if (coord3 == -1) {
 * <p>
 * return false;
 * <p>
 * }
 * if (pBoard[pCoords[0]][i].getType().equals("Water")) {
 * return false;
 * <p>
 * }
 * <p>
 * }
 * break;
 * case "East":
 * int coord4 = pCoords[1];
 * for (int i = 0; i < pLength; i++) {
 * coord4 = coord4 - i;
 * <p>
 * <p>
 * if (coord4 == 10) {
 * return false;
 * <p>
 * }
 * if (pBoard[pCoords[0]][i].getType().equals("Water")) {
 * return false;
 * }
 * <p>
 * }
 * break;
 * default:
 * <p>
 * return false;
 * }
 */