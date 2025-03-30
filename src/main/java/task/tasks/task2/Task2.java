package task.tasks.task2;

import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import task.interfaces.Taskable;
import task.tasks.task2.sub.Article;
import task.tasks.task2.sub.Frequency;
import task.tasks.task2.sub.Magazine;
import task.tasks.task2.sub.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static consoledInterface.controller.sub.output.Cout.cout;
import static consoledInterface.util.System.system;

public class Task2 implements Taskable {
    private final Cin cin;
    private ParseUtil parseUtil;
    private List<Magazine> magazines;

    public Task2() {
        cin = Taskable.super.getCin();
        parseUtil = Taskable.super.getParseUtil();
        magazines = new ArrayList<>();
    }

    /*
 Реалізуйте клас «Людина»(ім’я, прізвище, дата народження). Визначити тип Frequency -
перерахування (enum) зі значеннями Weekly, Monthly, Yearly. Визначте класи Article( автор
статті(«Людина»), назва статті, рейтинг статті) та Magazine( назва журналу поле типу Frequency
з інформацією про періодичність виходу журналу, дата виходу журналу, тираж журналу, полі
типу Article[] зі списком статей у журналі).
     */

    @Override
    public String getTaskName() {
        return "Task2";
    }

    @Override
    public void firstOperation() {
        var tempStr = "";
        system("cls");
        do {
            magazines.add(getInputedMagazine());

            cout("\nShould keep adding magazines(enter 'yes' or 'no')?\n");
            tempStr = cin.getLine();
        }while (tempStr.equalsIgnoreCase("yes"));

        system("pause");
        system("cls");
    }

    @Override
    public void secondOperation() {
        var magazine = new Magazine();
        var temp = new ArrayList<Article>();
        temp.add(new Article(
                new Person("Oleh","Vasylevych", LocalDate.of(2005, 2,4)),
                "Stream Api Updates!!",
                5.00
        ));

        temp.add(new Article(
                new Person("Oleh","Vasylevych", LocalDate.of(2005, 2,4)),
                "You no longer need to put String[] args in psvm!!",
                5.00
        ));

        magazine.setCaption("Oracle! Java 24 is out!!");
        magazine.setType("Information Technology");
        magazine.setFrequency(Frequency.Monthly);
        magazine.setPublicDate(LocalDate.of(2025, 3, 18));
        magazine.setTopicsInside(temp);

        magazines.add(magazine);
    }

    @Override
    public void thirdOperation() {
        if(magazines.isEmpty())
            secondOperation();

        cout("\nResult:\n");
        magazines.forEach(s -> cout(s.toString()));
        cout("\n");

        system("pause");
        system("cls");
    }

    public Magazine getInputedMagazine() {
        var magazine = new Magazine();
        cout("\nPlease enter caption:\n");
        magazine.setCaption(cin.getLine());

        cout("\nPlease enter type:\n");
        magazine.setType(cin.getLine());

        cout("\nPlease enter frequency\n");
        cout("Please choose between such types:\n");
        cout("1.Yearly\n2.Monthly\n3.Weekly\n");
        var temp = parseUtil.getParsedInt(3,1);
        switch (temp) {
            case 1 -> magazine.setFrequency(Frequency.Yearly);
            case 2 -> magazine.setFrequency(Frequency.Monthly);
            case 3 -> magazine.setFrequency(Frequency.Weekly);
        }

        cout("\nPlease enter date of publishing:\n");
        magazine.setPublicDate(parseUtil.getParsedDate());

        cout("\nAddTopics:\n");
        var topicsInside = new ArrayList<Article>();
        var tempStr = "";
        do {
            topicsInside.add(topicInput());

            cout("\nAdd more topics(Enter 'yes' or 'no')?\n");
            tempStr = cin.getLine();
        }while (tempStr.equalsIgnoreCase("yes"));

        magazine.setTopicsInside(topicsInside);

        return magazine;
    }

    public Article topicInput() {
        var tempAuthor = new Person();
        var tempTopic = new Article();

        cout("\nPlease enter First Name of Author:\n");
        tempAuthor.setFirstName(cin.getLine());

        cout("\nPlease enter Last Name of Author:\n");
        tempAuthor.setLastName(cin.getLine());

        cout("\nPlease enter birth date of Author\n");
        tempAuthor.setBirthDate(parseUtil.getParsedDate());

        tempTopic.setAuthor(tempAuthor);

        cout("\nPlease enter caption of article\n");
        tempTopic.setCaption(cin.getLine());

        cout("\nPlease enter rate for article\n");
        tempTopic.setRate(parseUtil.getParsedDouble(5.00, 1.00));

        return tempTopic;
    }
}
