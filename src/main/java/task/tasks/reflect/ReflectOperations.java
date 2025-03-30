package task.tasks.reflect;

import consoledInterface.ApplicationInit;
import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ConcurrentUtil;
import consoledInterface.util.ParseUtil;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static consoledInterface.controller.sub.output.Cout.cout;

public class ReflectOperations {
    private ParseUtil parseUtil;
    private Cin cin;
    public ReflectOperations() {
        parseUtil = ApplicationInit.getIO().getTaskExectuor().parseUtil;
        cin = ApplicationInit.getIO().getTaskExectuor().getParseUtil().cin;
    }

    public <T> T getFilledClassByType(String type, Class<?> clazz, Set<Class<?>> neededTypeChildrens) {
        T tempClass = null;
        try {
            var neededChild = neededTypeChildrens
                    .stream()
                    .filter(s -> s.getSimpleName().contains(type))
                    .findFirst()
                    .get();

            tempClass = (T) neededChild.getDeclaredConstructor().newInstance();
        }catch (NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        var superClassFields = new ArrayList<>(List.of(tempClass.getClass().getSuperclass().getDeclaredFields()));
        var childClassFields = new ArrayList<>(List.of(tempClass.getClass().getDeclaredFields()));
        T finalClass = tempClass;

        cout("\n");

        superClassFields.forEach(field -> {
            cout("Please enter " + field.getName() + ":\n");

            ConcurrentUtil.sleep(100L);
            fillInput(field, finalClass);
        });

        cout("\n");

        childClassFields.forEach(field -> {
            cout("Please enter " + field.getName() + ":\n");

            ConcurrentUtil.sleep(100L);
            fillInput(field, finalClass);
        });

        return finalClass;
    }

    public <T> void fillInput(Field field, T temp) {
        String pattern = "-";
        field.setAccessible(true);
        switch (field.getType().getSimpleName()) {
            case "int" -> {
                try {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    int arg = parseUtil.getParsedInt(10,1);

                    ConcurrentUtil.awaitTillCinIsActive(countDownLatch, cin);

                    field.set(temp, arg);
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            case "double" -> {
                try {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    double arg = parseUtil.getParsedDouble(Double.MAX_VALUE / 1000, 1.0);

                    ConcurrentUtil.awaitTillCinIsActive(countDownLatch, cin);

                    field.set(temp, arg);
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            case "String" -> {
                try {
                    field.set(temp, cin.getLine());
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            case "boolean" -> {
                try {
                    cout("enter 'yes' or 'no'\n");
                    field.set(temp, cin.getLine().trim().contains("yes"));
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            case "LocalDate" -> {
                try {
                    cout(MessageFormat.format("enter in format(YY{0}MM{0}DD)\n", pattern), Color.DARKRED);
                    var tempDate = parseUtil.getParsedDate();
                    field.set(temp, tempDate);
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            case "Period" -> {
                try {
                    cout(MessageFormat.format("enter in format(YY{0}MM{0}DD)\n", pattern), Color.DARKRED);
                    var tempPeriod = parseUtil.getParsedPeriod(pattern);
                    field.set(temp, tempPeriod);
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        cout("\n");
    }
}
