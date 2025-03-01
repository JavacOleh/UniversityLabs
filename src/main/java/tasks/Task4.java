package tasks;

import common.ParseService;

public class Task4 {
    private final ParseService parseService;

    public Task4(ParseService parseService) {
        this.parseService = parseService;
    }

    public void execute() {
        System.out.println("Please enter digits, enter 0 or value >= 9 to exit");
        int temp = parseService.getParsedInt(Integer.MAX_VALUE - 1, 0);;
        int min = temp;
        do {
            if(temp >= 9 || temp == 0)
                break;
            else
                min = temp < min ? temp : min;

            temp = parseService.getParsedInt(Integer.MAX_VALUE - 1, 0);

        }while (true);

        System.out.println("The min: " + min);
    }

    /*
    Створіть функцію, що виконує пошук мінімального серед введених додатних чисел.
     Умовою завершення вводу вважати введення 0 або значення <=9.
    */
}
