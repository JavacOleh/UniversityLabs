package task.interfaces;

import consoledInterface.ApplicationInit;
import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;

import java.text.MessageFormat;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

public interface Taskable {
    String emptySituation = "\nPlease use operation 1 or 2 and only then use this operation";

    default void mainTaskExecute() {
        ParseUtil parseUtil = getParseUtil();
        int exitChoice = getExitChoice();
        int choice;

        do {
            system("cls");
            cout(MessageFormat.format("""
                    ---------------{0}---------------
                    Please select operation:
                    1.{1}
                    2.Let program do first operation itself
                    3.Execute task
                    {2}.Exit from task
                    """,
                    getTaskName(),
                    getFirstOperationName(),
                    exitChoice
            ));
            choice = parseUtil.getParsedInt(exitChoice, 1);

            subTasksExecute(choice);

            if (choice < exitChoice) {
                cout("\n");
                system("pause");
                system("cls");
            }

        } while (choice < exitChoice);
    }

    default void subTasksExecute(int choice) {
        switch (choice) {
            case 1 -> firstOperation();

            case 2 -> secondOperation();

            case 3 -> thirdOperation();
        }
    }

    default ParseUtil getParseUtil() {
        return new ParseUtil(getCin());
    }

    default Cin getCin() {
        return ApplicationInit.getIO().getInputController().getCin();
    }

    default String getFirstOperationName() {
        return "Enter some text";
    }

    default int getExitChoice() {
        return 4;
    }

    String getTaskName();
    void firstOperation();
    void secondOperation();
    void thirdOperation();

}
