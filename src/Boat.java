//Generic Boat class. A boat Object manages All boat parts which are placed in the array itself.
// A Boat works through references. The Boat has list of parts. Since an array doesnt store the data itself, but rather a reference to it, changing the part Object in the list also changes the one referenced in the 2D Array.
class Boat {
    int size;
    BoatPart[] parts;
    String type;

    Boat(int pSize, String pType, BoatPart[] pParts) {
        size = pSize;
        type = pType;
        parts = pParts;
    }

    // Get the Ship type (e.G. Submarine, Cruiser)
    public String getType() {return type;}

    public int getSize() {
        return size;

    }

    //OPIMIZE RUNTIME -damaged counter
    public boolean isDestroyed() {
        int damaged = 0; // int damaged counts how many parts have been damaged
        for (int i = 0; i < size; i++) {
            if (parts[i].Damaged) {
                damaged++;
            }

        }

        return damaged == size;
    }

    public BoatPart[] getParts() {

        return parts;
    }

}
