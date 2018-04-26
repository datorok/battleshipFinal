package matritellab;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static matritellab.Game.fire;
import static matritellab.Table.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("This is a battleship game");
        System.out.println("You have 5 ships marked with different signs:");
        System.out.println("Carrier (5 unit long): CA1; CA2; CA3; CA4; CA5");
        System.out.println("Battleship (4 unit long): BA1; BA2; BA3; BA4");
        System.out.println("Cruiser (3 unit long): CR1; CR2; CR3");
        System.out.println("Submarine (3 unit long): SU1; SU2; SU3");
        System.out.println("Destroyer (2 unit long): DE1; DE2");
        System.out.println("Your hits will be marked with \"X\" sign on your enemy's grid");
        System.out.println("If you miss the target, you will see an \"0\" sign in the grid");
        System.out.println("You can set your tables (real) or you can use preset tables (test)");
        System.out.println("Press 1 for real play or any other number for test game:");
        String chosenString = sc.nextLine();

        while (!tableSizeParse(chosenString)){
            System.out.println("The number of the chosen option must be provided in number format. Provide it again: ");
            chosenString=sc.nextLine();
        }
        int choosenInt = Integer.parseInt(chosenString);


        if (choosenInt == 1) {

            System.out.println("Provide the size of the table: ");
            String tableSizeString=sc.nextLine();
            while (!tableSizeParse(tableSizeString)){
                System.out.println("The tableSize data must be provided in number format. Provide again: ");
                tableSizeString=sc.nextLine();
            }
            int tableSize = Integer.parseInt(tableSizeString);

            if (tableSize>100){

                System.out.println("If you have so much time, do something meaningful instead of wasting your time playing!");
                System.exit(0);
            }

            Table daniTable = initialisation(tableSize);
            Table sanyiTable = initialisation(tableSize);

            while (!daniTable.isLost() && !sanyiTable.isLost()) {

                fire(daniTable, sanyiTable);
                System.out.println("YOUR TURN IS OVER! PASS THE LAPTOP TO YOUR OPPONENT WITHIN 5 SECOND!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fire(sanyiTable, daniTable);
                System.out.println("YOUR TURN IS OVER! PASS THE LAPTOP TO YOUR OPPONENT WITHIN 5 SECOND!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else {

            System.out.println("Welcome to Jutland!");
            System.out.println("Today is  31.05.1916");

            Table royalNavy = yourTestTable();
            Table kaiserlicheMarine = enemyTestTable();

            while (!royalNavy.isLost() && !kaiserlicheMarine.isLost()) {

                fire(royalNavy, kaiserlicheMarine);
                System.out.println("YOUR TURN IS OVER! PASS THE LAPTOP TO YOUR OPPONENT WITHIN 5 SECOND!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fire(kaiserlicheMarine, royalNavy);
                if (royalNavy.isLost() || kaiserlicheMarine.isLost()) {
                    break;
                }
                System.out.println("YOUR TURN IS OVER! PASS THE LAPTOP TO YOUR OPPONENT WITHIN 5 SECOND!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
