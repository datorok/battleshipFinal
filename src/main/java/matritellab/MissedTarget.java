package matritellab;

public class MissedTarget extends TableEntity {

    /**
     * The "MissedTarget" Class is inherited from the TableEntity Class. This Class is the blueprint of the
     * MissedTarget objects that are placed onto the Table in case of shots that missed the target.
     * It's only function is to record the players' unsuccessful shot attempts
     */

    public MissedTarget(boolean status, Coordinate coordinates, String name) {
        super(status, coordinates, name);
    }
}
