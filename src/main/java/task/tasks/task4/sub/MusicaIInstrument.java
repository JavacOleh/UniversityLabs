package task.tasks.task4.sub;

import lombok.*;

import static consoledInterface.controller.sub.output.Cout.cout;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class MusicaIInstrument {
    private String name;

    public abstract void sound();
    public abstract void desc();
    public abstract void history();

    public void show() {
        cout("\nName: + " + name + "\n");
    }
}
