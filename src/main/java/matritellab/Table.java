package matritellab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static matritellab.Game.mainShipPlacer;
import static matritellab.PrintOut.createPlayersGridInString;

public class Table {

    private int tableSize;
    private boolean lost;
    private String name;
    private TableEntity[][] table;
    private List<TableEntity> tableEntities;

    public Table(String name, int tableSize) {
        this.name = name;
        this.tableSize = tableSize;
        lost = false;
        table = new TableEntity[tableSize][tableSize];
        tableEntities = new ArrayList<TableEntity>();
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableEntity[][] getTable() {
        return table;
    }

    public void setTable(TableEntity[][] table) {
        this.table = table;
    }

    public void addToShips (TableEntity tableEntity){
        tableEntities.add(tableEntity);
    }

    public void removeFromShips (TableEntity tableEntity){
        tableEntities.remove(tableEntity);
    }

    public List<TableEntity> getTableEntities() {
        return tableEntities;
    }

    public void setTableEntities(List<TableEntity> tableEntities) {
        this.tableEntities = tableEntities;
    }


    /**
     * The "stringParseToInt" method gets an String as a parameter and returns true if the given
     * String can be cutted into two pieces as per the ";" mark in the middle, and the parts can be
     * parsed to [0-9] && [0-9].
     * If it isn't feasible the method return false.
     */

    public static boolean stringParseToInt(String coordinateString) {

      if(coordinateString.length()!=3 || !coordinateString.contains(";") || coordinateString.length()==1 || coordinateString.equals(";;;")){
          return false;
      }
        String []coordinateStringArr = coordinateString.split(";");
        try {
            Integer.parseInt(coordinateStringArr[0]);
            Integer.parseInt(coordinateStringArr[1]);

        } catch (NumberFormatException ex) {

            return false;
        }

        return true;
    }

    /**
     * The "stringParseToIntAboveTen" method gets an String as a parameter and returns true if the given
     * String can be cutted into two pieces as per the ";" mark in the middle, and the parts can be
     * parsed to [0-99] && [0-99].
     * If it isn't feasible the method return false.
     */

    public static boolean stringParseToIntAboveTen (String coordinateString) {

        if(!coordinateString.contains(";") || (coordinateString.length()<3) || (coordinateString.length()>5) || coordinateString.equals(";;;;")|| coordinateString.equals(";;;;;")){
            return false;
        }
        String []coordinateStringArr = coordinateString.split(";");
        try {
            Integer.parseInt(coordinateStringArr[0]);
            Integer.parseInt(coordinateStringArr[1]);

        } catch (NumberFormatException ex) {

            return false;
        }
        return true;
    }

    /**
     * The "coordinateRequestForTable" method gets an String from the player and returns it if the
     * given string can be converted to a meaningful Coordinate object (n;n) as per the Table's size.
     * If it isn't feasible it repeats the request to the player till the String is't presented
     * in the requested format.
     */

    public static String coordinateRequestForTable(int size) {

        Scanner sc = new Scanner(System.in);
        String coordinateString = sc.nextLine();
        int horisontal = -1;
        int vertical = -1;
        if (size <= 10) {

            while (!stringParseToInt(coordinateString)) {

                System.out.println("False data format! Please provide the data again (int;int): ");
                coordinateString = sc.nextLine();
            }

            String[] coordinateStringArr = coordinateString.split(";");
            horisontal = Integer.parseInt(coordinateStringArr[0]);
            vertical = Integer.parseInt(coordinateStringArr[1]);

            while (horisontal > size || vertical > size) {

                System.out.println("Invalid parameters. Values must be less than " + size + ". Provide again the coordinates(int;int): ");
                coordinateString = sc.nextLine();

                while (!stringParseToInt(coordinateString)) {
                    System.out.println("False data format! Please provide the data again (int;int): ");
                    coordinateString = sc.nextLine();
                }
                coordinateStringArr = coordinateString.split(";");
                horisontal = Integer.parseInt(coordinateStringArr[0]);
                vertical = Integer.parseInt(coordinateStringArr[1]);
            }
            return coordinateString;

        } else {

            while (!stringParseToIntAboveTen(coordinateString)) {

                System.out.println("False data format! Please provide the data again (int;int): ");
                coordinateString = sc.nextLine();
            }

            String[] coordinateStringArr = coordinateString.split(";");
            horisontal = Integer.parseInt(coordinateStringArr[0]);
            vertical = Integer.parseInt(coordinateStringArr[1]);

            while (horisontal > size || vertical > size) {

                System.out.println("Invalid parameters. Values must be less than " + size + ". Provide again the coordinates(int;int): ");
                coordinateString = sc.nextLine();

                while (!stringParseToIntAboveTen(coordinateString)) {
                    System.out.println("False data format! Please provide the data again (int;int): ");
                    coordinateString = sc.nextLine();
                }
                coordinateStringArr = coordinateString.split(";");
                horisontal = Integer.parseInt(coordinateStringArr[0]);
                vertical = Integer.parseInt(coordinateStringArr[1]);
            }
            return coordinateString;
        }
    }

    /**
     * The "shipPlacerCheckParameters" method gets an String from the player and returns it if the
     * given string is "H" or "V".
     * If it isn't feasible it repeats the request to the player till the String is't presented
     * in the requested format.
     */

    public static String shipPlacerCheckParameters(String direction) {

        Scanner sc = new Scanner(System.in);

        while (!direction.equals("H") && !direction.equals("V")) {
            System.out.println("Invalid parameters. Values must be H or V. Provide again:");
            direction = sc.nextLine();
        }
        return direction;
    }

    /**
     * The "tableSizeParse" method returns true if a given String can be parsed to int<999.
     * It returns false if it isn't feasible. This method is used for the setting of the Table's size
     */

    public static boolean tableSizeParse(String sizeString){
        if(sizeString.length()>3){
            return false;
        }
        try {
            Integer.parseInt(sizeString);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * The "initialisation" method helps to the player to put all of his/her MainShips and SubShips onto
     * his/her Table. This method calls the mainShipPlacer method that uses the mainShipPlacerHorizontal
     * and the mainShipPlacerVertical methods to initialize the given TableEntities (MainShips and SubShips)
     */

    public static Table initialisation(int tableSize) {

        System.out.println("Provide the player's name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();


        Table nameTable = new Table(name, tableSize);
        nameTable.setName(name);
        System.out.println(createPlayersGridInString(nameTable));
        System.out.println("Your first ship is a carrier. The length of this ship is 5 units");
        mainShipPlacer(nameTable, "carrier", 5);
        System.out.println(createPlayersGridInString(nameTable));
        System.out.println("Your second ship is a battleship. The length of this ship is 4 units");
        mainShipPlacer(nameTable, "battleship", 4);
        System.out.println(createPlayersGridInString(nameTable));
        System.out.println("Your tird ship is a cruiser. The length of this ship is 3 units");
        mainShipPlacer(nameTable, "cruiser", 3);
        System.out.println(createPlayersGridInString(nameTable));
        System.out.println("Your forth ship is a submarine. The length of this ship is 3 units");
        mainShipPlacer(nameTable, "submarine", 3);
        System.out.println(createPlayersGridInString(nameTable));
        System.out.println("Your last ship is a destroyer. The length of this ship is 2 units");
        mainShipPlacer(nameTable, "destroyer", 2);
        System.out.println(createPlayersGridInString(nameTable));
        return nameTable;
    }

    /**
     * The "yourTestTable" method creates an totally presetted testtable for the first player
     */

    public static Table yourTestTable() {

        Table royalNavy = new Table("royalNavy", 10);
        royalNavy.setName("royalNavy");

        Coordinate coordinatesde = new Coordinate(String.valueOf(0).concat(";").concat(String.valueOf(0)));
        Coordinate coordinatesde1 = new Coordinate(String.valueOf(0).concat(";").concat(String.valueOf(1)));
        MainShip destroyership = new MainShip(true,  coordinatesde,  "H","DE1", true, "destroyer");
        SubShip destroyershippart = new SubShip(true,  coordinatesde1,"H","DE2", true);
        royalNavy.addToShips(destroyership);
        destroyership.addToSubShipPartList(destroyershippart);

        Coordinate coordinates = new Coordinate(String.valueOf(0).concat(";").concat(String.valueOf(3)));
        Coordinate coordinates1 = new Coordinate(String.valueOf(0).concat(";").concat(String.valueOf(4)));
        Coordinate coordinates2 = new Coordinate(String.valueOf(0).concat(";").concat(String.valueOf(5)));
        MainShip submarineship = new MainShip(true, coordinates, "H","SM1", true, "submarine");
        SubShip submarineship1 = new SubShip(true,  coordinates1,"H", "SM2", true);
        SubShip submarineship2 = new SubShip(true,  coordinates2, "H","SM3", true);
        submarineship.addToSubShipPartList(submarineship1);
        submarineship.addToSubShipPartList(submarineship2);
        royalNavy.addToShips(submarineship);

        Coordinate coordinatescr = new Coordinate(String.valueOf(5).concat(";").concat(String.valueOf(5)));
        Coordinate coordinatescr1 = new Coordinate(String.valueOf(5).concat(";").concat(String.valueOf(6)));
        Coordinate coordinatescr2 = new Coordinate(String.valueOf(5).concat(";").concat(String.valueOf(7)));
        MainShip cruisership = new MainShip(true,  coordinatescr, "H","CR1", true, "cruiser");
        SubShip cruisershippart1 = new SubShip(true,  coordinatescr1, "H","CR2", true);
        SubShip cruisershippart2 = new SubShip(true,  coordinatescr2, "H","CR3", true);
        cruisership.addToSubShipPartList(cruisershippart1);
        cruisership.addToSubShipPartList(cruisershippart2);
        royalNavy.addToShips(cruisership);

        Coordinate coordinatesba = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(6)));
        Coordinate coordinatesba1 = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(7)));
        Coordinate coordinatesba2 = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(8)));
        Coordinate coordinatesba3 = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(9)));
        MainShip battleship = new MainShip(true,  coordinatesba, "H","BA1", true, "battleship");
        SubShip battleshippart1 = new SubShip(true,  coordinatesba1, "H","BA2", true);
        SubShip battleshippart2 = new SubShip(true,  coordinatesba2, "H","BA3", true);
        SubShip battleshippart3 = new SubShip(true,  coordinatesba3, "H","BA4", true);
        battleship.addToSubShipPartList(battleshippart1);
        battleship.addToSubShipPartList(battleshippart2);
        battleship.addToSubShipPartList(battleshippart3);
        royalNavy.addToShips(battleship);

        Coordinate coordinatesca = new Coordinate(String.valueOf(9).concat(";").concat(String.valueOf(5)));
        Coordinate coordinatesca1 = new Coordinate(String.valueOf(9).concat(";").concat(String.valueOf(6)));
        Coordinate coordinatesca2 = new Coordinate(String.valueOf(9).concat(";").concat(String.valueOf(7)));
        Coordinate coordinatesca3 = new Coordinate(String.valueOf(9).concat(";").concat(String.valueOf(8)));
        Coordinate coordinatesca4 = new Coordinate(String.valueOf(9).concat(";").concat(String.valueOf(9)));
        MainShip carriership = new MainShip(true,  coordinatesca, "H","CA1", false, "carrier");
        SubShip carriershippart1 = new SubShip(true,  coordinatesca1, "H","CA2", false);
        SubShip carriershippart2 = new SubShip(true,  coordinatesca2, "H","CA3", false);
        SubShip carriershippart3 = new SubShip(true,  coordinatesca3, "H","CA4", false);
        SubShip carriershippart4 = new SubShip(true,  coordinatesca4, "H","CA5", false);
        carriership.addToSubShipPartList(carriershippart1);
        carriership.addToSubShipPartList(carriershippart2);
        carriership.addToSubShipPartList(carriershippart3);
        carriership.addToSubShipPartList(carriershippart4);
        royalNavy.addToShips(carriership);

        royalNavy.getTable()[0][0]=destroyership;
        royalNavy.getTable()[0][1]=destroyershippart;
        royalNavy.getTable()[0][3]=submarineship;
        royalNavy.getTable()[0][4]=submarineship1;
        royalNavy.getTable()[0][5]=submarineship2;
        royalNavy.getTable()[5][5]=cruisership;
        royalNavy.getTable()[5][6]=cruisershippart1;
        royalNavy.getTable()[5][7]=cruisershippart2;
        royalNavy.getTable()[7][6]=battleship;
        royalNavy.getTable()[7][7]=battleshippart1;
        royalNavy.getTable()[7][8]=battleshippart2;
        royalNavy.getTable()[7][9]=battleshippart3;
        royalNavy.getTable()[9][5]=carriership;
        royalNavy.getTable()[9][6]=carriershippart1;
        royalNavy.getTable()[9][7]=carriershippart2;
        royalNavy.getTable()[9][8]=carriershippart3;
        royalNavy.getTable()[9][9]=carriershippart4;

        return royalNavy;
    }

    /**
     * The "enemyTestTable" method creates an totally presetted testtable for the first player's opponent
     */

    public static Table enemyTestTable() {

        Table kaiserlicheMarine = new Table("kaiserlicheMarine", 10);
        kaiserlicheMarine.setName("kaiserlicheMarine");

        Coordinate coordinatesde = new Coordinate(String.valueOf(0).concat(";").concat(String.valueOf(0)));
        Coordinate coordinatesde1 = new Coordinate(String.valueOf(0).concat(";").concat(String.valueOf(1)));
        MainShip destroyership = new MainShip(true,  coordinatesde, "H","DE1", true, "destroyer");
        SubShip destroyershippart = new SubShip(true,  coordinatesde1, "H","DE2", true);
        destroyership.addToSubShipPartList(destroyershippart);
        kaiserlicheMarine.addToShips(destroyership);

        Coordinate coordinates = new Coordinate(String.valueOf(1).concat(";").concat(String.valueOf(3)));
        Coordinate coordinates1 = new Coordinate(String.valueOf(1).concat(";").concat(String.valueOf(4)));
        Coordinate coordinates2 = new Coordinate(String.valueOf(1).concat(";").concat(String.valueOf(5)));
        MainShip submarineship = new MainShip(true,  coordinates, "H","SM1", true, "submarine");
        SubShip submarineship1 = new SubShip(true,  coordinates1, "H","SM2", true);
        SubShip submarineship2 = new SubShip(true, coordinates2, "H", "SM3", true);
        submarineship.addToSubShipPartList(submarineship1);
        submarineship.addToSubShipPartList(submarineship2);
        kaiserlicheMarine.addToShips(submarineship);

        Coordinate coordinatescr = new Coordinate(String.valueOf(3).concat(";").concat(String.valueOf(5)));
        Coordinate coordinatescr1 = new Coordinate(String.valueOf(3).concat(";").concat(String.valueOf(6)));
        Coordinate coordinatescr2 = new Coordinate(String.valueOf(3).concat(";").concat(String.valueOf(7)));
        MainShip cruisership = new MainShip(true,  coordinatescr,"H", "CR1", true, "cruiser");
        SubShip cruisershippart1 = new SubShip(true,  coordinatescr1,  "H","CR2", true);
        SubShip cruisershippart2 = new SubShip(true,  coordinatescr2, "H", "CR3", true);
        cruisership.addToSubShipPartList(cruisershippart1);
        cruisership.addToSubShipPartList(cruisershippart2);
        //kaiserlicheMarine.addToShips(cruisership);

        Coordinate coordinatesba = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(6)));
        Coordinate coordinatesba1 = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(7)));
        Coordinate coordinatesba2 = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(8)));
        Coordinate coordinatesba3 = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(9)));
        MainShip battleship = new MainShip(true,  coordinatesba, "H","BA1", true, "battleship");
        SubShip battleshippart1 = new SubShip(true,  coordinatesba1, "H", "BA2", true);
        SubShip battleshippart2 = new SubShip(true,  coordinatesba2, "H","BA3", true);
        SubShip battleshippart3 = new SubShip(true,  coordinatesba3, "H","BA4", true);
        battleship.addToSubShipPartList(battleshippart1);
        battleship.addToSubShipPartList(battleshippart2);
        battleship.addToSubShipPartList(battleshippart3);
        //kaiserlicheMarine.addToShips(battleship);

        Coordinate coordinatesca = new Coordinate(String.valueOf(4).concat(";").concat(String.valueOf(0)));
        Coordinate coordinatesca1 = new Coordinate(String.valueOf(5).concat(";").concat(String.valueOf(0)));
        Coordinate coordinatesca2 = new Coordinate(String.valueOf(6).concat(";").concat(String.valueOf(0)));
        Coordinate coordinatesca3 = new Coordinate(String.valueOf(7).concat(";").concat(String.valueOf(0)));
        Coordinate coordinatesca4 = new Coordinate(String.valueOf(8).concat(";").concat(String.valueOf(0)));
        MainShip carriership = new MainShip(true,  coordinatesca, "V","CA1", false, "carrier");
        SubShip carriershippart1 = new SubShip(true,  coordinatesca1,"V", "CA2", false);
        SubShip carriershippart2 = new SubShip(true,  coordinatesca2, "V","CA3", false);
        SubShip carriershippart3 = new SubShip(true,  coordinatesca3, "V","CA4", false);
        SubShip carriershippart4 = new SubShip(true,  coordinatesca4, "V","CA5", false);
        carriership.addToSubShipPartList(carriershippart1);
        carriership.addToSubShipPartList(carriershippart2);
        carriership.addToSubShipPartList(carriershippart3);
        carriership.addToSubShipPartList(carriershippart4);
        //kaiserlicheMarine.addToShips(carriership);

        kaiserlicheMarine.getTable()[0][0]=destroyership;
        kaiserlicheMarine.getTable()[0][1]=destroyershippart;

        kaiserlicheMarine.getTable()[1][3]=submarineship;
        kaiserlicheMarine.getTable()[1][4]=submarineship1;
        kaiserlicheMarine.getTable()[1][5]=submarineship2;
        /*
        kaiserlicheMarine.getTable()[3][5]=cruisership;
        kaiserlicheMarine.getTable()[3][6]=cruisershippart1;
        kaiserlicheMarine.getTable()[3][7]=cruisershippart2;
        kaiserlicheMarine.getTable()[7][6]=battleship;
        kaiserlicheMarine.getTable()[7][7]=battleshippart1;
        kaiserlicheMarine.getTable()[7][8]=battleshippart2;
        kaiserlicheMarine.getTable()[7][9]=battleshippart3;
        kaiserlicheMarine.getTable()[4][0]=carriership;
        kaiserlicheMarine.getTable()[5][0]=carriershippart1;
        kaiserlicheMarine.getTable()[6][0]=carriershippart2;
        kaiserlicheMarine.getTable()[7][0]=carriershippart3;
        kaiserlicheMarine.getTable()[8][0]=carriershippart4;
        */
        return kaiserlicheMarine;
    }
}
