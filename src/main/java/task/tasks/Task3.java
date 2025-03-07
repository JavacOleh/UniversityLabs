package task.tasks;

import consoledInterface.util.ParseUtil;
import consoledInterface.ApplicationInit;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

public class Task3 {
    /*
    У кофетерій прийшли кілька друзів.
     Реалізувати програму розрахунку вартості замовлення в кафетерії, за умови,
      що замовлення може бути від кількох людей і кожен клієнт формує свою частину замовлення.
       Необхідно запитати у користувача на скільки людина замовлення.
        Далі кожній людині виводитися меню (назви напоїв, кондитерських виробів і їх ціна) і він обирає.
         Передбачити можливість вибору декількох складників меню для клієнта, якщо він бажає додати ще щось до свого замовлення.
Результат роботи програми – підрахунок з суми загального замовлення всієї компанії.
 Створити алгоритм таким чином, щоб була можливість обслуговувати багато компаній.
    */
    private final ParseUtil parseUtil;
    private double sum = 0;
    private final String currency = " kč";

    public Task3(ParseUtil parseUtil) {
        this.parseUtil = parseUtil;
    }

    public void execute() {
        int choice;
        int exitChoice = 2;
        cout("Welcome to task 3!", ApplicationInit.textColor);
        do {
            cout(MessageFormat.format("""
                    Please select operation:
                    1.Execute task
                    {0}.Exit from task
                    """, exitChoice),
                    ApplicationInit.textColor
            );
            choice = parseUtil.getParsedInt(exitChoice, 0);

            operationExecutor(choice);

        }while (choice < exitChoice);
    }

    private void operationExecutor(int choice) {
        switch (choice) {
            case 1 -> {
                int peopleSize;

                cout("""
                        Hello Waiter!
                        please enter how many people will make order:
                        """,
                        ApplicationInit.textColor
                );
                peopleSize = parseUtil.getParsedInt(Integer.MAX_VALUE - 1, 1);
                taskExecute(peopleSize);
            }
        }
    }

    private void taskExecute(int peopleSize) {
        int person = 0;
        LinkedHashMap<String, Double> food = getFoodContainer();
        int choice;

        do {
            cout(MessageFormat.format("""
                    Please select food for {0} person(enter a digit please):
                    """, person), ApplicationInit.textColor);

            var keySet = new ArrayList<String>(food.keySet());
            var values = new ArrayList<Double>(food.values());
            for (int i = 0; i < food.size(); i++) {
                cout(i + 1 + ": " + keySet.get(i) + " - " + values.get(i) + currency, ApplicationInit.textColor);
            }

            cout(food.size() + 1 + ": End order for person number " + (person + 1), ApplicationInit.textColor);

            choice = parseUtil.getParsedInt(food.size() + 1, 1);

            var priceList = new ArrayList<Double>(food.values());

            if(choice < food.size() + 1) {
                sum += priceList.get(choice - 1);
                cout("Successfully added food for " + (person + 1), ApplicationInit.textColor);
            }else {
                person++;
                cout("continuing with next person " + (person + 1), ApplicationInit.textColor);
            }

            system("pause");
            system("cls");

        }while (person < peopleSize);

        cout("The sum of this company is: " + sum + currency, ApplicationInit.textColor);
    }

    private LinkedHashMap<String, Double> getFoodContainer() {
        var food = new LinkedHashMap<String, Double>();

        food.put("Durum kebab", 140.0);
        food.put("Grander Texas Box", 241.0);
        food.put("Single BigTasty Bacon Menu", 185.0);
        food.put("Sushi Philadelfia(16 pieces)", 230.0);
        food.put("Star pizza(32cm)", 175.0);
        food.put("Star pizza(45cm)", 240.0);
        food.put("Chips Lay's with crab flavor 133g", 100.0);
        food.put("Crackers Flint with kebab flavor 100g", 100.0);
        food.put("Crackers Flint with kebab crab 100g", 100.0);
        food.put("Milka with a large hazelnut 87g", 60.0);

        food.put("Coca Cola 0.5l", 50.0);
        food.put("Sprite 0.5l", 50.0);
        food.put("Blue Fanta 0.5l", 50.0);
        return food;
    }
}
