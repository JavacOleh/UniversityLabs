package task.tasks;

import consoledInterface.controller.sub.input.Cin;
import javafx.scene.paint.Color;
import task.interfaces.Taskable;
import java.text.MessageFormat;
import java.util.regex.Pattern;

import static consoledInterface.controller.sub.output.Cout.cout;

/*
    Перевірте, чи надійно складений пароль.
     Пароль вважається надійним, якщо він складається з 8 або більше символів.
      Для створення паролю використовувати великі та малі англійські літери, цифри та символи !*_.
     Пароль має містити хоча б одному символу з перелічених допустимих типів символів.
*/

public class Task3 implements Taskable {
    protected final Cin cin;
    protected String password;
    protected String note;
    protected String case1;

    public Task3() {
        password = "";
        note = MessageFormat.format("""
                        Note that password must have:
                        * lowercase and uppercase english letters.
                        * Length of password 8 or more.
                        * contained numbers and symbols.
                        * at least 1 specific symbol like {0}
                        """,
                getSpecificSymbols());
        case1 = "\nPlease enter the password:\n";

        cin = Taskable.super.getCin();
    }

    @Override
    public String getTaskName() {
        return "Task3";
    }

    @Override
    public String getFirstOperationName() {
        return "Create password";
    }

    private String getSpecificSymbols() {
        return "@$#^&\'\"\\?";
    }

    public void secondOperation() {
        this.password = "Password1!#@!";
    }

    public void firstOperation() {
        cout(case1);
        cout(note, Color.RED);
        password = cin.getLine();
    }

    public void thirdOperation() {
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
