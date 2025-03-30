package task.tasks.reflect;

import consoledInterface.ApplicationInit;
import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import javafx.scene.paint.Color;
import org.reflections.Reflections;
import task.tasks.task1.sub.placement.Placement;
import task.tasks.task3.sub.Animal;

import java.lang.reflect.Field;
import java.util.Set;

import static consoledInterface.controller.sub.output.Cout.cout;

public class TasksReflectUtil {
    protected final Cin cin;
    protected final ParseUtil parseUtil;
    protected ReflectOperations reflectOperations;
    public TasksReflectUtil() {
        cin = ApplicationInit.getIO().getInputController().getCin();
        parseUtil = new ParseUtil(cin);
        reflectOperations = new ReflectOperations();
    }

    public <T> T getFilledDataByType(String type, Class<?> clazz) {
        return reflectOperations.getFilledClassByType(type, clazz, getNeededTypeChildrens(clazz));
    }

    public <T> void inputField(Field field, T temp) {
        reflectOperations.fillInput(field, temp);
    }

    public Set<Class<?>> getNeededTypeChildrens(Class<?> clazz) {
        String className = clazz.getName();
        String type = className.substring(className.lastIndexOf('.') + 1);
        return switch (type) {
            case "Placement" -> (Set<Class<?>>) (Set<?>) getNeededTypeTask1(); // Приведение типов
            case "Animal" -> (Set<Class<?>>) (Set<?>) getNeededTypeTask3(); // Приведение типов
            default -> {
                cout("\nNull Pointer Exception: TasksReflectUtil.java, Line 47\n", Color.RED);
                yield null;
            }
        };
    }

    public Set<Class<? extends Placement>> getNeededTypeTask1() {
        Reflections reflections = new Reflections("task.tasks.task1.sub.placement.placements");
        return reflections.getSubTypesOf(Placement.class);
    }

    public Set<Class<? extends Animal>> getNeededTypeTask3() {
        Reflections reflections = new Reflections("task.tasks.task3.sub.child");
        return reflections.getSubTypesOf(Animal.class);
    }
}
