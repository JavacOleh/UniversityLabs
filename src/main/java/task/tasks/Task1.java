package task.tasks;
import consoledInterface.util.ParseUtil;
import consoledInterface.ApplicationInit;

import static consoledInterface.controller.sub.output.Cout.cout;

public class Task1 {
    private final ParseUtil parseUtil;

    public Task1(ParseUtil parseUtil) {
        this.parseUtil = parseUtil;
    }

    /*
Користувач вводить з клавіатури два числа.
 Потрібно вивести всі непарні числа у вказаному діапазоні.
 Якщо межі вказані не вірно потрібно провести нормалізацію границь.
 Наприклад, якщо ввели 20 і 11, потрібна нормалізація, після якої початок стане рівним 11, а кінець 20
    */

    public void execute() {
        cout("Task1", ApplicationInit.textColor);
        cout("Enter start:", ApplicationInit.textColor);
        var n1 = parseUtil.getParsedInt();
        cout("Enter end:", ApplicationInit.textColor);
        var n2 = parseUtil.getParsedInt();

        printNonPairDigitsInRange(getRange(n1,n2));
    }

    public int[] getRange(int a1, int a2) {
        //a1 = a1 < 0 ? a1 * -1 : a1;
        //a2 = a2 < 0 ? a2 * -1 : a2;
        a2 = a1 == a2 ? a2 += 10 : a2;
        return a2 > a1 ? new int[] { a1, a2 } : new int[] { a2, a1 };
    }

    public void printNonPairDigitsInRange(int[] range) {
        cout("Non pair digits in range [" + range[0] + ", " + range[1] + "]:", ApplicationInit.textColor);
        for (int i = range[0]; i < range[1]; i++) {
            if(i % 2 != 0)
                cout(i + ", ", ApplicationInit.textColor);
        }
    }
}
