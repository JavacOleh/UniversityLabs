package tasks;

import common.ConsoleUtil;
import common.ParseService;

import java.text.MessageFormat;
import java.util.*;

public class Task6 {
    private final ParseService parseService;
    private final ConsoleUtil consoleUtil;
    private List<Integer> integers;
    private int indexOfMaxElement;
    private int elementMax;
    private boolean executedVar3;

    public Task6(ParseService parseService) {
        this.parseService = parseService;
        consoleUtil = new ConsoleUtil(parseService);
        integers = new ArrayList<>();
    }

    public void execute() {
        int choice;
        System.out.println("Welcome to task 6!");
        int exitChoice = 5;
        do {
            System.out.print(MessageFormat.format("""
                    Please select operation:
                    1.Enter digits to array
                    2.Let program to enter 10 digits to array itself
                    3.Execute task
                    4.Show details
                    {0}.Exit from task
                    """, exitChoice));
            choice = parseService.getParsedInt(exitChoice, 0);

            if (choice < exitChoice) {
                operationExecutor(choice);
                consoleUtil.waitAndCls();
            }

        } while (choice < exitChoice);
    }

    public void operationExecutor(int choice) {
        switch (choice) {
            case 1 -> {
                String temp;
                System.out.println("Please enter digits or enter 'exit' to exit");
                do {
                    temp = parseService.scanner.nextLine();

                    if (!temp.contains("exit")) {
                        var temp2 = parseService.getParsedIntByStringValue(temp);
                        integers.add(temp2);
                    }

                } while (!temp.contains("exit"));
                executedVar3 = false;
            }
            case 2 -> {
                Random random = new Random();
                integers.clear();

                for (int i = 0; i < 10; i++) {
                    var tempValue = random.nextInt(-100, 100);
                    if (!integers.contains(tempValue))
                        integers.add(tempValue);
                }
                executedVar3 = false;
            }

            case 3 -> {
                if (integers.isEmpty()) {
                    System.out.println("Please choose operation 1 or 2 for first, and only then choose operation 3.");
                    return;
                }
                indexOfMaxElement = integers.indexOf(Collections.max(integers));
                elementMax = Collections.max(integers);

                if (indexOfMaxElement == integers.size() - 1) {
                    integers.sort(Comparator.reverseOrder());
                } else if (indexOfMaxElement == 0) {
                    integers.sort(Comparator.naturalOrder());
                } else {
                    var firstHalf = integers.subList(0, indexOfMaxElement);
                    var secondHalf = integers.subList(indexOfMaxElement + 1, integers.size());
                    firstHalf.sort(Comparator.naturalOrder());
                    secondHalf.sort(Comparator.reverseOrder());
                    integers = new ArrayList<>(firstHalf.size() + secondHalf.size() + 1);
                    integers.addAll(firstHalf);
                    integers.add(elementMax);
                    integers.addAll(secondHalf);
                }
                executedVar3 = true;
            }

            case 4 -> {
                if (integers.isEmpty()) {
                    System.out.println("Please choose operation 1 or 2 for first, and only then choose operation 3.");
                    return;
                }

                System.out.print(MessageFormat.format("""
                                The array: {0}
                                """,
                        integers.toString()
                        ));

                if (executedVar3) {
                    System.out.print(MessageFormat.format("""
                                    The max element: {0}
                                    The index of max element: {1}
                                    """,
                            elementMax,
                            indexOfMaxElement
                    ));
                }

            }
        }
    }

    /*
    Написати метод, який у переданому рядку чисел знаходить
     найбільший елемент і впорядкувати всі елементи у правильному порядку відносно до найбільшого,
     тобто якщо існує рядок {1.2; 0.5; 7.0; 2.6; 5.0},
     то після впорядкування має вийти рядок {0.5; 1.2; 7.0; 5.0; 2.6}
     */
}
