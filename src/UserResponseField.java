/**
 * Class that manages the actual field that is displayed to the user. The Ships of the Computer must be obfuscated.
 * This class achieves this by projecting an empty water field and dynamically updating, depending on which input the user provides.
 */

public class UserResponseField {
    BoatPart[][] displayField;

    UserResponseField(BoatPart[][] pEmptyField) {
        displayField = pEmptyField;

    }

    public void setHitAt(int[] coords) {
        displayField[coords[0]][coords[1]].setDestroyed();
        displayField[coords[0]][coords[1]].setType("Submarine");
    }
    //setMarkAt() is used to set a mark on a tile, which is used to show the user where the boat will be placed.
    public void setMarkAt(int[] coords){
        if(displayField[coords[0]][coords[1]].getType() == "Water") {
            displayField[coords[0]][coords[1]].setType("Mark");
        }
    }
    public void removeMarkAt(int[] coords){
        if(displayField[coords[0]][coords[1]].getType() == "Mark") {
            displayField[coords[0]][coords[1]].setType("Water");
        }
    }
    public BoatPart[][] getDisplayField(){
        return displayField;


    }
}
