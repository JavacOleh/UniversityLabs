package task.tasks;

import consoledInterface.ApplicationInit;
import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;

import java.text.MessageFormat;
import java.util.*;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

public class Task6 {

    private final ParseUtil parseUtil;
    private final Cin cin;
    private List<Integer> integers;
    private int indexOfMaxElement;
    private int elementMax;
    private boolean executedVar3;

    public Task6(ParseUtil parseUtil) {
        this.parseUtil = parseUtil;
        integers = new ArrayList<>();
        cin = ApplicationInit.getIO().getInputController().getCin();
    }

    public void execute() {
        int choice;
        cout("Welcome to task 6!", ApplicationInit.textColor);
        int exitChoice = 5;
        do {
            cout(MessageFormat.format("""
                    Please select operation:
                    1.Enter digits to array
                    2.Let program to enter 10 digits to array itself
                    3.Execute task
                    4.Show details
                    {0}.Exit from task
                    """, exitChoice), ApplicationInit.textColor);

            choice = parseUtil.getParsedInt(exitChoice, 0);

            if (choice < exitChoice) {
                operationExecutor(choice);
                system("pause");
                system("cls");
            }

        } while (choice < exitChoice);
    }

    public void operationExecutor(int choice) {
        switch (choice) {
            case 1 -> {
                String temp;
                cout("Please enter digits or enter 'exit' to exit", ApplicationInit.textColor);
                do {
                    temp = cin.getLine();

                    if (!temp.contains("exit")) {
                        var temp2 = parseUtil.getParsedIntByStringValue(temp);
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
                    cout("Please choose operation 1 or 2 for first, and only then choose operation 3.", ApplicationInit.textColor);
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
                    cout("Please choose operation 1 or 2 for first, and only then choose operation 3.", ApplicationInit.textColor);
                    return;
                }
                cout(MessageFormat.format("""
                                The array: {0}
                                """,
                        integers.toString()
                ), ApplicationInit.textColor);

                if (executedVar3) {
                    cout(MessageFormat.format("""
                                    The max element: {0}
                                    The index of max element: {1}
                                    """,
                            elementMax,
                            indexOfMaxElement
                    ), ApplicationInit.textColor);

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
