package matritellab;

public class SubShip extends TableEntity {

    /**
     * The "MissedTarget" Class is inherited from the TableEntity Class. This Class is the blueprint of the
     * SubShips (parts of the MainShips). She SubShips are used for to represent the length of an MainShip
     * For example if length of an MainShip is 3, it has 2 SubShips. If it's length is only 2, it has 1 SubShip
     * The SubShips are stand alone objects, each of them owns name, Coordinate and status.
     */

    private String direction;
    private boolean canFire;

    public SubShip(boolean status, Coordinate coordinates, String direction, String name, boolean canFire) {
        super(status, coordinates, name);
        this.direction = direction;
        this.canFire = canFire;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }
}
