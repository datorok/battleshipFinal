package matritellab;

public class Coordinate {

    /*
    In this game each TableEntity (MainShip, SubShip, MissedTarget) has coordinate
    The coordinate is a string that consists of 2 numbers separated by a ;
    */

    private String coordinates;

    public Coordinate(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
