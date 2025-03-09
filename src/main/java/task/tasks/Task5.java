package task.tasks;

import consoledInterface.util.ConcurrentUtil;
import consoledInterface.util.ParseUtil;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static consoledInterface.controller.sub.output.Cout.cout;

/*
    Напишіть метод,
     який з деякого рядка, що містить певну сукупність електронних адрес
      видаляє усі адреси з доменом .ru
*/

public class Task5 extends Task3 {
    public String exitWord;
    List<String> emails;

    public Task5(ParseUtil parseUtil) {
        super(parseUtil);
        exitWord = "exit";
        note = "you can enter " + exitWord + " to stop writing emails\n";
        task = "Task5";
        operation1 = "1.Enter emails";
        case1 = "\nPlease enter emails:\n";
        emails  = new ArrayList<>();
    }

    @Override
    protected void firstOperation() {
        cout("\nSorry this operation is temporarily unavailable, " +
                "we're working on it.\nPlease keep checking our github for updates.\nThanks for your understanding.",
                Color.DARKRED
        );

        /*Bug with multithreading:
         (1-2 times works looks like okay but more times - stuns and doesn't keep to next code),
          some why doesn't read 'exit' when user entered to exit.
         */

        /*
        CountDownLatch latch = new CountDownLatch(1);
        String temp;
        cout(case1);
        cout(note, Color.GREEN);

        do {
            temp = cin.getLine();

            cout("\n");

            if (temp.trim().equalsIgnoreCase(exitWord)) {
                latch.countDown();
                break;
            } else
                emails.add(temp);


        } while (temp.trim().equalsIgnoreCase(exitWord));

        ConcurrentUtil.await(latch);
        */
    }

    @Override
    protected void secondOperation() {
        emails.add("RussianEmail@mail.ru");
        emails.add("SecondRussianEmail@mail.ru");
        emails.add("TempEmail@gmail.com");
        emails.add("PetroPoroshenko@president.ua");
    }

    @Override
    protected void thirdOperation() {
        if(emails.isEmpty()) {
            cout(emptySituation);
            return;
        }

        cout("\nThe emails you or the program entered:\n");
        emails.forEach(email -> cout(email + "\n", Color.GREY));

        emails = emails.stream().filter(s -> !s.contains(".ru")).toList();

        cout("\nGood emails:\n");

        emails.forEach(email -> cout(email + "\n", Color.GREEN));
    }
}
