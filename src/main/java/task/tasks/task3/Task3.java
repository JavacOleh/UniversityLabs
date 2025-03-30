package task.tasks.task3;

import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import task.interfaces.Taskable;
import task.tasks.reflect.TasksReflectUtil;
import task.tasks.task3.sub.Animal;
import task.tasks.task3.sub.child.Kangaroo;
import task.tasks.task3.sub.child.Rabbit;
import task.tasks.task3.sub.child.Tiger;
import task.tasks.task3.sub.child.Wolf;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static consoledInterface.controller.sub.output.Cout.cout;

public class Task3 implements Taskable {
    private List<Animal> zooPark;
    private ParseUtil parseUtil;
    private TasksReflectUtil reflectUtil;
    private Cin cin;
    private Class<?> aClass;

    public Task3() {
        parseUtil = Taskable.super.getParseUtil();
        cin = Taskable.super.getCin();
        aClass = Animal.class;

        zooPark = new ArrayList<>();
        reflectUtil = new TasksReflectUtil();
    }

    @Override
    public String getFirstOperationName() {
        return "Input data about animals";
    }

    @Override
    public String getTaskName() {
        return "Task3";
    }

    @Override
    public void firstOperation() {
        String keepCycle;
        Animal temp;
        var placementChildrens = new ArrayList<>(reflectUtil.getNeededTypeChildrens(aClass));
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

            temp = reflectUtil.getFilledDataByType(childName, aClass);

            zooPark.add(temp);

            cout("\nAdd more (enter 'yes' or 'no')?\n");
            keepCycle = cin.getLine();

        } while (keepCycle.equalsIgnoreCase("yes"));
    }

    @Override
    public void secondOperation() {
        Animal temp = new Tiger(true);
        temp.setAge(4);
        temp.setName("Valera");
        temp.setPredator(true);
        temp.setWeight(30);

        zooPark.add(temp);

        temp = new Kangaroo(2.2);
        temp.setAge(3);
        temp.setName("Chel");
        temp.setPredator(true);
        temp.setWeight(25);

        zooPark.add(temp);

        temp = new Rabbit(1.2);
        temp.setAge(2);
        temp.setName("Alesha");
        temp.setPredator(false);
        temp.setWeight(2.3);

        zooPark.add(temp);

        temp = new Wolf(true);
        temp.setAge(5);
        temp.setName("Karat");
        temp.setPredator(true);
        temp.setWeight(4);

        zooPark.add(temp);
    }

    @Override
    public void thirdOperation() {
        if(zooPark.isEmpty())
            secondOperation();

        double foodPerDayForWolfs = foodPerDayForTypedAnimal(Wolf.class);
        double foodPerDayForKangaroo = foodPerDayForTypedAnimal(Wolf.class);
        double foodPerDayForRabbit = foodPerDayForTypedAnimal(Wolf.class);
        double foodPerDayForTiger = foodPerDayForTypedAnimal(Wolf.class);
        double summaryFoodPerDay = foodPerDayForWolfs + foodPerDayForKangaroo + foodPerDayForRabbit + foodPerDayForTiger;

        cout(MessageFormat.format("""
                
                Predators in ZooPark: {0}
                Food(in kg) per day for:
                Kangaroos: {1}
                Rabbits: {2}
                Tigers: {3}
                Wolfs: {4}
                for all animals: {5}
                """,
                zooPark.stream().filter(Animal::isPredator).count(),
                foodPerDayForKangaroo,
                foodPerDayForRabbit,
                foodPerDayForTiger,
                foodPerDayForWolfs,
                summaryFoodPerDay
        ));
    }

    public double foodPerDayForTypedAnimal(Class<? extends Animal> aClass) {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);
        zooPark.forEach(animal -> {
            var clazz = animal.getClass().getName();
            var currentClass = clazz.substring(clazz.lastIndexOf('.') -1);
            var neededClass = aClass.getName().substring(aClass.getName().lastIndexOf('.') -1);

            if(currentClass.equalsIgnoreCase(neededClass)) {
                result.updateAndGet(v -> v + animal.minKgFoodPerDay());
            }
        });

        return result.get();
    }
    /*
    Створити базовий клас «Тварина» та похідні класи “Тигр”, “Кролик”, “Вовк”, “Кенгуру”. За
допомогою конструкторів встановити ім’я кожної тварини та її характеристики, визначте метод,
який повертає рядок, який містить відображення звуку, що видається тваринам, а також інші
необхідні методи та поля.
Створіть змінну “Зоопарк”. Виведіть на екран кількість хижаків, які проживають у зоопарку,
визначте обсяг корму різних категорій необхідного для нормального існування Зоопарку
    */
}
