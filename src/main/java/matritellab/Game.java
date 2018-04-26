package matritellab;

import java.util.Scanner;

import static matritellab.PrintOut.*;
import static matritellab.Table.*;

    /*
       This Class contains all the methods that control the game
    */

public class Game {

     /**
      * The "didYouLoseTheGame" method checks whether any player has lose the game or not.
      * This method is called in each turn of the game by the "fire" method
      * If a player lose this game, this method terminates the program.
      */

    public static void didYouLoseTheGame(Table yourTable, Table enemyTable) {

        if (enemyTable.getTableEntities().isEmpty()) {
            System.out.println(enemyTable.getName() + " has lost the game.");
            System.out.println();
            enemyTable.setLost(true);
            String yourTableString = createPlayersGridInString(yourTable);
            String enemyTableString = createPlayersGridInString(enemyTable);
            mixTheGridStrings(yourTableString, enemyTableString);
            System.exit(0);
        }

        if (yourTable.getTableEntities().isEmpty()) {
            System.out.println(yourTable.getName() + " has lost the game.");
            System.out.println();
            yourTable.setLost(true);
            String yourTableString = createPlayersGridInString(yourTable);
            String enemyTableString = createPlayersGridInString(enemyTable);
            mixTheGridStrings(yourTableString, enemyTableString);
            System.exit(0);
        }
    }

    /**
     * The "shipStatusCheck" method checks whether a given MainShip object (and its PartShips)
     * has sunk due to a hit or not
     * This method is called in each turn of the game by the "fire" method
     * If a Ship object sink, this method changes its (and its SubShip parts) name putting "_"
     * marks before and after the original name 0th. and 1th. characters
     * for example, if a destroyer ship sink, its name changes the following way:
     *
     * ORIGINAL NAME:      DE1, DE2
     * FIRST HIT:          DEX,DE2 // DE1,DEX
     * SECOND HIT (SUNK):  _DE_, _DE_
     *
     */

    public static void shipStatusCheck(Table nametable, TableEntity tableEntity) {

        if (tableEntity instanceof MainShip) {

            boolean statusChecker = tableEntity.getStatus();

                for (int i = 0; i < ((MainShip) tableEntity).getSubShipPartList().size(); i++) {

                    if (((MainShip) tableEntity).getSubShipPartList().get(i).getStatus()){

                        statusChecker=true;
                    }
                }

            if (!statusChecker) {

                System.out.println(nametable.getName()+"'s " + ((MainShip) tableEntity).getShipType() + " has just sunk!");
                nametable.removeFromShips(tableEntity);

                String temp = tableEntity.getName();
                temp=temp.substring(0,2);
                    tableEntity.setName("_".concat(temp).concat("_"));
                    for (int i = 0; i < ((MainShip) tableEntity).getSubShipPartList().size(); i++) {
                        ((MainShip) tableEntity).getSubShipPartList().get(i).setName("_".concat(temp).concat("_"));
                    }
            }
        }
    }

    /**
     * The "mainShipPlacerHorisontal" method checks whether is there enough space for a given type
     * of ship in the given line of the Table or not
     * This method is called by the "mainShipPlacer" method if the player wants to put a ship
     * onto the Table horizontally
     * If the space is sufficient in the line, the method initialise a new MainShip object
     * and its SubShip parts as per the provided parameters
     */

