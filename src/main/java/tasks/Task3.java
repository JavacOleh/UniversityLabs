package tasks;

import common.ConsoleUtil;
import common.ParseService;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
    private final ParseService parseService;
    private final ConsoleUtil consoleUtil;
    private double sum = 0;
    private final String currency = " kč";

    public Task3(ParseService parseService) {
        this.parseService = parseService;
        this.consoleUtil = new ConsoleUtil(parseService);
    }

    public void execute() {
        int choice;
        int exitChoice = 2;
        System.out.println("Welcome to task 3!");
        do {
            System.out.print(MessageFormat.format("""
                    Please select operation:
                    1.Execute task
                    {0}.Exit from task
                    """, exitChoice));
            choice = parseService.getParsedInt(exitChoice, 0);

            operationExecutor(choice);

        }while (choice < exitChoice);
    }

    private void operationExecutor(int choice) {
        switch (choice) {
            case 1 -> {
                int peopleSize;
                System.out.print("""
                        Hello Waiter!
                        please enter how many people will make order:
                        """);
                peopleSize = parseService.getParsedInt(Integer.MAX_VALUE - 1, 1);
                taskExecute(peopleSize);
            }
        }
    }

    private void taskExecute(int peopleSize) {
        int person = 0;
        LinkedHashMap<String, Double> food = getFoodContainer();
        int choice;

        do {
            System.out.print(MessageFormat.format("""
                    Please select food for {0} person(enter a digit please):
                    """, person));

            var keySet = new ArrayList<String>(food.keySet());
            var values = new ArrayList<Double>(food.values());
            for (int i = 0; i < food.size(); i++) {
                System.out.println(i + 1 + ": " + keySet.get(i) + " - " + values.get(i) + currency);
            }

            System.out.println(food.size() + 1 + ": End order for person number " + (person + 1));

            choice = parseService.getParsedInt(food.size() + 1, 1);

            var priceList = new ArrayList<Double>(food.values());

            if(choice < food.size() + 1) {
                sum += priceList.get(choice - 1);
                System.out.println("Successfully added food for " + (person + 1));
            }else {
                person++;
                System.out.println("continuing with next person " + (person + 1));
            }

            consoleUtil.waitAndCls();

        }while (person < peopleSize);

        System.out.println("The sum of this company is: " + sum + currency);
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
