public class Player {
    Boat[] verfügbareBoote;
    Boat[] gesetzteBoote;
    BoatPart[][] eigenesGebiet;
    String name;
    Player(Boat[] pSpielerboote, BoatPart[][] pSpielergebiet) {
        verfügbareBoote = pSpielerboote;
        eigenesGebiet = pSpielergebiet;
    }

    public Boat[] getAvailableBoats() {

        return verfügbareBoote;
    }

    public BoatPart[][] getOwnArea() {
        return eigenesGebiet;
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
    public boolean haveILost(){
        int lostShips = 0;
        for(int i = 0; i< verfügbareBoote.length; i++){
            if(verfügbareBoote[i].isDestroyed()){
                lostShips++;
            }
        }
        if(lostShips == verfügbareBoote.length){

            return true;
        }
        return false;
    }
}

