package task.tasks;

import consoledInterface.controller.sub.input.Cin;
import javafx.scene.paint.Color;
import task.interfaces.Taskable;
import java.util.ArrayList;
import java.util.Random;

import static consoledInterface.controller.sub.output.Cout.cout;

public class Task2 implements Taskable {
    private final Cin cin;
    private String someText;

    public Task2() {
        cin = Taskable.super.getCin();
        someText = "";
    }

    /*
  Для деякого об’єкту StringBuilder виконайте таке:
    • отримайте підрядки з основного рядка з використанням getChars() або subString();
    • додайте підрядки з можливістю додавання підрядка як у кінець так і у середину існуючого рядка( append(), insert());
    • видаліть або замініть деякий підрядок з основного рядка (delete(), insert()).
     */

    @Override
    public String getTaskName() {
        return "Task2";
    }

    @Override
    public void firstOperation() {
        cout("\nEnter text please:\n");
        someText = cin.getLine();
    }

    @Override
    public void secondOperation() {
        someText =
                """
                Example text, this is just example.
                Not more than example and you should know this.
                And yeah, this is still text. Don't forget that!
                """;
    }

    @Override
    public void thirdOperation() {
        if (someText.isEmpty()) {
            cout(emptySituation);
            return;
        }

        String splitter = "\n";
        //Для удобства заменим все разделители на splitter
        someText = someText.replaceAll("[.,!?]", splitter);

        //Sub strings via subString()
        var stringBuilder = new StringBuilder(someText);
        ArrayList<String> subStrings = new ArrayList<>();
        int end = stringBuilder.lastIndexOf(splitter);
        int start;

        while (end != -1) {
            start = stringBuilder.lastIndexOf(splitter, end - 1); // Найдем начало подстроки перед разделителем
            if (start == -1) {
                subStrings.add(stringBuilder.substring(0, end));
            } else {
                subStrings.add(stringBuilder.substring(start + 1, end)); // +1, чтобы не включить сам разделитель
            }
            end = start;
        }

        //Adding subStrings
        stringBuilder = new StringBuilder("");
        var rnd = new Random();
        int randomInt;

        for (int i = 0; i < subStrings.size(); i++) {
            var temp = subStrings.get(i);
            if (i < subStrings.size() / 2)
                stringBuilder.append(temp).append(splitter);
            else {
                randomInt = rnd.nextInt(0, subStrings.size() / 2);
                stringBuilder.insert(randomInt, temp + splitter);
            }
        }

        cout("\nПідрядки з основного рядка з використанням subString():\n");
        cout(subStrings.toString(), Color.RED);

        cout("\n\nДодані підрядки як у кінець так і у середину існуючого рядка:");
        cout(stringBuilder.toString(), Color.BLUE);

        //Replacing some subString
        int replaceStart = stringBuilder.lastIndexOf(someText.substring(someText.lastIndexOf(splitter)));

        stringBuilder.replace(replaceStart, stringBuilder.length(), " Something ");

        cout("\nЗамінений деякий підрядок з основного рядка:");
        cout(stringBuilder.toString(), Color.BLUE);
    }
}
