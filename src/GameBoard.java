public class GameBoard {
    /**
     * This Class generates Boards and is responsible for the Visualisation of those.
     */

    public int x;
    public int y;
//Constructor calculates Length and width to use for adjusting Board Size
    GameBoard(int pLength, int pWidth) {
        x = pLength;
        y = pWidth;

    }


  //Colours that are used in displaying a Board
    public enum Colour {
        RED  ("\033[0;31m"),
        WHITE  ("\033[0;37m"),
        BLUE  ("\033[0;34m"),
        PURPLE  ("\033[0;35m"),
        YELLOW  ("\033[0;33m"),
        GREEN  ("\033[0;32m"),
        RESET  ("\u001B[0m");
        private final String color;
        private Colour(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return color;
        }
    }

    /**
     * makeNewBoard():  This Method generates an empty Board which consists only of water tiles
     */
    BoatPart[][] makeNewBoard() {
        BoatPart[][] temp = new BoatPart[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                temp[i][j] = new BoatPart("Water");
            }
        }


        return temp;
    }

    /**
     * BoardToString(): This Method Builds a String from a given Board which can be visualised later.
     */

    public String NoColourBoardToString(BoatPart[][] pBoard) {
        StringBuilder composedOutput = new StringBuilder();


        for (int i = 0; i < x; i++) {   //Outer Loop Iterates through lines
            composedOutput.append("\n");

            //new Line Break after every x fields


            for (int j = 0; j < y; j++) {   //Inner Loop Iterates through columns

                switch (pBoard[i][j].getType()) {  //Determine which tye of Tile is present
                    case "Water":

                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("[0]");
                        } else composedOutput.append("[ ]");;

                        break;
                    //Add cases for different Ships in Future(Maybe :))

                    case "Mark":

                        composedOutput.append("[M]");
                        break;

                    case "Submarine":
                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("[X]");
                        } else composedOutput.append("[S]");
                        break;

                    case "Cruiser":
                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("[X]");
                        } else composedOutput.append("[C]");
                        break;

                    case "Frigatte":

                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("[X]");

                        } else composedOutput.append("[F]");
                        break;
                    case "AirCraftCarrier":


                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("[X]");
                        } else composedOutput.append("[A]");
                        break;
                }
            }
        }

        return composedOutput.toString();
    }


    public String BoardToString(BoatPart[][] pBoard, boolean pColourMode){
        String printedBoard;
        if(pColourMode){
            return BoardToStringColour(pBoard);

        }
        return NoColourBoardToString(pBoard);
        }

        public void buildFirstLineNumbers(StringBuilder composedOutput, int arrayLength){
            StringBuilder composer = composedOutput;
            composer.append("    ");
            for(int i = 0; i < arrayLength; i++){
                    composer.append(i+"  ");

            }
        }
    public String BoardToStringColour(BoatPart[][] pBoard) {
        StringBuilder composedOutput = new StringBuilder();
        buildFirstLineNumbers(composedOutput, pBoard[0].length);
        for (int i = 0; i < x; i++) {   //Outer Loop Iterates through lines

            if(i <9) {                 //This fixes a line shift when the number becomes one with 2 digits
                composedOutput.append("\n" + (i + 1) + "  ");
            }
            else{
                composedOutput.append("\n" + (i + 1) + " ");

            }
            //new Line Break after every x fields


            for (int j = 0; j < y; j++) {   //Inner Loop Iterates through columns

                switch (pBoard[i][j].getType()) {  //Determine which tye of Tile is present
                    case "Water":

                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("[" + Colour.GREEN + "0" + Colour.RESET + "]");
                        } else composedOutput.append("[" + Colour.BLUE + "~" + Colour.RESET + "]");;

                        break;
                    //Add cases for different Ships in Future(Maybe :))

                    case "Mark":

                        composedOutput.append("[\u001B[31m0\u001B[0m]");
                        break;

                    case "Submarine":
                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("["+ Colour.YELLOW + "X"+ Colour.RESET + "]");
                        } else composedOutput.append("[S]");
                        break;

                    case "Cruiser":
                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("["+ Colour.YELLOW + "X"+ Colour.RESET + "]");
                        } else composedOutput.append("[C]");
                        break;

                    case "Frigatte":

                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("["+ Colour.YELLOW + "X"+ Colour.RESET + "]");

                        } else composedOutput.append("[F]");
                        break;
                    case "AirCraftCarrier":


                        if (pBoard[i][j].Damaged) {
                            composedOutput.append("["+ Colour.YELLOW + "X"+ Colour.RESET + "]");
                        } else composedOutput.append("[A]");
                        break;
                }
            }
        }

        return composedOutput.toString();
    }





}



