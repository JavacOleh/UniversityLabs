package task.tasks;


import common.FileService;
import consoledInterface.util.ParseUtil;
import consoledInterface.ApplicationInit;

import java.nio.file.Path;

import static consoledInterface.util.System.system;
import static consoledInterface.controller.sub.output.Cout.cout;

public class Task2 {
    private final FileService fileService;
    private final ParseUtil parseUtil;

    private int x;
    private double distanceAtoB;
    private double distanceBtoC;
    private double cargoWeight;

    public Task2(ParseUtil parseUtil) {
        this.parseUtil = parseUtil;
        fileService = new FileService(Path.of("src/data/Task2.txt"));
    }

    public void execute() {
        int choice;
        int exitChoice = 4;
        cout("Welcome to task 2!", ApplicationInit.textColor);
        do {
            cout("""
                    Please select operation:
                    
                    1.Read such data from file:
                    * 'x' value,
                    * distance between point A & B,
                    * distance between point B & C,
                    * cargo weight.
                    
                    2.Write such data to file:
                    * 'x' value of oil,
                    * distance between point A & B,
                    * distance between point B & C,
                    * cargo weight.
                    ! You will enter that data in console !
                    
                    3.Calculate minimum fuel receivement at point B
                     to get to point C from point A.
                    ! If you choose that option without
                    entering data, calculate won't happen !
                    
                    """ + exitChoice + ".Exit from task", ApplicationInit.textColor
            );

            choice = parseUtil.getParsedInt(exitChoice, 1);

            operationExecutor(choice);
            if (choice < exitChoice) {
                system("pause");
                system("cls");
            }

        } while (choice < exitChoice);
    }

    /*
  Вантажний літак повинен пролетіти з вантажем з пункту А пункт С через пункт В. Ємність бака для палива у літака — Х літрів (значення зчитується з файлу).
  Споживання палива на 1 км залежно від ваги вантажу літака таке:
- до 500 кг - 1 літрів/км;
- до 1000 кг - 4 літрів/км;
- до 1500 кг - 7 літрів/км;
- до 2000 кг - 9 літрів/км;
- більше 2000 кг – літак не піднімає.
  З файлу вводиться відстань між пунктами А і В і відстань між пунктами В і С, а також вага вантажу.
  Програма повинна розрахувати, яку мінімальну кількість палива необхідно для дозаправки літака в пункті В, щоб долетіти з пункту А до пункту С.
  У разі неможливості подолати будь-яку з відстаней — програма повинна вивести повідомлення про неможливість польоту за введеним маршрутом.
     */

    private void operationExecutor(int choice) {
        switch (choice) {
            case 1: {
                x = Integer.parseInt(fileService.getPropertyFromFile("x"));
                distanceAtoB = Double.parseDouble(fileService.getPropertyFromFile("distanceAtoB"));
                distanceBtoC = Double.parseDouble(fileService.getPropertyFromFile("distanceBtoC"));
                cargoWeight = Double.parseDouble(fileService.getPropertyFromFile("cargoWeight"));
                break;
            }

            case 2: {
                cout("Please enter x(current fuel):", ApplicationInit.textColor);
                x = parseUtil.getParsedInt(7000, 0);

                cout("Please enter distance between point A & B(in kilometers):", ApplicationInit.textColor);
                distanceAtoB = parseUtil.getParsedDouble();

                cout("Please enter distance between point B & C(in kilometers):", ApplicationInit.textColor);
                distanceBtoC = parseUtil.getParsedDouble();

                cout("Please enter cargo weight(in kilograms):", ApplicationInit.textColor);
                cargoWeight = parseUtil.getParsedInt();

                StringBuilder temp = new StringBuilder();
                temp.append("x: " + x + "\n");
                temp.append("distanceAtoB: " + distanceAtoB + "\n");
                temp.append("distanceBtoC: " + distanceBtoC + "\n");
                temp.append("cargoWeight: " + cargoWeight + "\n");

                fileService.writeDataToFilePath(temp.toString());
                break;
            }

            case 3: {
                int fuelConsumption = getFuelConsumption();
                if (fuelConsumption == -1) {
                    cout("Airplane cannot fly because cargo weight is above 2000!", ApplicationInit.textColor);
                    return;
                }

                double fuelNeededAB = distanceAtoB * fuelConsumption;
                double fuelNeededBC = distanceBtoC * fuelConsumption;

                if (fuelNeededAB > x) {
                    cout("Airplane cannot get to point B.", ApplicationInit.textColor);
                    return;
                }

                if (fuelNeededBC > x) {
                    cout("Airplane cannot fly to point C event after adding more fuel.", ApplicationInit.textColor);
                    return;
                }

                int remainingFuel = (int) (x - fuelNeededAB);
                int refuelNeeded = (int) Math.max(0, fuelNeededBC - remainingFuel);

                cout("In point B you need to add " + refuelNeeded + " liters to get from point A to point C.", ApplicationInit.textColor);
                break;
            }
        }
    }

    private int getFuelConsumption() {
        if (cargoWeight > 2000) return -1; // Літак не може підняти вантаж
        if (cargoWeight > 1500) return 9;
        if (cargoWeight > 1000) return 7;
        if (cargoWeight > 500) return 4;
        return 1;
    }
}
