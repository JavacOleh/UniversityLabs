package tasks;

import common.ConsoleUtil;
import common.ParseService;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Task5 {
    private final ParseService parseService;
    private final ConsoleUtil consoleUtil;
    private final ArrayList<Integer> integers;

    public Task5(ParseService parseService) {
        this.parseService = parseService;
        consoleUtil = new ConsoleUtil(parseService);
        integers = new ArrayList<>();
    }
    /*
    Маємо одновимірний масив, заповнений випадковими числами.
    На основі даних масиву потрібно створити двовимірний масив з наступних рядків:
    1 одновимірний масив, що містить лише парні числа;
    2 одновимірний масив, що містить лише ті числа масиву, що є числами Фібоначі;
    3 одновимірний масив, що містить лише від’ємні числа масиву, що більші за число -17;
    4 одновимірний масив, що містить прості числа з першого масиву
     */

    public void execute() {
        int choice;
        int exitChoice = 4;
        System.out.println("Welcome to task 5!");
        do {
            System.out.print(MessageFormat.format("""
                    Please select operation:
                    1.Enter digits to array
                    2.Let program to enter 10 digits to array itself
                    3.Execute task
                    {0}.Exit from task
                    """, exitChoice));
            choice = parseService.getParsedInt(exitChoice, 0);

            if(choice < exitChoice) {
                operationExecutor(choice);
                consoleUtil.waitAndCls();
            }

        }while (choice < exitChoice);
    }

    public void operationExecutor(int choice) {
        switch (choice) {
            case 1 -> {
                String temp;
                System.out.println("Please enter digits or enter 'exit' to exit");
                do {
                    temp = parseService.scanner.nextLine();

                    if(!temp.contains("exit")) {
                        var temp2 = parseService.getParsedIntByStringValue(temp);
                        integers.add(temp2);
                    }

                }while (!temp.contains("exit"));
            }
            case 2 -> {
                Random random = new Random();

                for (int i = 0; i < 10; i++) {
                    var tempValue = random.nextInt(-100, 100);
                    integers.add(tempValue);
                }
            }
            case 3 -> {
                if(integers.isEmpty()) {
                    System.out.println("Please choose operation 1 or 2 for first, and only then choose operation 3.");
                    return;
                }
                Integer[] array1 = integers.stream().filter(s -> s % 2 == 0).toArray(Integer[]::new);
                Integer[] array2 = getFibonacciArray();
                Integer[] array3 = integers.stream().filter(s -> s < 0 && s > -17).toArray(Integer[]::new);
                Integer[] array4 = integers.stream().filter(this::isPrime).toArray(Integer[]::new);
                var temp = MessageFormat.format("""
                        1.Одновимірний масив, що містить лише парні числа:
                        {0}
                        
                        2.Одновимірний масив, що містить лише ті числа масиву, що є числами Фібоначі:
                        {1}
                        
                        3.Одновимірний масив, що містить лише від’ємні числа масиву, що більші за число -17;
                        {2}
                        
                        4.Одновимірний масив, що містить прості числа з першого масиву
                        {3}
                        
                        5.Сгенерований або заповнений вами масив
                        {4}
                        """,
                        Arrays.toString(array1),
                        Arrays.toString(array2),
                        Arrays.toString(array3),
                        Arrays.toString(array4),
                        integers.toString()
                );
                System.out.print(temp);
            }
        }
    }

    public Integer[] getFibonacciArray() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = integers.size() - 1; i >= 2; i--) {
            if (integers.get(i) == integers.get(i - 1) + integers.get(i - 2))
                temp.add(integers.get(i));
        }

        return temp.toArray(Integer[]::new);
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false; // 1 и все отрицательные числа не простые
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // Если делится на i, то это не простое число
            }
        }
        return true;
    }
}
