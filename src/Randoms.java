/**
 * just a shitty data container for random numbers used by computer
 */

public class Randoms {
    int[] coords;
    String Direction;

    Randoms(int[] pCoords, String pDirection) {
        coords = pCoords;
        Direction = pDirection;
    }

    public int[] getCoords() {
        return coords;

    }

    public String getDirection() {
        return Direction;
    }
}
