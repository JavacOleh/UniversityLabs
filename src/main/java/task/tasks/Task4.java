package task.tasks;

import consoledInterface.ApplicationInit;
import consoledInterface.util.ParseUtil;
import static consoledInterface.controller.sub.output.Cout.cout;

public class Task4 {
    private final ParseUtil parseUtil;

    public Task4(ParseUtil parseUtil) {
        this.parseUtil = parseUtil;
    }

    public void execute() {
        cout("Please enter digits, enter 0 or value >= 9 to exit", ApplicationInit.textColor);
        int temp = parseUtil.getParsedInt(Integer.MAX_VALUE - 1, 0);;
        int min = temp;
        do {
            if(temp >= 9 || temp == 0)
                break;
            else
                min = temp < min ? temp : min;

            temp = parseUtil.getParsedInt(Integer.MAX_VALUE - 1, 0);

        }while (true);

        cout("The min: " + min, ApplicationInit.textColor);
    }

    /*
    Створіть функцію, що виконує пошук мінімального серед введених додатних чисел.
     Умовою завершення вводу вважати введення 0 або значення <=9.
    */
}
