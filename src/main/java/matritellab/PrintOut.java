package matritellab;

public class PrintOut {

    /**
     * The "mixTheGridStrings" method gets the String representations of the players' Tables and mix them
     * to a third String containing both Tables' lines
     */

    public static void mixTheGridStrings(String yourTable, String enemyTable) {

        String[] yourTableArr = yourTable.split("\n");
        String[] enemyTableArr = enemyTable.split("\n");

        StringBuilder mix = new StringBuilder();

        for (int i = 0; i < yourTableArr.length; i++) {
            mix.append(yourTableArr[i]);
            mix.append("        ");
            mix.append(enemyTableArr[i]);
            mix.append("\n");
        }
        System.out.println(mix.toString());
    }

    /**
     * The "createPlayersGridInString" method creates the String representations of the player's Table
     * containing all data of the player's objects without any restriction. For this reason the players
     * may see all of their own objects regardless its status (intact, damaged and sunk as well).
     */

    public static String createPlayersGridInString(Table nameTable) {
        String space = "";
        for (int i = 0; i < (nameTable.getTableSize() / 2); i++) {
            space = space.concat("     ");
        }

        String ret = space + nameTable.getName().toUpperCase() + space.concat("\n");
        ret = ret.concat("\n");
        String headline = "";
        for (int i = 0; i < nameTable.getTable().length; i++) {
            if (i < 10) {
                headline = headline.concat("     ").concat(String.valueOf(i));
            } else {
                headline = headline.concat("    ").concat(String.valueOf(i));
            }
        }
        ret = ret.concat(headline + "\n");

        String headline2 = "  ";
        for (int i = 0; i < nameTable.getTable().length; i++) {
            headline2 = headline2.concat("------");
        }
        ret = ret.concat(headline2 + "\n");

        for (int i = 0; i < nameTable.getTable().length; i++) {
            ret = ret.concat(String.valueOf(i));
            if (i < 10) {
                ret = ret.concat(" ");
            }
            for (int j = 0; j < nameTable.getTable()[i].length; j++) {

                if (nameTable.getTable()[i][j] == null) {
                    ret = ret.concat("|     ");
                } else if (nameTable.getTable()[i][j] instanceof MissedTarget) {
                    ret = ret.concat("| ".concat(nameTable.getTable()[i][j].getName()).concat(" "));
                } else if (String.valueOf(nameTable.getTable()[i][j].getName().charAt(0)).equals("_")) {
                    ret = ret.concat("| ").concat(nameTable.getTable()[i][j].getName());
                } else {
                    ret = ret.concat("| ".concat(nameTable.getTable()[i][j].getName()).concat(" "));
                }
            }

            ret = ret.concat("|");
            ret = ret.concat("\n");
            ret = ret.concat(headline2);
            ret = ret.concat("\n");
            ret = ret.concat("\n");
        }
        return ret;
    }

    /**
     * The "createEnemysGridInString" method creates the String representations of the player's opponent's
     * Table containing data of the opponent's objects with restrictions.
     * For this reason the players can see in this representation only the damaged MainShips and SubShips
     * hitted before. The intact units of the opponent can't be see in this representation
     */

    public static String createEnemysGridInString(Table nameTable) {

        String space = "";
        String ret = "";
        for (int i = 0; i < (nameTable.getTableSize() / 2); i++) {
            space = space.concat("     ");
        }
        if (nameTable.getName().length() < 10) {
            ret = space + space + nameTable.getName().toUpperCase() + space.concat("\n");
        } else {
            ret = space + nameTable.getName().toUpperCase() + space.concat("\n");
        }
        ret = ret.concat("\n");
        String headline = "  ";
        for (int i = 0; i < nameTable.getTable().length; i++) {
            if (i < 10) {
                headline = headline.concat("     ").concat(String.valueOf(i));
            } else {
                headline = headline.concat("    ").concat(String.valueOf(i));
            }
        }
        ret = ret.concat(headline + "\n");

        String headline2 = "  ";
        for (int i = 0; i < nameTable.getTable().length; i++) {
            headline2 = headline2.concat("------");
        }
        ret = ret.concat(headline2 + "\n");

        for (int i = 0; i < nameTable.getTable().length; i++) {
            ret = ret.concat(String.valueOf(i));

            if (i < 10) {
                ret = ret.concat(" ");
            }
            for (int j = 0; j < nameTable.getTable()[i].length; j++) {

                if (nameTable.getTable()[i][j] == null) {
                    ret = ret.concat("|     ");
                } else if (!(nameTable.getTable()[i][j] instanceof MissedTarget) && (String.valueOf(nameTable.getTable()[i][j].getName().charAt(2)).equals("0") || String.valueOf(nameTable.getTable()[i][j].getName().charAt(2)).equals("X"))) {
                    ret = ret.concat("|  ".concat(String.valueOf(nameTable.getTable()[i][j].getName().charAt(2))).concat("  "));
                } else if (String.valueOf(nameTable.getTable()[i][j].getName().charAt(0)).equals("_")) {
                    ret = ret.concat("| ").concat(nameTable.getTable()[i][j].getName());
                } else if (nameTable.getTable()[i][j] instanceof MissedTarget) {
                    ret = ret.concat("|  0  ");
                } else {
                    ret = ret.concat("|     ");
                }
            }
            ret = ret.concat("|");
            ret = ret.concat("\n");
            ret = ret.concat(headline2);
            ret = ret.concat("\n");
            ret = ret.concat("\n");
        }
        return ret;
    }
}