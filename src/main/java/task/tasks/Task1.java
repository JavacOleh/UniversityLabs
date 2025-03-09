package task.tasks;

import consoledInterface.ApplicationInit;
import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import javafx.scene.paint.Color;
import task.interfaces.Taskable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

public class Task1 implements Taskable {
    private final ParseUtil parseUtil;
    private final Cin cin;
    private String someText;

    public Task1(ParseUtil parseUtil) {
        this.parseUtil = parseUtil;
        cin = ApplicationInit.getIO().getInputController().getCin();
        someText = "";
    }

    /*
    З заданим текстом необхідно виконати наступне:
    • підрахувати кількість, слів та речень у рядку;
    • продублювати найдовший рядок у тексті;
    • існує масив заборонених слів. Необхідно замінити символи заборонених слів зірочками.
    */

    @Override
    public void mainTaskExecute() {
        int exitChoice = 4;
        int choice;
        do {
            system("cls");
            cout(MessageFormat.format("""
                    ---------------Task1---------------
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

                int wordsCountInLine = someText.split("\\s+").length;
                int sentencesCountInEachLine = someText.split("[.!?]").length;
                StringBuffer theBiggestSentence = new StringBuffer();
                AtomicReference<String> filteredText = new AtomicReference<>(someText);

                //TheBiggestLine
                theBiggestSentence.append(
                        Stream.of(someText.split("[.!?]"))
                                .max(Comparator.comparingInt(String::length))
                                .orElse("")
                );

                //Filtered text
                var badWords = getBadWords();
                badWords.forEach(badWord ->
                        filteredText.set(
                                filteredText.get().replaceAll(
                                        "(?i)" + badWord,
                                        "*".repeat(badWord.length())
                                )
                        )
                );
                cout(MessageFormat.format("""
                                
                                
                                Count of words in text: {0}
                                Count of sentences in text: {1}""",
                        wordsCountInLine,
                        sentencesCountInEachLine
                ));
                cout("\nThe biggest sentence in text:\n");
                cout(theBiggestSentence.toString(), Color.RED);
                cout("\nText without bad words(filtered text):\n");
                cout(filteredText.get(), Color.GREEN);
            }
        }
    }

    public ArrayList<String> getBadWords() {
        var temp = new ArrayList<String>();

        //EN
        temp.add("Dumb");
        temp.add("Idiot");
        temp.add("Dickhead");
        temp.add("Bitch");
        temp.add("Stupid");
        temp.add("Fuck");
        temp.add("Fagot");

        //Русские слова добавлять не буду, не хочу)
        return temp;
    }
}
