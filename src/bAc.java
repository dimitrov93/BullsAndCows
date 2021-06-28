import java.util.Random;
import java.util.Scanner;

public class bAc {
    static int bull, cow;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        menuOption();

        byte userOption = scanner.nextByte();
        int randomNumberForUserOne = randomNumberFromComputer();
        int randomNumberForUserTwo = randomNumberFromComputer();

        System.out.println("Random number 1 made from the computer: " + randomNumberForUserOne);
        System.out.println("Random number 2 made from the computer: " + randomNumberForUserTwo);
        switchMenuOptions(userOption, randomNumberForUserOne, randomNumberForUserTwo);

    }

    public static void menuOption() {
        System.out.println("Welcome to the game \"Bulls and Cows\" ");
        System.out.println("1. Single player");
        System.out.println("2. Multi Player");
        System.out.println("3. Exit");
        System.out.print("Select option from the menu: ");

    }

    public static void switchMenuOptions (byte userOption, int randomNumberForUserOne, int randomNumberForUserTwo) {
        switch (userOption) {
            case 1 -> singlePlayerOption(randomNumberForUserOne);
            case 2 -> multiPlayerOption(randomNumberForUserOne, randomNumberForUserTwo);
            default -> System.out.println("Have a great day!");
        }
    }

    public static int randomNumberFromComputer() {
        Random rand  = new Random ();
        while (true) {
            int n  = rand.nextInt(9000) + 1000;
            int a = n / 1000; // 1 number
            int b = (n / 100) % 10; // 2 number
            int c =  (n / 10) % 10; // 3 number
            int d = n % 10; // 4 number
            if (a == b || a == c || a == d  || b == c  || b == d  || c == d ) {
                continue;
            } else {
                return n;
            }
        }
    }

    public static int userNumber() {
        Scanner scanner = new Scanner(System.in);
        int userNumber = scanner.nextInt();
        while (true) {
            int a = (userNumber) / 1000 ; // 1 number
            int b = (userNumber / 100) % 10; // 2 number
            int c =  (userNumber / 10) % 10; // 3 number
            int d = userNumber % 10; // 4 number
            if (userNumber < 1000 || userNumber > 10000 ) {
                System.out.println("Incorrect number");
                userNumber = scanner.nextInt();
            }
            else if (a == b || a == c || a == d  || b == c  || b == d  || c == d)  {
                System.out.println("Incorrect number");
                userNumber = scanner.nextInt();
            }
            else return userNumber;
        }
    }

    public static void countBullsAndCows() {
        if (bull==1 && cow != 1) {
            System.out.println("You have " + bull + " bull and " + cow + " cows");
        } else if (bull != 1 && cow == 1) {
            System.out.println("You have " + bull + " bulls and " + cow + " cow");
        } else if (bull == 1 && cow == 1) {
            System.out.println("You have " + bull + " bull and " + cow + " cow");
        } else {
            System.out.println("You have " + bull + " bulls and " + cow + " cows");
        }
    }

    public static void singlePlayerOption(int randomNumberForUserOne) {
        System.out.println("Enter number: ");

        do {
            bull = 0; cow = 0;
            int singlePlayerUserNumber = userNumber();
            int a1 = singlePlayerUserNumber / 1000; // 1 number
            int b1 = (singlePlayerUserNumber / 100) % 10; // 2 number
            int c1 =  (singlePlayerUserNumber / 10) % 10; // 3 number
            int d1 = singlePlayerUserNumber % 10; // 4 number

            int a2 = randomNumberForUserOne / 1000; // 1 number
            int b2 = (randomNumberForUserOne / 100) % 10; // 2 number
            int c2 =  (randomNumberForUserOne / 10) % 10; // 3 number
            int d2 = randomNumberForUserOne % 10; // 4 number

            if (a1 == a2) bull++;
            if (b1 == b2) bull++;
            if (c1 == c2) bull++;
            if (d1 == d2) bull++;

            if ((a1 == b2 || a1 == c2 || a1 == d2)) cow++;
            if ((b1 == a2 || b1 == c2 || b1 == d2)) cow++;
            if ((c1 == a2 || c1 == b2 || c1 == d2)) cow++;
            if ((d1 == a2 || d1 == b2 || d1 == c2)) cow++;

            countBullsAndCows();

            if (bull == 4) {
                System.out.println("Congratulations! You WIN! ");
                break;
            }

        } while (true);
    }

    public static void multiPlayerOption (int randomNumberForUserOne, int randomNumberForUserTwo) {
        while (multiPlayerUserOne(randomNumberForUserOne, randomNumberForUserTwo) < 1) {

            multiPlayerUserOne(randomNumberForUserOne, randomNumberForUserTwo);
            if (multiPlayerUserTwo(randomNumberForUserTwo, randomNumberForUserOne) > 1)
            {
                break;
            }
            multiPlayerUserTwo(randomNumberForUserTwo, randomNumberForUserOne);
        }
    }

    public static int multiPlayerUserOne(int randomNumberForUserOne, int randomNumberForUserTwo) {
        System.out.println("User 1, enter number: ");
        int userOneNumber = userNumber();
        do {
            bull = 0; cow = 0;

            int a1 = userOneNumber / 1000; // 1 number
            int b1 = (userOneNumber / 100) % 10; // 2 number
            int c1 =  (userOneNumber / 10) % 10; // 3 number
            int d1 = userOneNumber % 10; // 4 number

            int a2 = randomNumberForUserOne / 1000; // 1 number
            int b2 = (randomNumberForUserOne / 100) % 10; // 2 number
            int c2 =  (randomNumberForUserOne / 10) % 10; // 3 number
            int d2 = randomNumberForUserOne % 10; // 4 number

            if (a1 == a2) bull++;
            if (b1 == b2) bull++;
            if (c1 == c2) bull++;
            if (d1 == d2) bull++;

            if ((a1 == b2 || a1 == c2 || a1 == d2)) cow++;
            if ((b1 == a2 || b1 == c2 || b1 == d2)) cow++;
            if ((c1 == a2 || c1 == b2 || c1 == d2)) cow++;
            if ((d1 == a2 || d1 == b2 || d1 == c2)) cow++;

            countBullsAndCows();

            if (bull == 4) {
                System.out.println("Congratulations, User 1! You WIN! ");
                break;
            } else  {
                multiPlayerUserTwo(randomNumberForUserTwo, randomNumberForUserOne);
                break;
            }

        }
        while (randomNumberForUserOne != userOneNumber);
        return 1;
    }


    public static int multiPlayerUserTwo(int randomNumberForUserTwo, int randomNumberForUserOne) {
        System.out.println("User 2, enter number: ");
        int userTwoNumber = userNumber();

        do {
            bull = 0; cow = 0;

            int a1 = userTwoNumber / 1000; // 1 number
            int b1 = (userTwoNumber / 100) % 10; // 2 number
            int c1 =  (userTwoNumber / 10) % 10; // 3 number
            int d1 = userTwoNumber % 10; // 4 number

            int a2 = randomNumberForUserTwo / 1000; // 1 number
            int b2 = (randomNumberForUserTwo / 100) % 10; // 2 number
            int c2 =  (randomNumberForUserTwo / 10) % 10; // 3 number
            int d2 = randomNumberForUserTwo % 10; // 4 number

            if (a1 == a2) bull++;
            if (b1 == b2) bull++;
            if (c1 == c2) bull++;
            if (d1 == d2) bull++;

            if ((a1 == b2 || a1 == c2 || a1 == d2)) cow++;
            if ((b1 == a2 || b1 == c2 || b1 == d2)) cow++;
            if ((c1 == a2 || c1 == b2 || c1 == d2)) cow++;
            if ((d1 == a2 || d1 == b2 || d1 == c2)) cow++;

            countBullsAndCows();

            if (bull == 4) {
                System.out.println("Well played, User 2! You WIN! ");
                break;
            } else {
                multiPlayerUserOne(randomNumberForUserOne, randomNumberForUserTwo);
                break;
            }
        }
        while (randomNumberForUserTwo != userTwoNumber);
        return 1;
    }
}