package task.tasks.task1;

import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import task.interfaces.Taskable;
import task.tasks.task1.sub.placement.Placement;
import task.tasks.task1.sub.placement.placements.Apartment;
import task.tasks.task1.sub.placement.placements.House;
import task.tasks.reflect.TasksReflectUtil;
import task.tasks.task1.sub.PlacementPool;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

public class Task1 implements Taskable {
    private final Cin cin;
    public TasksReflectUtil task1ReflectUtil;
    private PlacementPool placementPool;
    private final ParseUtil parseUtil;
    protected Class<?> aClass;

    public Task1() {
        super();
        cin = Taskable.super.getCin();
        parseUtil = Taskable.super.getParseUtil();

        placementPool = new PlacementPool();
        task1ReflectUtil = new TasksReflectUtil();
        aClass = Placement.class;
    }

    /*
    Завдання 1. Описати клас для зберігання даних про заданий тип об’єктів у заданій
предметній області. При описі класу дотримуватися принципу інкапсуляції – усі поля з
даними повинні бути закритими, а для роботи з даними, що містяться у цих полях,
реалізувати відповідні методи. Для створення екземплярів класу реалізувати відкриті
конструктори, що заповнюють поля об’єктів даними. Реалізувати перегрузку одного –
двох методів класу та конструктора класу. Для демонстрації функціонування класу
створити масив з 5-7 єкземплярів класу. Для створеного масиву реалізувати:
а) вивід на консоль даних з усіх елементів масиву;
б) вивід даних лише тих елементів, які відповідають заданому у варіанті завдання
критерію;
в) пошук в масиві та вивід на консоль об’єктів з вказаною властивістю (згідно до
варіанта).

2.
Предметна область: оренда житла, клас: помешкання, орієнтовний перелік полів:
адреса, кількість кімнат, вартість оренди, помешкання орендовано (так/ні), дата оренди,
термін оренди.

Вивести окремо список вільних та окремо список орендованих помешкань.
Реалізувати пошук вільних помешкань з вказаною кількістю кімнат та допустимою вартістю оренди.
    */

    @Override
    public String getFirstOperationName() {
        return "Add placements";
    }

    @Override
    public String getTaskName() {
        return "Task1";
    }

    @Override
    public void firstOperation() {
        String keepCycle;
        Placement temp;
        var placementChildrens = new ArrayList<>(task1ReflectUtil.getNeededTypeChildrens(aClass));
        int minChild = 0;
        int maxChild = placementChildrens.size() - 1;

        do {
            cout("\nPlease choose what type is it gonna be:\n");
            cout(MessageFormat.format("""
                            Enter number between {0}-{1}:
                            """,
                    minChild,
                    maxChild)
            );

            for (int i = minChild; i <= maxChild; i++) {
                var str = placementChildrens.get(i).getName();
                cout(i + ": " + str.substring(str.lastIndexOf('.') + 1) + "\n");
            }
            int childChoice;

            do {
                childChoice = parseUtil.getParsedInt(maxChild, minChild);
            } while (childChoice == -1);

            var childName = placementChildrens.get(childChoice).getName();
            childName = childName.substring(childName.lastIndexOf('.') + 1);

            temp = task1ReflectUtil.getFilledDataByType(childName, aClass);

            placementPool.addPlacement(temp);

            cout("\nAdd more (enter 'yes' or 'no')?\n");
            keepCycle = cin.getLine();

        } while (keepCycle.equalsIgnoreCase("yes"));
    }

    @Override
    public void secondOperation() {
        placementPool = new PlacementPool();
        Placement placement = new Apartment(
                "Kremenchuk",
                4,
                100,
                false,
                LocalDate.of(2025, 8, 1),
                Period.of(0, 6, 0),
                5,
                true
        );

        Placement placement2 = new House(
                "Wroclaw",
                2,
                500,
                true,
                LocalDate.of(2025, 4, 1),
                Period.of(0, 4, 0),
                1,
                true,
                true
        );

        Placement placement3 = new Apartment(
                "Ostrava",
                4,
                100,
                false,
                LocalDate.of(2025, 8, 1),
                Period.of(0, 6, 0),
                5,
                true
        );

        placementPool.addPlacement(placement);
        placementPool.addPlacement(placement2);
        placementPool.addPlacement(placement3);
    }

    @Override
    public void thirdOperation() {
        if(placementPool.getPlacements().isEmpty())
            secondOperation();

        int exitChoice = 5;
        int choice;
        system("cls");
        do {
            cout("\nPlease choose an operation:\n");
            cout(MessageFormat.format("""
                            1.Show array
                            2.Show rented placements
                            3.Show non-rented placements
                            4.Look placements by price and rooms count
                            {0}.Exit from subTask
                            """,
                    exitChoice
            ));
            choice = parseUtil.getParsedInt(exitChoice, 1);

            subTasksExecutor(choice);

            if (choice < exitChoice) {
                cout("\n");
                system("pause");
                system("cls");
            }

        } while (choice < exitChoice);
    }

    private void subTasksExecutor(int choice) {
        switch (choice) {
            case 1 -> placementPool.print();

            case 2 -> printPlacements(
                    new ArrayList<>(
                            placementPool.getPlacements()
                                    .stream()
                                    .filter(Placement::isAlreadyRented)
                                    .toList())
            );

            case 3 -> printPlacements(
                    new ArrayList<>(
                            placementPool.getPlacements()
                                    .stream()
                                    .filter(s -> !s.isAlreadyRented())
                                    .toList())
            );

            case 4 -> {
                double maxPrice;
                double desiredRoomsCount;

                cout("\nPlease enter max price of renting per month:\n");
                maxPrice = parseUtil.getParsedDouble(10_000, 1.0);

                cout("\nPlease enter desired rooms count:\n");
                desiredRoomsCount = parseUtil.getParsedInt(10, 1);

                printPlacements(new ArrayList<>(placementPool.getPlacements()
                        .stream()
                        .filter(s -> !s.isAlreadyRented())
                        .filter(s -> s.getRoomsCount() == desiredRoomsCount)
                        .filter(s -> s.getPricePerMonth() <= maxPrice)
                        .toList())
                );
            }

        }
    }

    private void printPlacements(List<Placement> placements) {
        PlacementPool temp = new PlacementPool(placements);
        temp.print();
    }
}
