package task.tasks.task4;

import javafx.scene.paint.Color;
import task.interfaces.Taskable;
import task.tasks.task4.sub.MusicaIInstrument;
import task.tasks.task4.sub.child.Cello;
import task.tasks.task4.sub.child.Trombone;
import task.tasks.task4.sub.child.Ukulele;
import task.tasks.task4.sub.child.Violin;

import java.util.ArrayList;
import java.util.List;

import static consoledInterface.controller.sub.output.Cout.cout;

public class Task4 implements Taskable {
    private final List<MusicaIInstrument> musicaIInstrumentList;

    {
        musicaIInstrumentList = new ArrayList<>();
    }

    @Override
    public String getFirstOperationName() {
        return "Enter Data about music instruments";
    }

    @Override
    public String getTaskName() {
        return "Task4";
    }

    @Override
    public void firstOperation() {
        cout("\nOperation is not supported for this task.\nPlease use operation 2 or 3.\n", Color.RED);
    }

    @Override
    public void secondOperation() {
        musicaIInstrumentList.add(new Cello());
        musicaIInstrumentList.add(new Trombone());
        musicaIInstrumentList.add(new Ukulele());
        musicaIInstrumentList.add(new Violin());
    }

    @Override
    public void thirdOperation() {
        if(musicaIInstrumentList.isEmpty())
            secondOperation();

        cout("\n");
        musicaIInstrumentList.forEach(s -> {
            cout("\n");
            s.sound();
            s.show();
            s.desc();
            s.history();
            cout("\n");
        });
        cout("\n");
    }
    /*
    Створити базовий клас «Музичний інструмент» та похідні класи «Скрипка», «Тромбон»,
«Укулеле», “Віолончель”. За допомогою конструктора встановити ім’я кожного музичного
інструменту та його характеристики.
Реалізуйте для кожного з класів методи:
■ Sound — Видає звук музичного інструменту
(пишемо текстом у консоль);
■ Show — відображає назву музичного інструменту;
■ Desc — відображає опис музичного інструменту;
■ History — відображає історію створення музичного інструменту
     */
}
