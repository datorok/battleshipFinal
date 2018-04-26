package matritellab;

public class TableEntity {

    public boolean status;
    public Coordinate coordinates;
    public String name;

    public TableEntity(boolean status, Coordinate coordinates, String name) {
        this.status = status;
        this.coordinates = coordinates;
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
