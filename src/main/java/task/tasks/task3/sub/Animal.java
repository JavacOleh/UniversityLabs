package task.tasks.task3.sub;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Animal {
    protected String name;
    protected boolean isPredator;
    protected double weight;
    protected int age;

    public abstract String toVoice();
    public abstract double minKgFoodPerDay();
}
