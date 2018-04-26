package matritellab;

import java.util.ArrayList;
import java.util.List;

/**
 * The "MainShip" Class is inherited from the TableEntity Class. This Class is the blueprint of the
 * MainShips (Destroyer, Submarine, Cruiser, Battleship, Carrier).
 * The MainShips have an SubShip List in order to manage its SubShips that are separated and stand alone
 * objects.
 */

public class MainShip extends TableEntity {

    private String direction;
    private boolean canFire;
    private String shipType;
    private List<SubShip> subShipPartList;

    public MainShip(boolean status, Coordinate coordinates, String direction, String name,  boolean canFire, String shipType) {
        super(status, coordinates, name);
        this.direction = direction;
        this.canFire = canFire;
        this.shipType = shipType;
        this.subShipPartList = new ArrayList<SubShip>();
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

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public List<SubShip> getSubShipPartList() {
        return subShipPartList;
    }

    public void setSubShipPartList(List<SubShip> subShipPartList) {
        this.subShipPartList = subShipPartList;
    }

    public void addToSubShipPartList(SubShip subship) {

        subShipPartList.add(subship);
    }
}
