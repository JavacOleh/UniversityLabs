import common.ParseService;
import tasks.*;

import java.text.MessageFormat;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final ParseService parseService = new ParseService(scanner);

    public static void main(String[] args) {
        int choice;
        int exitChoice = 7;
        System.out.println("Welcome to my application!");
        do {
            System.out.print(MessageFormat.format("""
                    ------------Main menu------------
                    please select operation:
                    1.Task 1
                    2.Task 2
                    3.Task 3
                    4.Task 4
                    5.Task 5
                    6.Task 6
                    {0}.Exit from application
                    """, exitChoice));

            choice = parseService.getParsedInt(exitChoice, 1);
            taskExecuter(choice);

            if (choice < exitChoice) {
                System.out.println("\nPress any key to continue.");
                scanner.nextLine();

                System.out.print("\n".repeat(10));
            }else
                System.out.println("Goodbye, thanks for using my application.");
        } while (choice < exitChoice);
    }

    public static void taskExecuter(int choice) {
        switch (choice) {
            case 1: {
                Task1 task1 = new Task1(parseService);
                task1.execute();
                break;
            }

            case 2: {
                Task2 task2 = new Task2(parseService);
                task2.execute();
                break;
            }

            case 3: {
                Task3 task3 = new Task3(parseService);
                task3.execute();
                break;
            }

            case 4: {
                Task4 task4 = new Task4(parseService);
                task4.execute();
                break;
            }

            case 5: {
                Task5 task5 = new Task5(parseService);
                task5.execute();
                break;
            }
            case 6: {
                Task6 task6 = new Task6(parseService);
                task6.execute();
                break;
            }
        }
    }
}
