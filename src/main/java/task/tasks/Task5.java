package task.tasks;

import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import javafx.scene.paint.Color;
import task.interfaces.Taskable;

import java.util.ArrayList;
import java.util.List;

import static consoledInterface.controller.sub.output.Cout.cout;

/*
    Напишіть метод,
     який з деякого рядка, що містить певну сукупність електронних адрес
      видаляє усі адреси з доменом .ru
*/

public class Task5 implements Taskable {
    private Cin cin;
    private String exitWord;
    List<String> emails;

    public Task5() {
        super();
        exitWord = "exit";
        emails  = new ArrayList<>();
        cin = Taskable.super.getCin();
    }

    @Override
    public void firstOperation() {
        String temp;
        cout("\nPlease enter emails:\n");
        cout("you can enter " + exitWord + " to stop writing emails\n", Color.GREEN);

        do {
            cin.setCinActive(true); //Флажок устанавливаться в методе cin.getLine() успевает не всегда почему-то. Поэтому будем его тут устанавливать

            temp = cin.getLine();
            cout("\n");

            if(!temp.equalsIgnoreCase(exitWord))
                emails.add(temp);

        }while (!temp.equalsIgnoreCase(exitWord));
    }

    @Override
    public void secondOperation() {
        emails.add("RussianEmail@mail.ru");
        emails.add("SecondRussianEmail@mail.ru");
        emails.add("TempEmail@gmail.com");
        emails.add("PetroPoroshenko@president.ua");
    }

    @Override
    public void thirdOperation() {
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

    @Override
    public String getFirstOperationName() {
        return "Add emails";
    }

    @Override
    public String getTaskName() {
        return "Task5";
    }
}
