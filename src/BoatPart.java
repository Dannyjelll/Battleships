public class BoatPart {
    public String typeofship;
    boolean Damaged; //Also used to mark if you already shot a location without a ship

    BoatPart(String pBoatType) {
        Damaged = false;
        typeofship = pBoatType; //Since our 2 Dimensional Array has BoatPart as its data type, BoatPart also needs to be used for Water Tiles
    }

    public String getType() {

        return typeofship;
    }

    public boolean isDestroyed() {

        return Damaged;
    }

    public void setDestroyed() {
        Damaged = true;

    }

    public void setType(String pType) {
        typeofship = pType;

    }

    public boolean equals(Object o) {

        if (!(o instanceof BoatPart)) {
            return false;
        }
        return ((BoatPart) o).getType().equals(this.getType());
    }
}
