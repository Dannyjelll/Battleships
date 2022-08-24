//The ship builder is responsible for creating new Boat objects as needed.

public class ShipBuilder {
    //BuildShip -> creates a ship object, which owns a list that includes all its parts.
    public Boat build_a_ship(int pLength) {
        String type = switch (pLength) {
            case 2 -> "Submarine";
            case 3 -> "Cruiser";
            case 4 -> "Frigatte";
            case 5 -> "AirCraftCarrier";
            default ->
                    throw new RuntimeException("Something went terribly wrong. Check configuration. (Boat type doesnt exist)");
        };

        return new Boat(pLength, type, fabricateParts(pLength, type));
    }

    public BoatPart[] fabricateParts(int pAmount, String pType) {
        BoatPart[] parts = new BoatPart[pAmount];
        for (int i = 0; i < pAmount; i++) {
            parts[i] = new BoatPart(pType);
        }
        return parts;
    }
}
