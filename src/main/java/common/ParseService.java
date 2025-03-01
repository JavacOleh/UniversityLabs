package common;

import java.util.Scanner;

//Not done!
public class ParseService {
    public Scanner scanner;

    public ParseService(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getParsedInt() {
        String parsedValue;
        int temp;

        do {
            parsedValue = scanner.nextLine();

            try {
                temp = Integer.parseInt(parsedValue);
                return temp;

            }catch (NumberFormatException e) {
                System.out.println("Please enter a digit!");
            }

        } while (true);
    }

    public int getParsedInt(int maxValue, int minValue) {
        String parsedValue;
        int temp;

        do {
            parsedValue = scanner.nextLine();

            try {
                temp = Integer.parseInt(parsedValue);
                if (temp > maxValue || temp < minValue)
                    System.out.println("Please enter a digit lower than " + maxValue + " and bigger than " + minValue + "!");
                else
                    return temp;

            }catch (NumberFormatException e) {
                System.out.println("Please enter a digit!");
            }

        }while (true);
    }
    public int getParsedIntByStringValue(String value) {
        int temp;

        do {
            try {
                temp = Integer.parseInt(value);
                return temp;

            }catch (NumberFormatException e) {
                System.out.println("Please enter a digit!");
                value = scanner.nextLine();
            }

        } while (true);
    }
    public int getParsedIntByStringValue(String value, int maxValue, int minValue) {
        int temp;
        do {

            try {
                temp = Integer.parseInt(value);
                if (temp > maxValue || temp < minValue)
                    System.out.println("Please enter a digit lower than " + maxValue + " and bigger than " + minValue + "!");
                else
                    return temp;

            }catch (NumberFormatException e) {
                System.out.println("Please enter a digit!");
                value = scanner.nextLine();
            }

        }while (true);
    }

    public double getParsedDouble() {
        String parsedValue;
        double temp;

        do {
            parsedValue = scanner.nextLine();

            try {
                temp = Double.parseDouble(parsedValue);
                return temp;

            }catch (NumberFormatException e) {
                System.out.println("Please enter a digit!");
            }

        } while (true);
    }

    public double getParsedDouble(double maxValue, double minValue) {
        String parsedValue;
        double temp;

        do {
            parsedValue = scanner.nextLine();

            try {
                temp = Double.parseDouble(parsedValue);
                if (temp > maxValue || temp < minValue)
                    System.out.println("Please enter a digit lower than " + maxValue + " and bigger than " + minValue + "!");
                else
                    return temp;

            }catch (NumberFormatException e) {
                System.out.println("Please enter a digit!");
            }

        }while (true);
    }
}
