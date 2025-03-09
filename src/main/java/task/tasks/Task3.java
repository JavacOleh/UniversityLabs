package task.tasks;

import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import consoledInterface.ApplicationInit;
import javafx.scene.paint.Color;
import task.interfaces.Taskable;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

/*
    Перевірте, чи надійно складений пароль.
     Пароль вважається надійним, якщо він складається з 8 або більше символів.
      Для створення паролю використовувати великі та малі англійські літери, цифри та символи !*_.
     Пароль має містити хоча б одному символу з перелічених допустимих типів символів.
*/

public class Task3 implements Taskable {
    protected final ParseUtil parseUtil;
    protected final Cin cin;
    protected String password;
    protected String task;
    protected String operation1;
    protected String note;
    protected String case1;

    public Task3(ParseUtil parseUtil) {
        password = "";
        task = "Task3";
        operation1 = "1.Create password";
        note = MessageFormat.format("""
                        Note that password must have:
                        * lowercase and uppercase english letters.
                        * Length of password 8 or more.
                        * contained numbers and symbols.
                        * at least 1 specific symbol like {0}
                        """,
                getSpecificSymbols());
        case1 = "\nPlease enter the password:\n";

        this.parseUtil = parseUtil;
        cin = ApplicationInit.getIO().getInputController().getCin();
    }

    public void mainTaskExecute() {
        int choice;
        int exitChoice = 4;
        do {
            system("cls");
            cout(MessageFormat.format("""
                            ---------------{1}---------------
                            Please select operation:
                            {2}
                            2.Let program do first operation itself
                            3.Execute task
                            {0}.Exit from task
                            """, exitChoice, task, operation1),
                    ApplicationInit.textColor
            );
            choice = parseUtil.getParsedInt(exitChoice, 0);

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
            case 1 -> firstOperation();
            case 2 -> secondOperation();
            case 3 -> thirdOperation();
        }
    }

    private String getSpecificSymbols() {
        return "@$#^&\'\"\\?";
    }

    protected void secondOperation() {
        this.password = "Password1!#@!";
    }

    protected void firstOperation() {
        cout(case1);
        cout(note, Color.RED);
        password = cin.getLine();
    }

    protected void thirdOperation() {
        if (password.isEmpty()) {
            cout(emptySituation);
            return;
        }

        cout("\nYou or program entered:\n" + password + "\n");

        if (check())
            cout("\nIt's ok\n", Color.GREEN);
        else
            cout("\nIt's bad\n", Color.RED);
    }

    protected boolean check() {
        boolean hasLowercase = Pattern.compile("[a-z]").matcher(password).find();
        boolean hasUppercase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecialChar = Pattern.compile("[!*_.]").matcher(password).find();
        boolean hasCustomChar = Pattern.compile("[" + getSpecificSymbols() + "]").matcher(password).find();

        return hasLowercase &&
                hasUppercase &&
                hasNumber &&
                hasSpecialChar &&
                hasCustomChar &&
                password.length() >= 8;
    }
}
