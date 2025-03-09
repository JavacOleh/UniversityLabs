package task;

import consoledInterface.util.ParseUtil;
import consoledInterface.ApplicationInit;
import consoledInterface.util.ConcurrentUtil;
import lombok.Getter;
import task.interfaces.Taskable;
import task.tasks.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

@Getter
public class TaskExectuor {
    public final ParseUtil parseUtil;
    private final ExecutorService executorService;

    public TaskExectuor() {
        this.executorService = Executors.newSingleThreadExecutor();
        parseUtil = new ParseUtil(ApplicationInit.getIO().getInputController().getCin());
    }

    public void start() {
        executorService.submit(() -> {
            AtomicInteger choice = new AtomicInteger(-1);
            int exitChoice = 6;
            cout("Welcome to my application!\n", ApplicationInit.textColor);
            do {
                cout("""
                        ------------Main menu------------
                        please select operation:
                        1.Task 1
                        2.Task 2
                        3.Task 3
                        4.Task 4
                        5.Task 5
                        """, ApplicationInit.textColor);

                choice.set(parseUtil.getParsedInt(exitChoice, 1));

                ConcurrentUtil.await(choice, -1);

                executor(choice.get());

                if (choice.get() < exitChoice) {
                    system("pause");
                    system("cls");
                } else {
                    cout("Thanks for using my application!" +
                            "\nYou can close the program if you want." +
                            "\nTasks has ended, there won't be anything now." +
                            "\nRestart the app to restart task."
                    );
                }

            } while (choice.get() < exitChoice);
        });
    }

    public void executor(int choice) {
        system("cls");

        Taskable task = switch (choice) {
            case 1 -> new Task1(parseUtil);
            case 2 -> new Task2(parseUtil);
            case 3 -> new Task3(parseUtil);
            case 4 -> new Task4(parseUtil);
            case 5 -> new Task5(parseUtil);
            default -> null;
        };

        if (task != null)
            task.mainTaskExecute();
        else
            cout("Unknown Task!");

        system("cls");
    }
}
