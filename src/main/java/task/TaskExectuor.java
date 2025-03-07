package task;

import consoledInterface.util.ParseUtil;
import consoledInterface.ApplicationInit;
import consoledInterface.util.ConcurrentUtil;
import lombok.Getter;
import task.tasks.*;

import java.text.MessageFormat;
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

    public void main() {
        executorService.submit(() -> {
            AtomicInteger choice = new AtomicInteger(-1);
            int exitChoice = 7;
            cout("Welcome to my application!", ApplicationInit.textColor);
            do {
                cout("""
                ------------Main menu------------
                please select operation:
                1.Task 1
                2.Task 2
                3.Task 3
                4.Task 4
                5.Task 5
                6.Task 6
                """, ApplicationInit.textColor);

                choice.set(parseUtil.getParsedInt(exitChoice, 1));

                ConcurrentUtil.await(choice, -1);

                executor(choice.get());

                if (choice.get() < exitChoice) {
                    system("pause");
                    system("cls");
                }else {
                    cout("Thanks for using my application!" +
                            "\nYou can close the program if you want." +
                            "\nTask has ended, there won't be anything now." +
                            "\nRestart the app to restart task."
                    );
                }

            } while (choice.get() < exitChoice);
        });
    }

    public void executor(int choice) {
        system("cls");
        switch (choice) {
            case 1: {
                Task1 task1 = new Task1(parseUtil);
                task1.execute();
                break;
            }

            case 2: {
                Task2 task2 = new Task2(parseUtil);
                task2.execute();
                break;
            }

            case 3: {
                Task3 task3 = new Task3(parseUtil);
                task3.execute();
                break;
            }

            case 4: {
                Task4 task4 = new Task4(parseUtil);
                task4.execute();
                break;
            }

            case 5: {
                Task5 task5 = new Task5(parseUtil);
                task5.execute();
                break;
            }
            case 6: {
                Task6 task6 = new Task6(parseUtil);
                task6.execute();
                break;
            }
        }
        system("cls");
    }
}
