public class Player {
    Boat[] availableBoats;
    BoatPart[][] ownArea;
    String name;
    Player(Boat[] pSpielerboote, BoatPart[][] pSpielergebiet) {
        availableBoats = pSpielerboote;
        ownArea = pSpielergebiet;
    }

    public Boat[] getAvailableBoats() {

        return availableBoats;
    }

    public BoatPart[][] getOwnArea() {
        return ownArea;
    }
    public String getName(){
        if(name.isEmpty()){
            return "Player";
        }
        return name;
    }
    public void setName(String pName){
        name = pName;

    }

    //OPTIMIZE RUNTIME
    public boolean haveILost(){
        int lostShips = 0;
        for(int i = 0; i< availableBoats.length; i++){
            if(availableBoats[i].isDestroyed()){
                lostShips++;
            }
        }
        if(lostShips == availableBoats.length){

            return true;
        }
        return false;
    }
}

