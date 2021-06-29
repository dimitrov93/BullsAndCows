import java.util.Random;
import java.util.Scanner;

public class bAcVer2 {
    static String userOneName, userTwoName;
    static int bull, cow;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuOption();
        byte userOption = scanner.nextByte();

        int randomNumberForUserOne = randomNumberFromComputer();
        int randomNumberForUserTwo = randomNumberFromComputer();

        System.out.println("Random number 1 made from the computer: " + randomNumberForUserOne);
        System.out.println("Random number 2 made from the computer: " + randomNumberForUserTwo);
        switchMenuOptions(userOption, numberToArray(randomNumberForUserOne), numberToArrayTwo(randomNumberForUserTwo));
    }

    public static void menuOption() {
        System.out.println("Welcome to the game \"Bulls and Cows\" ");
        System.out.println("1. Single player");
        System.out.println("2. Multi Player");
        System.out.println("3. Exit");
        System.out.print("Select option from the menu: ");

    }

    public static void switchMenuOptions(byte userOption, int[] randomNumberForUserOneArray, int[] randomNumberForUserTwoArray) {
        switch (userOption) {
            case 1 -> singlePlayerOption(randomNumberForUserOneArray);
            case 2 -> multiPlayerOption(randomNumberForUserOneArray, randomNumberForUserTwoArray);
            default -> System.out.println("Have a great day!");
        }
    }

    public static int randomNumberFromComputer() {
        Random rand = new Random();
        while (true) {
            int n = rand.nextInt(9000) + 1000;
            int a = n / 1000; // 1 number
            int b = (n / 100) % 10; // 2 number
            int c = (n / 10) % 10; // 3 number
            int d = n % 10; // 4 number
            if (a == b || a == c || a == d || b == c || b == d || c == d) {
                continue;
            } else {
                return n;
            }
        }
    }

    public static int[] numberToArray(int randomNumberForUserOne) {
        String numberToArray = Integer.toString(randomNumberForUserOne);
        int[] randomNumberForUserOneArray = new int[numberToArray.length()];
        for (int i = 0; i < numberToArray.length(); i++) {
            randomNumberForUserOneArray[i] = numberToArray.charAt(i) - '0';
        }
        return randomNumberForUserOneArray;
    }

    public static int[] numberToArrayTwo(int randomNumberForUserTwo) {
        String numberToArray = Integer.toString(randomNumberForUserTwo);
        int[] randomNumberForUserTwoArray = new int[numberToArray.length()];
        for (int i = 0; i < numberToArray.length(); i++) {
            randomNumberForUserTwoArray[i] = numberToArray.charAt(i) - '0';
        }
        return randomNumberForUserTwoArray;
    }

    public static int[] userNumber() {
        Scanner scanner = new Scanner(System.in);
        int userNumber = scanner.nextInt();

        while (true) {
            int a = (userNumber) / 1000; // 1 number
            int b = (userNumber / 100) % 10; // 2 number
            int c = (userNumber / 10) % 10; // 3 number
            int d = userNumber % 10; // 4 number
            if (userNumber < 1000 || userNumber > 10000) {
                System.out.println("Incorrect number");
                userNumber = scanner.nextInt();
            } else if (a == b || a == c || a == d || b == c || b == d || c == d) {
                System.out.println("Incorrect number");
                userNumber = scanner.nextInt();
            } else break;
        }
        String temp = Integer.toString(userNumber);
        int[] userNumberArray = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            userNumberArray[i] = temp.charAt(i) - '0';
        }
        return userNumberArray;
    }

    public static void singlePlayerOption(int[] randomNumberForUserOneArray) {
        System.out.println("Enter number: ");

        do {
            bull = 0; cow = 0;
            int[] userFinalNumber = userNumber();

            for (int i = 0; i < randomNumberForUserOneArray.length; i++) {
                if (randomNumberForUserOneArray[i] == userFinalNumber[i]) {
                    bull++;
                }
                for (int j = userFinalNumber.length - 1; j >= 0; j--) {
                    if (randomNumberForUserOneArray[i] == userFinalNumber[j] && randomNumberForUserOneArray[i] != userFinalNumber[i]) {
                        cow++;
                    }
                }
            }
            resultForBullsAndCows(bull, cow);
        } while (bull != 4);
    }

    public static void userNames() {
        Scanner in = new Scanner(System.in);
        System.out.println("User 1, enter your name: ");
        userOneName = in.nextLine();
        System.out.println("User 2, enter your name: ");
        userTwoName = in.nextLine();
    }

    public static void multiPlayerOption(int[] randomNumberForUserOne, int[] randomNumberForUserTwo) {
        userNames();
        while (multiPlayerUserOne(randomNumberForUserOne, randomNumberForUserTwo) < 1) {

            multiPlayerUserOne(randomNumberForUserOne, randomNumberForUserTwo);
            if (multiPlayerUserTwo(randomNumberForUserTwo, randomNumberForUserOne) > 1) {
                break;
            }
            multiPlayerUserTwo(randomNumberForUserTwo, randomNumberForUserOne);
        }
    }

    public static int multiPlayerUserOne(int[] randomNumberForUserOneArray, int[] randomNumberForUserTwoArray) {
        System.out.println(userOneName + ", enter number: ");
        int[] userFinalNumber = userNumber();
        do {
            bull = 0; cow = 0;

            for (int i = 0; i < randomNumberForUserOneArray.length; i++) {
                if (randomNumberForUserOneArray[i] == userFinalNumber[i]) {
                    bull++;
                }
                for (int j = userFinalNumber.length - 1; j >= 0; j--) {
                    if (randomNumberForUserOneArray[i] == userFinalNumber[j] && randomNumberForUserOneArray[i] != userFinalNumber[i]) {
                        cow++;
                    }
                }
            }

            resultForBullsAndCows(bull, cow);
            if (bull == 4) {
                System.out.println("Well played, " + userOneName + "! You WIN! ");
                break;
            } else {
                multiPlayerUserTwo(randomNumberForUserTwoArray, randomNumberForUserOneArray);
                break;
            }

        } while (true);
        return 1;
    }

    public static int multiPlayerUserTwo(int[] randomNumberForUserTwo, int[] randomNumberForUserOne) {
        System.out.println(userTwoName + ", enter number: ");
        int[] userFinalNumber = userNumber();

        do {
            bull = 0; cow = 0;

            for (int i = 0; i < randomNumberForUserTwo.length; i++) {
                if (randomNumberForUserTwo[i] == userFinalNumber[i]) {
                    bull++;
                }
                for (int j = userFinalNumber.length - 1; j >= 0; j--) {
                    if (randomNumberForUserTwo[i] == userFinalNumber[j] && randomNumberForUserTwo[i] != userFinalNumber[i]) {
                        cow++;
                    }
                }
            }

            resultForBullsAndCows(bull, cow);
            if (bull == 4) {
                System.out.println("Well played, " + userTwoName + "! You WIN! ");
                break;
            } else {
                multiPlayerUserOne(randomNumberForUserOne, randomNumberForUserTwo);
                break;
            }

        } while (true);
        return 1;
    }

    public static void resultForBullsAndCows(int bull, int cow) {
        if (bull == 1 && cow != 1) {
            System.out.println("You have " + bull + " bull and " + cow + " cows");
        } else if (bull != 1 && cow == 1) {
            System.out.println("You have " + bull + " bulls and " + cow + " cow");
        } else if (bull == 1 && cow == 1) {
            System.out.println("You have " + bull + " bull and " + cow + " cow");
        } else {
            System.out.println("You have " + bull + " bulls and " + cow + " cows");
        }

    }
}
