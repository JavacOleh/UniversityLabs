package task.tasks;

import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import consoledInterface.ApplicationInit;
import javafx.scene.paint.Color;
import task.interfaces.Taskable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

import static consoledInterface.util.System.system;
import static consoledInterface.controller.sub.output.Cout.cout;

public class Task2 implements Taskable {
    private final ParseUtil parseUtil;
    private final Cin cin;
    private String someText;

    public Task2(ParseUtil parseUtil) {
        this.parseUtil = parseUtil;
        cin = ApplicationInit.getIO().getInputController().getCin();
        someText = "";
    }

    public void mainTaskExecute() {
        int exitChoice = 4;
        int choice;

        do {
            system("cls");
            cout(MessageFormat.format("""
                    ---------------Task2---------------
                    Please select operation:
                    1.Enter some text
                    2.Let program choose text
                    3.Execute task
                    {0}.Exit from task
                    """, exitChoice));
            choice = parseUtil.getParsedInt(exitChoice, 1);

            subTasksExecute(choice);

            if (choice < exitChoice) {
                cout("\n");
                system("pause");
                system("cls");
            }

        } while (choice < exitChoice);
    }

    /*
  Для деякого об’єкту StringBuilder виконайте таке:
    • отримайте підрядки з основного рядка з використанням getChars() або subString();
    • додайте підрядки з можливістю додавання підрядка як у кінець так і у середину існуючого рядка( append(), insert());
    • видаліть або замініть деякий підрядок з основного рядка (delete(), insert()).
     */

    public void subTasksExecute(int choice) {
        switch (choice) {
            case 1 -> {
                cout("\nEnter text please:\n");
                someText = cin.getLine();
            }

            case 2 -> someText =
                    "Example text, this is just example." +
                            "Not more than example and you should know this.\n" +
                            "And yeah, this is still text. Don't forget that!";

            case 3 -> {
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

                cout("\nДодані підрядки як у кінець так і у середину існуючого рядка:\n");
                cout(stringBuilder.toString(), Color.BLUE);

                //Replacing some subString
                int replaceStart = stringBuilder.lastIndexOf(someText.substring(someText.lastIndexOf(splitter)));

                stringBuilder.replace(replaceStart, stringBuilder.length(), " Something ");

                cout("\nЗамінений деякий підрядок з основного рядка:\n");
                cout(stringBuilder.toString(), Color.BLUE);
            }
        }
    }
}
