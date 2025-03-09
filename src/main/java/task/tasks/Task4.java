package task.tasks;

import consoledInterface.util.ParseUtil;

import java.util.regex.Pattern;

/*
    За допомогою методу matches() класу Pattern перевірте слова вхідного рядку на відповідність деякому шаблону,
     згідно якого слова мають починатись з великої англійської літери, 1
      після чого має йти 1-8 символів англійського алфавіту, 2
       після чого має йти “tion”. 3
       А завершуватись слово має розділовим знаком (,.!:;). 4
     Приклад рядків, які мають належати такому шаблону:
      Situation:  Motivation,  Action!  Obligation.
*/

public class Task4 extends Task3 {
    String[] words;

    public Task4(ParseUtil parseUtil) {
        super(parseUtil);
        task = "Task4";
        operation1 = "1.Enter text";
        note = """
                Note that text must follow next rules:
                * Every words first letter must begin with uppercased english letter
                * After this word must have from 1 to 8 english letters
                * Every words must have ending as "tion"
                * After this word must have at least one of any those symbols:
                 ',' '.' '!' ':' ';'
                """;
        case1 = "\nPlease enter text:\n";
    }

    @Override
    protected boolean check() {
        // Разделяем строку на слова
        words = password.split("\\s+");

        // Проверяем каждое слово по порядку
        for (String word : words) {
            if (!checkWord(word))
                return false;
        }
        return true;
    }

    @Override
    protected void secondOperation() {
        password = "Situation:  Motivation,  Action!  Obligation.";
    }

    private boolean checkWord(String word) {
        String substring2 = word.substring(0, word.length() - 1);
        boolean startsWithUpperCase = Pattern.compile("^[A-Z]").matcher(word).find();

        String substring1 = word.substring(1, word.length() - 4); // Обрезаем последние 4 символа (для "tion")
        boolean hasValidLength = word.length() >= 5 && word.length() <= 11 && substring1.matches("[a-zA-Z]+");

        boolean endsWithTion = Pattern.compile("tion$").matcher(substring2).find();

        boolean endsWithPunctuation = Pattern.compile("[,.!:;]$").matcher(word).find();

        /*
        System.out.println("Word: " + word);
        System.out.println("startsWithUpperCase: " + startsWithUpperCase);
        System.out.println("hasValidLength: " + hasValidLength);
        System.out.println("endsWithTion: " + endsWithTion);
        System.out.println("endsWithPunctuation: " + endsWithPunctuation);
        */

        return startsWithUpperCase && hasValidLength && endsWithTion && endsWithPunctuation;
    }
}