    public static void mainShipPlacerHorisontal(Table nameTable, String coordinateString, String name, int size) {

        Scanner sc = new Scanner(System.in);
        String[] coordinateStringArr = coordinateString.split(";");
        int horisontal = Integer.parseInt(coordinateStringArr[0]);
        int vertical = Integer.parseInt(coordinateStringArr[1]);

        while ((vertical + size > nameTable.getTableSize())) {
            System.out.println("There is not enough space in the given line for the ship. Provide again:");
            coordinateString = coordinateRequestForTable(nameTable.getTableSize());

            coordinateStringArr = coordinateString.split(";");
            horisontal = Integer.parseInt(coordinateStringArr[0]);
            vertical = Integer.parseInt(coordinateStringArr[1]);
        }
        /*
                if (nameTable.getTable()[horisontal][vertical] == null && nameTable.getTable()[horisontal][vertical + 1] == null && nameTable.getTable()[horisontal][vertical + 2] == null && nameTable.getTable()[horisontal][vertical + 3] == null) {
                    nameTable.getTable()[horisontal][vertical] = "BA";
                    nameTable.getTable()[horisontal][vertical + 1] = "BA";
                    nameTable.getTable()[horisontal][vertical + 2] = "BA";
                    nameTable.getTable()[horisontal][vertical + 3] = "BA";

                } else {
                    System.out.println("The requested space is already taken. Let's start again...");
                    battleshipPlacer(nameTable);
        }
        */
        boolean willFit = true;
        for (int i = 0; i < size; i++) {
            if (nameTable.getTable()[horisontal][vertical + i] != null) {
                willFit = false;
            }
        }

        if (willFit == true) {

            Coordinate coordinate = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical)));

            if (size == 2) {

                MainShip destroyership = new MainShip(true, coordinate,  "H", "DE1",true, "destroyer");
                nameTable.getTable()[horisontal][vertical]=destroyership;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical + i)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "H", "DE".concat(String.valueOf(i + 1)), true);
                    destroyership.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal][vertical+i]=tempsubship;
                }

                nameTable.addToShips(destroyership);

            } else if (size == 3 && name.equals("cruiser")) {

                MainShip cruisership = new MainShip(true, coordinate, "H", "CR1", true, "cruiser");
                nameTable.getTable()[horisontal][vertical]=cruisership;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical + i)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "H", "CR".concat(String.valueOf(i + 1)), true);
                    cruisership.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal][vertical+i]=tempsubship;
                }

                nameTable.addToShips(cruisership);

            } else if (size == 3 && name.equals("submarine")) {

                MainShip submarineship = new MainShip(true, coordinate, "H","SU1",  true, "submarine");
                nameTable.getTable()[horisontal][vertical]=submarineship;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical + i)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "H", "SU".concat(String.valueOf(i + 1)), true);
                    submarineship.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal][vertical+i]=tempsubship;
                }

                nameTable.addToShips(submarineship);

            } else if (size == 4) {

                MainShip battleship = new MainShip(true, coordinate, "H", "BA1", true, "battleship");
                nameTable.getTable()[horisontal][vertical]=battleship;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical + i)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "H", "BA".concat(String.valueOf(i + 1)), true);
                    battleship.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal][vertical+i]=tempsubship;
                }

                nameTable.addToShips(battleship);

            } else if (size == 5) {

                MainShip carriership = new MainShip(true, coordinate,  "H","CA1", false, "carrier");
                nameTable.getTable()[horisontal][vertical]=carriership;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical + i)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "H", "CA".concat(String.valueOf(i + 1)), true);
                    carriership.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal][vertical+i]=tempsubship;
                }
                nameTable.addToShips(carriership);
            }

        } else {

            System.out.println("The requested space is already taken. Let's start again...");
            mainShipPlacer(nameTable, name, size);
        }
    }

    /**
     * The "mainShipPlacerVertical" method checks whether is there enough space for a given type
     * of ship in the given column of the Table or not
     * This method is called by the "mainShipPlacer" method if the player wants to put a ship
     * onto the Table vertically
     * If the space is sufficient in the line, the method initialise a new MainShip object
     * and its SubShip parts as per the provided parameters
     */

    public static void mainShipPlacerVertical(Table nameTable, String coordinateString, String name, int size) {

        Scanner sc = new Scanner(System.in);
        String[] coordinateStringArr = coordinateString.split(";");
        int horisontal = Integer.parseInt(coordinateStringArr[0]);
        int vertical = Integer.parseInt(coordinateStringArr[1]);

        while ((horisontal + size > nameTable.getTableSize())) {

            System.out.println("There is not enough space in the given column for the ship. Provide again:");
            coordinateString = coordinateRequestForTable(nameTable.getTableSize());

            coordinateStringArr = coordinateString.split(";");
            horisontal = Integer.parseInt(coordinateStringArr[0]);
            vertical = Integer.parseInt(coordinateStringArr[1]);
        }
        /*
                if (nameTable.getTable()[horisontal][vertical] == null && nameTable.getTable()[horisontal + 1][vertical] == null && nameTable.getTable()[horisontal + 2][vertical] == null && nameTable.getTable()[horisontal + 3][vertical] == null) {
                    nameTable.getTable()[horisontal][vertical] = "BA";
                    nameTable.getTable()[horisontal + 1][vertical] = "BA";
                    nameTable.getTable()[horisontal + 2][vertical] = "BA";
                    nameTable.getTable()[horisontal + 3][vertical] = "BA";

                } else {
                    System.out.println("The requested space is already taken. Let's start again...");
                    battleshipPlacer(nameTable);
        }
        */
        boolean willFit = true;
        for (int i = 0; i < size; i++) {
            if (nameTable.getTable()[horisontal + i][vertical] != null) {
                willFit = false;
            }
        }

        if (willFit == true) {

            Coordinate coordinate = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical)));

            if (size == 2) {

                MainShip destroyership = new MainShip(true, coordinate, "V","DE1",  true, "destroyer");
                nameTable.getTable()[horisontal][vertical]=destroyership;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal + i).concat(";").concat(String.valueOf(vertical)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "V", "DE".concat(String.valueOf(i + 1)), true);
                    destroyership.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal+i][vertical]=tempsubship;

                }

                nameTable.addToShips(destroyership);

            } else if (size == 3 && name.equals("cruiser")) {

                MainShip cruisership = new MainShip(true, coordinate, "V", "CR1", true, "cruiser");
                nameTable.getTable()[horisontal][vertical]=cruisership;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal + i).concat(";").concat(String.valueOf(vertical)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "V", "CR".concat(String.valueOf(i + 1)), true);
                    cruisership.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal+i][vertical]=tempsubship;
                }

                nameTable.addToShips(cruisership);

            } else if (size == 3 && name.equals("submarine")) {

                MainShip submarineship = new MainShip(true, coordinate, "V","SU1",  true, "submarine");
                nameTable.getTable()[horisontal][vertical]=submarineship;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal + i).concat(";").concat(String.valueOf(vertical)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "V", "SU".concat(String.valueOf(i + 1)), true);
                    submarineship.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal+i][vertical]=tempsubship;
                }

                nameTable.addToShips(submarineship);

            } else if (size == 4) {

                MainShip battleship = new MainShip(true, coordinate, "V","BA1",  true, "battleship");
                nameTable.getTable()[horisontal][vertical]=battleship;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal + i).concat(";").concat(String.valueOf(vertical)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "V", "BA".concat(String.valueOf(i + 1)), true);
                    battleship.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal+i][vertical]=tempsubship;
                }

                nameTable.addToShips(battleship);

            } else if (size == 5) {

                MainShip carriership = new MainShip(true, coordinate, "V","CA1",  false, "carrier");
                nameTable.getTable()[horisontal][vertical]=carriership;

                for (int i = 1; i < size; i++) {
                    Coordinate coordinatesu = new Coordinate(String.valueOf(horisontal).concat(";").concat(String.valueOf(vertical + i)));
                    SubShip tempsubship = new SubShip(true, coordinatesu, "V", "CA".concat(String.valueOf(i + 1)), true);
                    carriership.addToSubShipPartList(tempsubship);
                    nameTable.getTable()[horisontal+i][vertical]=tempsubship;
                }

                nameTable.addToShips(carriership);
            }

        } else {

            System.out.println("The requested space is already taken. Let's start again...");
            mainShipPlacer(nameTable, name, size);
        }
    }

    /**
     * The "mainShipPlacer" method is the first step of the MainShip object initialisation
     * This uses the "coordinateRequestForTable" method to get the coordinate of the given
     * ship from the user, and for its validation. If the provided coordinate is valid (
     * it can be parsed to integer, and it fits into the given Table) the method gets the
     * demanded direction of the given MainShip object from the user.
     * As per the chosen direction (horizontal or vertical) this method passes the necessary
     * parameters to the mainShipPlacerVertical or to the mainShipPlacerHorizontal method
     */


    public static void mainShipPlacer(Table nameTable, String name, int size) {

        Scanner sc = new Scanner(System.in);
        System.out.println("provide the coordinate (int;int) of the ship's prow: ");

        String coordinateString = coordinateRequestForTable(nameTable.getTableSize());

        System.out.println("Provide the letter sign of the ship's direction (H or V) : ");
        String direction = sc.nextLine();

        direction = shipPlacerCheckParameters(direction);
        if (direction.equals("V")) {
            mainShipPlacerVertical(nameTable, coordinateString, name, size);
        } else {
            mainShipPlacerHorisontal(nameTable, coordinateString, name, size);
        }
    }

    /**
     * The "fire" method controls the game. Once the initialisation of the players' Tables
     * are finished, the "fire" method ask for the coordinates of the first player's target and
     * investigates whether he/she hits or misses the target (MainShip or SubShip object of
     * his/her opponent).
     *
     * If the player missed, the method creates an MissedTarget on the given coordinate of the Table
     *
     * If the player provides the same coordinate as just before (redundant shot), the methods notify him/her
     *
     * If the player hit an MainShip or an SubShip object of his/her opponent, the game sets the status
     * of the affected object to false and rename it changing the original names last character to "X"
     * For example SM2  ==>  SMX
     *
     * After the investigation this method calls the "shipStatusCheck"
     * and the "didYouLoseTheGame" methods in order to learn whether the target has sunk or
     * the game has been lost or not.
     */

    public static void fire(Table yourTable, Table enemyTable) {

        System.out.println("This is the current state: ");
        String yourTableString = createPlayersGridInString(yourTable);
        String enemyTableString = createEnemysGridInString(enemyTable);
        System.out.println();
        mixTheGridStrings(yourTableString, enemyTableString);
        Scanner sc = new Scanner(System.in);
        System.out.println("Provide the coordinate of your target (int;int): ");
        String targetCoordinate = coordinateRequestForTable(enemyTable.getTableSize());

        String[] targetCoordinateArr = targetCoordinate.split(";");

        int targethorisontal = Integer.parseInt(targetCoordinateArr[0]);
        int targetvertical = Integer.parseInt(targetCoordinateArr[1]);

        if (enemyTable.getTable()[targethorisontal][targetvertical] == null) {

            Coordinate coordinates = new Coordinate(String.valueOf(targethorisontal).concat(";").concat(String.valueOf(targetvertical)));
            MissedTarget missedtarget = new MissedTarget(true, coordinates, " 0 ");
            enemyTable.getTable()[targethorisontal][targetvertical] = missedtarget;
            System.out.println("You missed it!");

        } else if (!(enemyTable.getTable()[targethorisontal][targetvertical] instanceof MissedTarget) && (String.valueOf(enemyTable.getTable()[targethorisontal][targetvertical].getName().charAt(0)).equals("_") || String.valueOf(enemyTable.getTable()[targethorisontal][targetvertical].getName().charAt(2)).equals("X"))){
            System.out.println("You have already shot here... Don't waste your opportunities or you will lose!");
        } else {

            enemyTable.getTable()[targethorisontal][targetvertical].setStatus(false);
            String newShipNameforHittedShip = enemyTable.getTable()[targethorisontal][targetvertical].getName().substring(0, 2).concat("X");
            enemyTable.getTable()[targethorisontal][targetvertical].setName(newShipNameforHittedShip);
            System.out.println("You have just hit a ship!");

            for (int i = 0; i < enemyTable.getTableEntities().size(); i++) {
                shipStatusCheck(enemyTable, enemyTable.getTableEntities().get(i));
            }
            didYouLoseTheGame(enemyTable, yourTable);
        }

        System.out.println("This is the current state: ");
        System.out.println();
        String yourTableString1 = createPlayersGridInString(yourTable);
        String enemyTableString1 = createEnemysGridInString(enemyTable);
        mixTheGridStrings(yourTableString1, enemyTableString1);
        System.out.println();
        System.out.println();
    }
}
