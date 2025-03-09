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

public class Task1 implements Taskable {
    private final Cin cin;
    private String someText;

    public Task1() {
        cin = Taskable.super.getCin();
        someText = "";
    }

    /*
    З заданим текстом необхідно виконати наступне:
    • підрахувати кількість, слів та речень у рядку;
    • продублювати найдовший рядок у тексті;
    • існує масив заборонених слів. Необхідно замінити символи заборонених слів зірочками.
    */

    @Override
    public String getTaskName() {
        return "Task1";
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

        int wordsCountInLine = someText.split("\\s+").length;
        int sentencesCountInEachLine = someText.split("[.!?]").length;
        StringBuilder theBiggestSentence = new StringBuilder();
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

        cout(MessageFormat.format(
                        """
                        
                        
                        Count of words in text: {0}
                        Count of sentences in text: {1}""",
                wordsCountInLine,
                sentencesCountInEachLine
        ));

        cout("\n\nThe biggest sentence in text:");
        cout(theBiggestSentence.toString(), Color.RED);
        cout("\n\nText without bad words(filtered text):\n");
        cout(filteredText.get(), Color.GREEN);
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
