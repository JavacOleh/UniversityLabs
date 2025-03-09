package task.interfaces;

public interface Taskable {
    void mainTaskExecute();
    void subTasksExecute(int choice);
    String emptySituation = "\nPlease use operation 1 or 2 and only then use this operation";
}
